package com.mmt.shubh.expensemanager.expensebook.add;

import android.content.Context;

import com.mmt.shubh.expensemanager.core.dagger.scope.ActivityScope;
import com.mmt.shubh.expensemanager.database.api.ExpenseBookDataAdapter;
import com.mmt.shubh.expensemanager.database.api.MemberDataAdapter;
import com.mmt.shubh.expensemanager.expensebook.ExpenseBookModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by subhamtyagi on 2/29/16.
 */
@Module
public class ModuleExpenseBookUpdate {

    @Provides
    @ActivityScope
    public ExpenseBookModel provideExpenseBookModel(Context context, ExpenseBookDataAdapter expenseBookDataAdapter
            , MemberDataAdapter memberDataAdapter) {
        return new ExpenseBookModel(context, expenseBookDataAdapter, memberDataAdapter);
    }

    @Provides
    @ActivityScope
    public ExpenseBookFragmentPresenter provideExpenseBookFragmentPresenter(ExpenseBookModel expenseBookModel) {
        return new ExpenseBookFragmentPresenter(expenseBookModel);
    }

    @Provides
    @ActivityScope
    public AddMemberPresenter provideAddMemberPresenter(ExpenseBookModel expenseBookModel) {
        return new AddMemberPresenter(expenseBookModel);
    }
}
