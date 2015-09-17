package com.mmt.shubh.expensemanager.ui.fragment.expensebook;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.mmt.shubh.expensemanager.Constants;
import com.mmt.shubh.expensemanager.R;
import com.mmt.shubh.expensemanager.dagger.MainComponent;
import com.mmt.shubh.expensemanager.database.content.ExpenseBook;
import com.mmt.shubh.expensemanager.ui.activity.AddExpenseBookActivity;
import com.mmt.shubh.expensemanager.ui.activity.ExpenseBookDetailActivity;
import com.mmt.shubh.expensemanager.ui.adapters.ExpenseBookListAdapter;
import com.mmt.shubh.expensemanager.ui.adapters.base.ListRecyclerView;
import com.mmt.shubh.expensemanager.ui.component.DaggerExpenseBookActivityComponent;
import com.mmt.shubh.expensemanager.ui.component.ExpenseBookActivityComponent;
import com.mmt.shubh.expensemanager.ui.fragment.base.RecyclerViewFragment;
import com.mmt.shubh.expensemanager.ui.module.ExpenseBookListFragmentModule;
import com.mmt.shubh.expensemanager.ui.mvp.MVPLCEView;
import com.mmt.shubh.expensemanager.ui.presenters.ExpenseBookListPresenter;

import java.util.ArrayList;
import java.util.List;

public class ExpenseBookListFragment extends RecyclerViewFragment<MVPLCEView<List<ExpenseBook>>, ExpenseBookListPresenter>
        implements MVPLCEView<List<ExpenseBook>> {

    public static final String TAG = "ExpenseBookListFragment";

    private ExpenseBookListAdapter mExpenseBookListAdapter;

    public ExpenseBookListFragment() {
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_existing_expense_book;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData(false);
        mExpenseBookListAdapter = new ExpenseBookListAdapter(new ArrayList<ExpenseBook>());
        ListRecyclerView recyclerView = (ListRecyclerView) mRecyclerView;
        recyclerView.setOnItemClickListener(mItemClickListener);
    }

    private ListRecyclerView.OnItemClickListener mItemClickListener = new ListRecyclerView.OnItemClickListener() {
        @Override
        public boolean onItemClick(RecyclerView parent, View view, int position, long id) {
            installExpenseBookDetail(position);
            return true;
        }
    };

    private void installExpenseBookDetail(int position) {
        ExpenseBook expenseBook = mExpenseBookListAdapter.getItem(position);

        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.KEY_EXPENSE_BOOK, expenseBook);
        Intent intent = new Intent(getActivity(), ExpenseBookDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_expense_book, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_expense) {
            Intent intent = new Intent(getActivity().getApplicationContext(),AddExpenseBookActivity.class);
            startActivityForResult(intent, 123);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        showRecylerView(false);
    }

    @Override
    public void showContent() {
        showRecylerView(true);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        // TODO: 9/11/2015 handle this case if required
    }

    @Override
    public void setData(List<ExpenseBook> data) {
        if (data == null || data.size() <= 0) {
            showRecylerView(false);
        } else {
            mExpenseBookListAdapter.addData(data);
            showRecylerView(true);
            setAdapter(mExpenseBookListAdapter);
        }
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getLoaderManager().restartLoader(12345, null, mPresenter).forceLoad();
    }


    @Override
    protected void injectDependencies(MainComponent mainComponent) {
        super.injectDependencies(mainComponent);
        ExpenseBookActivityComponent component = DaggerExpenseBookActivityComponent.builder()
                .expenseBookListFragmentModule(new ExpenseBookListFragmentModule())
                .mainComponent(mainComponent)
                .build();
        component.inject(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loadData(false);
    }
}