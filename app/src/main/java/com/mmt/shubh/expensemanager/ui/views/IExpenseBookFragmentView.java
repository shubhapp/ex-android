package com.mmt.shubh.expensemanager.ui.views;

import android.os.Bundle;

import com.mmt.shubh.expensemanager.database.content.ExpenseBook;
import com.mmt.shubh.expensemanager.ui.mvp.MVPLCEView;
import com.mmt.shubh.expensemanager.ui.mvp.MVPView;

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
}
