package com.km2labs.android.spendview.member.detail;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.km2labs.android.spendview.core.view.SimpleImageView;
import com.km2labs.android.spendview.expense.ExpenseListViewModel;
import com.km2labs.android.spendview.utils.Constants;
import com.km2labs.shubh.expensemanager.R;
import com.km2labs.android.spendview.core.base.ToolBarActivity2;
import com.km2labs.android.spendview.core.dagger.component.ConfigPersistentComponent;
import com.km2labs.android.spendview.core.dagger.module.ActivityModule;
import com.km2labs.android.spendview.core.view.CircleImageView;
import com.km2labs.android.spendview.core.view.PaletteTransformation;
import com.km2labs.android.spendview.database.content.Member;
import com.squareup.picasso.Callback;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Subham Tyagi,
 * on 22/Jun/2015,
 * 5:55 PM
 * TODO:Add class comment.
 */
public class MemberDetailActivity extends ToolBarActivity2<MemberDetailPresenter> implements MemberDetailContract.View {

    @BindView(R.id.backdrop)
    SimpleImageView mImageView;

    @BindView(R.id.progress)
    ProgressBar mImageProgressBar;

    @BindView(R.id.profile_image)
    CircleImageView mProfileImageView;

    @BindView(R.id.member_name)
    TextView mMemberNameTextView;

    private Member mMember;

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        toggleHomeBackButton(true);
        mPresenter.attachView(this);
        parseIntent();

    }

    @Override
    protected void injectDependencies(ConfigPersistentComponent component) {
        component.activityComponent(new ActivityModule(this)).inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_member_detail;
    }

    private void parseIntent() {
        mMember = Parcels.unwrap(getIntent().getParcelableExtra(Constants.EXTRA_MEMBER));
        mMemberNameTextView.setText(mMember.getMemberName());
        mProfileImageView.loadImage(mMember.getProfilePhotoUrl());
        mImageView.loadImage(mMember.getCoverPhotoUrl(), PaletteTransformation.instance(), new Callback() {
            @Override
            public void onSuccess() {
                mImageProgressBar.setVisibility(View.GONE);
                Bitmap bitmap = ((BitmapDrawable) mImageView.getDrawable()).getBitmap(); // Ew!
                Palette palette = PaletteTransformation.getPalette(bitmap);
                int color = palette.getLightMutedColor(getResources().getColor(android.R.color.black));
                mMemberNameTextView.setTextColor(color);
            }

            @Override
            public void onError() {
                mImageProgressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

       /* mMemberModel.loadAllExpneseBooksByMemberId(mMember.getId()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(d -> {
                    if (d.size() < 0) {
                        mExpenseCardView.setVisibility(View.GONE);
                    } else {
                        mMemberCountTextView.setText(String.valueOf(d.size()));
                        mExpenseBookListView.setMode(ExpenseBookListView.MODE_MEMBER);
                        mExpenseBookListView.addData(d);
                    }
                }, e -> {
                    Timber.e("Unable to load expense for member %s", e.getMessage());
                });
*/
        /*mMemberModel.getAllSharedExpense(mMember.getId(), UserSettings.getInstance().getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(d -> {
                    calculateTotalExpense(d);
                    if (d != null && d.size() > 0) {
                        mExpenseListView.setMode(ExpenseListView.MODE_MEMBER);
                        mExpenseListView.addData(d);
                    } else {
                        mExpenseListView.showEmptyMessage();
                    }
                }, e -> {
                    Timber.e("Unable to load expense for member %s", e.getMessage());
                });*/
      /*  mMemberModel.getMemberExpenses(mMember.getId(), UserSettings.getInstance().getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(d -> {
                    double otherRemainingAmount = 0;
                    final double[] myRemainingAmount = {0};
                    for (MemberExpense memberExpense : d) {
                        otherRemainingAmount += memberExpense.getBalanceAmount();
                    }
                    final double finalOtherRemainingAmount = otherRemainingAmount;
                    mMemberModel.getMemberExpenses(mMember.getId(), UserSettings.getInstance().getUserId())
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io())
                            .subscribe(d2 -> {
                                for (MemberExpense memberExpense : d) {
                                    myRemainingAmount[0] += memberExpense.getBalanceAmount();
                                }
                                double diff = myRemainingAmount[0] - finalOtherRemainingAmount;
                                new Handler(Looper.getMainLooper()).post(() ->
                                        mBalanceAmountTextView.setText(StringsUtils.getLocalisedAmountString(diff)));
                            }, e2 -> {
                                Timber.e(e2.getMessage());
                            });
                }, e -> {
                    Timber.e(e.getMessage());
                });*/
    }

    private void calculateTotalExpense(List<ExpenseListViewModel> d) {
        double expenseAmount = 0;
        for (ExpenseListViewModel expenseListViewModel : d) {
            expenseAmount += expenseListViewModel.getExpenseAmount();
        }
        //mTotalExpenseTextView.setText(StringsUtils.getLocalisedAmountString(expenseAmount));
    }
}
