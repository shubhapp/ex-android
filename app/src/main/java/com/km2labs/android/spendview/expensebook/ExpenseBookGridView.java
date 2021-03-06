package com.km2labs.android.spendview.expensebook;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.km2labs.android.spendview.core.recyclerview.ListRecyclerView;
import com.km2labs.shubh.expensemanager.R;
import com.km2labs.android.spendview.database.content.ExpenseBook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by subhamtyagi on 2/20/16.
 */
public class ExpenseBookGridView extends LinearLayout {

    public static final int MODE_EXPENSE_BOOK = 0;
    public static final int MODE_MEMBER = 1;
    public static final int MODE_SUMMARY = 2;

    ExpenseBookGridAdapter mExpenseBookListAdapter;

    TextView mEmptyText;

    TextView mMoreTextView;

    ProgressBar mProgressBar;

    View mProgressContainer;

    List<ExpenseBook> mExpenseBooks;
    boolean isExpended;

    int mMode;
    private ListRecyclerView.OnItemClickListener mItemClickListener = (parent, view, position, id) -> {
        // TODO: 2/20/16 Open expense detail view from here
        return false;
    };

    public ExpenseBookGridView(Context context) {
        super(context);
        init(context);
    }

    public ExpenseBookGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ExpenseBookGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ExpenseBookGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.expense_book_list_view, this, true);
        ListRecyclerView listRecyclerView = (ListRecyclerView) findViewById(R.id.list);

        mProgressContainer = findViewById(R.id.progress_container);
        mEmptyText = (TextView) findViewById(R.id.empty_text);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mMoreTextView = (TextView) findViewById(R.id.more);
        mExpenseBookListAdapter = new ExpenseBookGridAdapter();

        listRecyclerView.setAdapter(mExpenseBookListAdapter);
        listRecyclerView.setOnItemClickListener(mItemClickListener);

        mMoreTextView.setOnClickListener(v -> {
            if (isExpended) {
                collapseList();
            } else {
                expendList();
            }
        });
    }

    private void expendList() {
        mMoreTextView.setText("Less");
        isExpended = true;
        mExpenseBookListAdapter.clear();
        mExpenseBookListAdapter.addData(mExpenseBooks);
    }

    private void collapseList() {
        mMoreTextView.setText("More");
        isExpended = false;
        List<ExpenseBook> expenseBooks = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            expenseBooks.add(mExpenseBooks.get(i));
        }
        mExpenseBookListAdapter.clear();
        mExpenseBookListAdapter.addData(expenseBooks);
    }


    public void setMode(int mode) {
        mMode = mode;
    }

    public void addData(List<ExpenseBook> expenseBooks) {
        mExpenseBookListAdapter.setMode(mMode);
        mProgressContainer.setVisibility(GONE);
        mExpenseBooks = expenseBooks;
        if (expenseBooks.size() <= 2) {
            mExpenseBookListAdapter.addData(expenseBooks);
            mMoreTextView.setVisibility(GONE);
        } else {
            collapseList();
        }
    }

    public void showEmptyText(@StringRes int id) {
        mEmptyText.setVisibility(VISIBLE);
        mEmptyText.setText(id);
        mProgressBar.setVisibility(GONE);
    }
}
