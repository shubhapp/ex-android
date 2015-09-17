package com.mmt.shubh.expensemanager.ui.component;

import com.mmt.shubh.expensemanager.dagger.ActivityScope;
import com.mmt.shubh.expensemanager.dagger.MainComponent;
import com.mmt.shubh.expensemanager.ui.activity.CashActivity;
import com.mmt.shubh.expensemanager.ui.fragment.CashListFragment;
import com.mmt.shubh.expensemanager.ui.module.CashActivityModule;

import dagger.Component;

/**
 * Created by Subham Tyagi,
 * on 07/Sep/2015,
 * 2:29 PM
 * TODO:Add class comment.
 */
@ActivityScope
@Component(
        dependencies =
                {
                        MainComponent.class
                },
        modules = {
                CashActivityModule.class
        }
)
public interface CashActivityComponent {

    void inject(CashActivity cashActivity);

    void inject(CashListFragment cashListFragment);

}
