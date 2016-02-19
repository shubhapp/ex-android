/*******************************************************************************
 * Copyright (c) 2015 AirWatch, LLC. All rights reserved.
 * This product is protected by copyright and intellectual property laws in
 * the United States and other countries as well as by international treaties.
 * AirWatch products may be covered by one or more patents queueed at
 * http://www.vmware.com/go/patents.
 ******************************************************************************/

package com.mmt.shubh.expensemanager.task;

import android.support.annotation.VisibleForTesting;

import com.mmt.shubh.expensemanager.debug.Logger;

import java.lang.ref.WeakReference;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by STyagi on 6/10/2015.
 * <p/>
 * Execute series of task in parallel or serial. Task should be subclass of {@link AbstractTask}
 */
public class TaskProcessor {

    private static TaskProcessor mTaskProcessor = new TaskProcessor();
    private String LOG_TAG = getClass().getName();
    private BlockingDeque<ITask> mTaskQueue;

    private ExecutorService mExecutorService = Executors.newSingleThreadExecutor();

    private WeakReference<OnTaskCompleteListener> mTaskCompleteListeners;

    private ITask mActive;

    private boolean isExecuting;

    private TaskProcessor() {
        mTaskQueue = new LinkedBlockingDeque<>(16);
    }

    public static TaskProcessor getTaskProcessor() {

        return mTaskProcessor;
    }

    /**
     * Add task at first position of task queue.
     *
     * @param task
     */
    public void addAtFrontOfQueue(ITask task) {
        if (task != null)
            mTaskQueue.addFirst(task);
    }

    /**
     * Add task to end of the queue
     *
     * @param task should be a subclass of {@link ITask}
     */
    public void addTask(ITask task) {
        if (task != null)
            mTaskQueue.add(task);
    }

    /**
     * Move any existing task to first position in the queue.
     *
     * @param task - {@link ITask}
     */
    public void moveToFirst(ITask task) {
        if (task != null) {
            mTaskQueue.remove(task);
            mTaskQueue.addFirst(task);
        }
    }

    /**
     * Move any existing task to end position in the queue.
     *
     * @param task - {@link ITask}
     */
    public void moveToEnd(ITask task) {
        if (task != null) {
            mTaskQueue.remove(task);
            mTaskQueue.add(task);
        }
    }

    /**
     * Start the execution of tasks
     */
    public void startExecution() {
        scheduleNext();
    }

    public void execute(ITask task) {
        mTaskQueue.offer(task);
        startExecution();
    }

    private void scheduleNext() {
        Logger.methodStart(LOG_TAG, "scheduleNext");
        if (!isExecuting) {
            mActive = mTaskQueue.poll();
            if (mActive != null) {
                mExecutorService.submit((new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        isExecuting = true;
                        executeTask(mActive);
                        isExecuting = false;
                        mActive = null;
                        scheduleNext();
                        return null;
                    }
                }));
            }
        }
        Logger.methodEnd(LOG_TAG, "scheduleNext");
    }

    /*
     * Execute single task
     */
    private void executeTask(ITask task) {
        Logger.methodStart(LOG_TAG, "executeTask -" + task.getTaskAction());
        TaskResult result = new TaskResult();
        try {
            result = task.execute();
        } catch (Exception e) {
            isExecuting = false;
            Logger.error(LOG_TAG, e.getMessage() + "\n" + e.getStackTrace().toString());
            throw new RuntimeException(e);
        }

        String action = task.getTaskAction();
        notifyListener(action, result);

        task.onPostExecute(result);
        Logger.methodEnd(LOG_TAG, "executeTask" + task.getTaskAction());
    }

    public void removeOnTaskCompleteListener(OnTaskCompleteListener listener) {
        if (mTaskCompleteListeners != null)
            mTaskCompleteListeners.clear();
    }

    @VisibleForTesting
    public ITask getFirstTask() {
        return mTaskQueue.peekFirst();
    }

    @VisibleForTesting
    public ITask getLastTask() {
        return mTaskQueue.peekLast();
    }

    public void setOnTaskCompleteListener(OnTaskCompleteListener listener) {
        mTaskCompleteListeners = new WeakReference<OnTaskCompleteListener>(listener);
    }

    public void notifyListener(String action, TaskResult result) {
        if (mTaskCompleteListeners != null && mTaskCompleteListeners.get() != null) {
            mTaskCompleteListeners.get().onTaskComplete(action, result);
        }
    }
}
