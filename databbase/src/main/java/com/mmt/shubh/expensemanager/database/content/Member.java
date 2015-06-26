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

package com.mmt.shubh.expensemanager.database.content;

public class Member extends BaseContent{

    public String mMemberName;
    public String mMemberEmail;
    public ExpenseBook mExpenseBook;

    public String getMemberName() {
        return mMemberName;
    }

    public Member setMemberName(String memberName) {
        mMemberName = memberName;
        return this;
    }

    public String getMemberEmail() {
        return mMemberEmail;
    }

    public Member setMemberEmail(String memberEmail) {
        mMemberEmail = memberEmail;
        return this;
    }

    public ExpenseBook getExpenseBook() {
        return mExpenseBook;
    }

    public Member setExpenseBook(ExpenseBook expenseBook) {
        mExpenseBook = expenseBook;
        return this;
    }
}
