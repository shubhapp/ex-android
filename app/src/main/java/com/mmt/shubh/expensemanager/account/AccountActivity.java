package com.mmt.shubh.expensemanager.account;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.mmt.shubh.expensemanager.R;
import com.mmt.shubh.expensemanager.core.base.ToolBarActivity2;
import com.mmt.shubh.expensemanager.core.dagger.component.ConfigPersistentComponent;
import com.mmt.shubh.expensemanager.core.dagger.module.ActivityModule;
import com.mmt.shubh.expensemanager.core.mvp.lce.MVPLCEView;
import com.mmt.shubh.expensemanager.database.content.Account;

import java.util.List;

import butterknife.Bind;


public class AccountActivity extends ToolBarActivity2<AccountActivityPresenter> implements MVPLCEView<List<Account>> {

    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;

    @Bind(R.id.viewPager)
    ViewPager mViewPager;

    AccountPagerAdapter mAccountPagerAdapter;

    @Override
    protected void injectDependencies(ConfigPersistentComponent component) {
        component.activityComponent(new ActivityModule(this))
                .inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toggleHomeBackButton(true);
        setTitle(R.string.title_activity_account);
        mPresenter.attachView(this);
        mPresenter.loadAllAccounts();
    }

    @Override
    protected int getLayout() {
        return R.layout.account_activity;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                break;
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {

    }

    @Override
    public void setData(List<Account> data) {
        mAccountPagerAdapter = new AccountPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAccountPagerAdapter);
        mAccountPagerAdapter.addData(data);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        mPresenter.loadAllAccounts();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView(false);
    }
}
