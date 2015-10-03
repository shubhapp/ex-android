package com.mmt.shubh.expensemanager.ui.module;

import android.content.Context;

import com.mmt.shubh.expensemanager.ViewStateQualifier;
import com.mmt.shubh.expensemanager.dagger.ActivityScope;
import com.mmt.shubh.expensemanager.database.api.MemberDataAdapter;
import com.mmt.shubh.expensemanager.database.content.Member;
import com.mmt.shubh.expensemanager.database.dataadapters.MemberSQLDataAdapter;
import com.mmt.shubh.expensemanager.ui.mvp.lce.LCEViewState;
import com.mmt.shubh.expensemanager.ui.mvp.lce.LCEViewStateImpl;
import com.mmt.shubh.expensemanager.ui.mvp.lce.MVPLCEView;
import com.mmt.shubh.expensemanager.ui.presenters.MemberListFragmentPresenter;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Subham Tyagi,
 * on 02/Oct/2015,
 * 7:26 PM
 * TODO:Add class comment.
 */
@Module
public class MemberListFragmentModule {


    @Provides
    @ActivityScope
    MemberDataAdapter provideMemberDataAdapter(Context context) {
        return new MemberSQLDataAdapter(context);
    }

    @Provides
    @ActivityScope
    MemberListFragmentPresenter provideMemberListFragmentPresenter(Context context, MemberDataAdapter memberDataAdapter) {
        return new MemberListFragmentPresenter(context, memberDataAdapter);
    }

    @Provides
    @ActivityScope
    LCEViewState<List<Member>, MVPLCEView<List<Member>>> provideViewLCEViewState() {
        return new LCEViewStateImpl<>();
    }


}
