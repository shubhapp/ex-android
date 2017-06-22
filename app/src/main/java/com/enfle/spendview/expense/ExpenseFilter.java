/*
 * Copyright (c) 2016. . The Km2Labs Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.enfle.spendview.expense;




/**
 * Created by Subham Tyagi,
 * on 11/Nov/2015,
 * 4:16 PM
 * TODO:Add class comment.
 */
public class ExpenseFilter {

    public static final int TIME_FILTER_ALL = 1;
    public static final int TIME_FILTER_DAY = 2;
    public static final int TIME_FILTER_WEEK = 3;
    public static final int TIME_FILTER_MONTH = 4;
    public static final int TIME_FILTER_YEAR = 5;
    public static final int TIME_FILTER_CUSTOM = 6;

    private long mExpenseBookId;
    private long mMemberId;
    private int mTimeFilter;
    private long mCategoryId;
    private long mAccountId;

    private long mStartDate;

    private long mEndDate;

    private boolean isLatest;

    public long getAccountId() {
        return mAccountId;
    }

    public ExpenseFilter setAccountId(long accountId) {
        mAccountId = accountId;
        return this;
    }

    public long getCategoryId() {
        return mCategoryId;
    }

    public ExpenseFilter setCategoryId(long categoryId) {
        mCategoryId = categoryId;
        return this;
    }

    public long getExpenseBookId() {
        return mExpenseBookId;
    }

    public ExpenseFilter setExpenseBookId(long expenseBookId) {
        mExpenseBookId = expenseBookId;
        return this;
    }

    public long getMemberId() {
        return mMemberId;
    }

    public ExpenseFilter setMemberId(long memberId) {
        mMemberId = memberId;
        return this;
    }

    public int getTimeFilter() {
        return mTimeFilter;
    }

    public void setTimeFilter(int timeFilter) {
    }

    public long getEndDate() {
        return mEndDate;
    }

    public long getStartDate() {
        return mStartDate;
    }

    public boolean isLatest() {
        return isLatest;
    }

    public ExpenseFilter setIsLatest(boolean isLatest) {
        this.isLatest = isLatest;
        return this;
    }
}