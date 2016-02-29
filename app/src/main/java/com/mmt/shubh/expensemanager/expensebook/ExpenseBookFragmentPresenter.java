package com.mmt.shubh.expensemanager.expensebook;

import android.os.Bundle;
import android.text.TextUtils;

import com.mmt.shubh.expensemanager.Constants;
import com.mmt.shubh.expensemanager.database.content.ExpenseBook;
import com.mmt.shubh.expensemanager.mvp.MVPAbstractPresenter;
import com.mmt.shubh.expensemanager.mvp.MVPPresenter;

import org.parceler.Parcels;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Subham Tyagi,
 * on 11/Sep/2015,
 * 5:14 PM
 * TODO:Add class comment.
 */
public class ExpenseBookFragmentPresenter extends MVPAbstractPresenter<IExpenseBookFragmentView>
        implements MVPPresenter<IExpenseBookFragmentView> {

    ExpenseBookModel mExpenseBookModel;

    public ExpenseBookFragmentPresenter(ExpenseBookModel expenseBookModel) {
        mExpenseBookModel = expenseBookModel;
    }

    public void validateExpenseNameAndProceed(String expenseName, String mOutputFileUri,
                                              String expenseDescription, boolean isUpdate) {
        if (TextUtils.isEmpty(expenseName)) {
            getView().showEmptyError();
            return;
        }
        // TODO: 2/29/16 Add a check for duplicate expense book via name
        /*if (Validator.expenseNameExist(mContext, expenseName) && !isUpdate) {
            getView().showDuplicateExpenseBook();
            return;
        }*/

        ExpenseBook expenseBook = new ExpenseBook();

        expenseBook.setName(expenseName);
        expenseBook.setProfileImagePath(mOutputFileUri);
        expenseBook.setDescription(expenseDescription);
        mExpenseBookModel.addExpenseBook(expenseBook, isUpdate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(d -> {
                    getView().hideProgress();
                    if (isUpdate)
                        getView().onExpenseBookUpdate();
                    else {
                        Bundle expenseBookInfo = new Bundle();
                        expenseBookInfo.putParcelable(Constants.KEY_EXPENSE_BOOK, Parcels.wrap(d));
                        getView().addMemberFragment(expenseBookInfo);
                    }
                }, e -> getView().showError(e.getMessage()));
        getView().showCreatingExpenseBookProgress();
    }


    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

}
