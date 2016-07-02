package com.mmt.shubh.expensemanager.expensebook.add;

import android.os.Bundle;

import com.mmt.shubh.expensemanager.core.mvp.MVPView;

/**
 * Created by Subham Tyagi,
 * on 11/Sep/2015,
 * 4:42 PM
 * TODO:Add class comment.
 */
public interface IExpenseBookFragmentView extends MVPView {
    void showEmptyError();

    void showDuplicateExpenseBook();

    void addMemberFragment(Bundle expenseBookInfo);

    void showError(String payload);

    void showCreatingExpenseBookProgress();

    void hideProgress();

    void exit();

    void onExpenseBookUpdate();
}