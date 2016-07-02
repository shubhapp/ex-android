package com.mmt.shubh.expensemanager.account;

import com.mmt.shubh.core.database.Selection;
import com.mmt.shubh.expensemanager.database.content.contract.ExpenseContract;
import com.mmt.shubh.expensemanager.expense.ExpenseListViewModel;
import com.mmt.shubh.expensemanager.core.mvp.BasePresenter;
import com.mmt.shubh.expensemanager.utils.DateUtil;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AccountDetailPresenter extends BasePresenter<IAccountDetailView> {


    AccountModel mAccountModel;

    @Inject
    public AccountDetailPresenter(AccountModel accountModel) {
        mAccountModel = accountModel;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void loadExpenseByAccountId(long id) {
        LocalDate currentDate = LocalDate.now();
        LocalDateTime localDate = LocalDateTime.of(currentDate.getYear(), 1, 1, 0, 0);
        long currentYearTimeInMilli = DateUtil.toMilliSeconds(localDate);
        Selection.Builder builder = new Selection.Builder();
        builder.appendSelection(ExpenseContract.ACCOUNT_KEY, Selection.EQUAL, id)
                .appendOperation(Selection.AND)
                .appendSelection(ExpenseContract.EXPENSE_DATE, Selection.GREATER, currentYearTimeInMilli);

        mAccountModel.getExpenses(builder.build())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(d -> {
                    getView().showExpense(d);
                    createGraphData(d);
                },
                e -> getView().showError(e, false));
    }

    private void createGraphData(List<ExpenseListViewModel> listViewModels) {
        Map<Integer, Double> mapData = new HashMap<>();
        for (ExpenseListViewModel expenseListViewModel : listViewModels) {

            long expenseTime = expenseListViewModel.getExpenseDateInMill();

            Double expenseAmount = expenseListViewModel.getExpenseAmount();
            Integer mnth = DateUtil.getMonthCount(expenseTime);

            if (mapData.containsKey(mnth)) {
                Double amount = mapData.get(mnth);
                amount += expenseAmount;
                mapData.put(mnth, amount);
            } else {
                mapData.put(mnth, expenseAmount);
            }
        }
        getView().setData(mapData);
    }
}