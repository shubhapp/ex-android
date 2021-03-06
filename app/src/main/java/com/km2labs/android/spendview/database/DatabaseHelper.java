/*
 * Copyright (c) 2014. The MMT group Project
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.km2labs.android.spendview.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    /* Database Name */
    private static final String DATABASE_NAME = "Expense_Manager.db";

    /* Database version */
    private static int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        ExpenseDDL.createUserTable(database);
        ExpenseDDL.createAccountTable(database);
        ExpenseDDL.createExpenseBookTable(database);
        ExpenseDDL.createMemberTable(database);
        ExpenseDDL.createExpenseTable(database);
        ExpenseDDL.createCategoryTable(database);
        ExpenseDDL.createMemberExpenseTable(database);
        ExpenseDDL.createMemberExpenseBookTable(database);
        ExpenseDDL.createTransactionTable(database);
        ExpenseDDL.createCardTable(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

}
