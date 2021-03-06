package com.km2labs.android.spendview.database.content.contract;

import android.provider.BaseColumns;

public interface ExpenseContract extends BaseColumns, BaseContract {
    String TABLE_NAME = "expenses";

    String PATH_EXPENSE = "EXPENSE";

    String PATH_EXPENSE_LIST = "Expense/List";

    String PATH_YEAR = "YEAR";
    String PATH_MONTH = "MONTH";

    int COLUMN_EXPENSE_ID = 0;
    int COLUMN_EXPENSE_AMOUNT = 1;
    int COLUMN_EXPENSE_DATE = 2;
    int COLUMN_EXPENSE_NAME = 3;
    int COLUMN_EXPENSE_PLACE = 4;
    int COLUMN_EXPENSE_DESCRIPTION = 5;
    int COLUMN_EXPENSE_BOOK = 6;
    int COLUMN_CATEGORY_KEY = 7;

    String EXPENSE_AMOUNT = "expense_amount";
    String EXPENSE_DATE = "expense_date";
    String EXPENSE_NAME = "expense_name";
    String EXPENSE_PLACE = "expense_place";
    String EXPENSE_DESCRIPTION = "expense_description";
    String EXPENSE_BOOK_KEY = "expense_book_key";
    String CATEGORY_KEY = "category_key";
    String TRANSACTION_KEY = "transaction_key";
    String OWNER_KEY = "owner_key";
    String ACCOUNT_KEY = "account_key";

}