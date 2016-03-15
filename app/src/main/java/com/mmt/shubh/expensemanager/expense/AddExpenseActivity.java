package com.mmt.shubh.expensemanager.expense;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.mmt.shubh.expensemanager.R;
import com.mmt.shubh.expensemanager.base.ToolBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shubham on 11/19/15.
 */
public class AddExpenseActivity extends ToolBarActivity {

    @Bind(R.id.tabs)
    TabLayout tabLayout;

    @Bind(R.id.viewPager)
    ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        ButterKnife.bind(this);
        initializeToolbar();
        toggleHomeBackButton(true);
        setTitle(R.string.add_new_expense);
        setup();

    }

    private void setup() {
        AddExpenseFragmentAdapter adapter = new AddExpenseFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
