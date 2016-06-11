package com.mmt.shubh.expensemanager.database.api;

import com.mmt.shubh.expensemanager.database.content.Expense;
import com.mmt.shubh.expensemanager.database.content.MemberExpense;
import com.mmt.shubh.expensemanager.expense.ExpenseFilter;
import com.mmt.shubh.expensemanager.expense.ExpenseListViewModel;

import java.util.List;

import rx.Observable;

/**
 * Created by styagi on 5/28/2015.
 */
public interface ExpenseDataAdapter extends IDataAdapter<Expense> {
    Observable<List<ExpenseListViewModel>> getExpensesWithFilters(long memberId);

    Observable<List<ExpenseListViewModel>> getExpenseByExpenseBookId(long expenseBookId);

    Observable<List<ExpenseListViewModel>> getExpensesWithFilters(ExpenseFilter filter);

    Observable<List<ExpenseListViewModel>> getExpenseByAccountId(long id);

    Observable<List<ExpenseListViewModel>> getExpenses(String selection);

    Observable<List<ExpenseListViewModel>> getAllSharedExpenseList(long id, long id2);

    Observable<List<MemberExpense>> getSharedExpenseDetails(long id, long id2);
}
