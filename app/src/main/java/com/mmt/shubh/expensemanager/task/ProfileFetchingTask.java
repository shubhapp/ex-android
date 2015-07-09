package com.mmt.shubh.expensemanager.task;

import android.content.Context;

import com.mmt.shubh.expensemanager.database.content.UserInfo;
import com.mmt.shubh.expensemanager.setup.ProfileFetcher;

/**
 * Created by Subham Tyagi,
 * on 02/Jul/2015,
 * 1:28 AM
 * TODO:Add class comment.
 */
public class ProfileFetchingTask extends AbstractTask {

    public final static String ACTION_PROFILE_FETCH = "com.mmt.shubh.ACTION_FETCH_PROFILE";

    private ProfileFetcher mProfileFetcher;

    public ProfileFetchingTask(Context context, ProfileFetcher profileFetcher) {
        super(context);
        mProfileFetcher = profileFetcher;
    }

    @Override
    public TaskResult execute() {
        UserInfo userInfo = mProfileFetcher.fetchUserAccountDetails(mContext);
        mProfileFetcher.saveUser(mContext, userInfo);
        return null;
    }

    @Override
    public String getTaskAction() {
        return ACTION_PROFILE_FETCH;
    }
}
