package com.km2labs.android.spendview.database.content.contract;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public interface ExpenseBookContract extends BaseColumns, BaseContract {

    String TABLE_NAME = "expense_book";
    String PATH_EXPENSE_BOOK = "ExpenseBook";

    String EXPENSE_BOOK_NAME = "name";
    String EXPENSE_BOOK_TYPE = "expense_book_type";
    String EXPENSE_BOOK_DESCRIPTION = "description";
    String EXPENSE_BOOK_PROFILE_IMAGE_URI = "profile_image_uri";
    String EXPENSE_BOOK_CREATION_TIME = "creation_time";
    String OWNER_KEY = "owner_key";

    Uri EXPENSE_BOOK_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT).authority(AUTHORITY).path(PATH_EXPENSE_BOOK).build();

}