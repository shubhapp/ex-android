package com.mmt.shubh.expensemanager.expense;

import android.content.Context;

import com.mmt.shubh.expensemanager.core.dagger.scope.ActivityScope;
import com.mmt.shubh.expensemanager.database.api.ExpenseBookDataAdapter;
import com.mmt.shubh.expensemanager.database.api.ExpenseDataAdapter;
import com.mmt.shubh.expensemanager.database.api.MemberDataAdapter;
import com.mmt.shubh.expensemanager.database.api.MemberExpenseDataAdapter;
import com.mmt.shubh.expensemanager.database.api.TransactionDataAdapter;
import com.mmt.shubh.expensemanager.database.api.exceptions.AccountDataAdapter;
import com.mmt.shubh.expensemanager.core.mvp.lce.LCEViewState;
import com.mmt.shubh.expensemanager.core.mvp.lce.LCEViewStateImpl;
import com.mmt.shubh.expensemanager.core.mvp.lce.MVPLCEView;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by subhamtyagi on 2/27/16.
 */
@Module
public class ModuleExpenseListFragment {

    @Provides
    @ActivityScope
    public ExpenseListPresenter provideExpenseListPresenter(Context context, ExpenseModel expenseModel) {
        return new ExpenseListPresenter(expenseModel, context);
    }

    @Provides
    @ActivityScope
    LCEViewState<List<ExpenseListViewModel>, MVPLCEView<List<ExpenseListViewModel>>> provideViewLCEViewState() {
        return new LCEViewStateImpl<>();
    }

    @Provides
    @ActivityScope
    public ExpenseModel provideExpenseModel(ExpenseDataAdapter expenseDataAdapter,
                                            MemberExpenseDataAdapter memberDataAdapter,
                                            TransactionDataAdapter transactionDataAdapter,
                                            AccountDataAdapter accountDataAdapter,
                                            MemberDataAdapter memberData, ExpenseBookDataAdapter expenseBookDataAdapter) {
        return new ExpenseModel(expenseDataAdapter, memberDataAdapter, transactionDataAdapter,
                accountDataAdapter, memberData,expenseBookDataAdapter);
    }
}