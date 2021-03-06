package com.km2labs.android.spendview.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.common.SignInButton;
import com.km2labs.shubh.expensemanager.R;
import com.km2labs.android.spendview.core.base.ToolBarActivity2;
import com.km2labs.android.spendview.core.dagger.component.ConfigPersistentComponent;
import com.km2labs.android.spendview.core.dagger.module.ActivityModule;
import com.km2labs.android.spendview.home.HomeActivity;

import butterknife.BindView;


public class LoginActivity extends ToolBarActivity2<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.plus_sign_in_button)
    SignInButton mPlusSignInButton;

    @BindView(R.id.facebook_login_button)
    AppCompatButton mFacebookLoginButton;

    @BindView(R.id.social_container)
    LinearLayout mSocialContainer;

    @BindView(R.id.login_progress)
    View mProgressView;


    @Override
    protected void injectDependencies(ConfigPersistentComponent component) {
        component.activityComponent(new ActivityModule(this)).inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeToolbar();
        getSupportActionBar().setElevation(0);
        mPresenter.setupFacebookLogin(mFacebookLoginButton, this);
        mPresenter.setupGoogleLogin(mPlusSignInButton, this);
        mPresenter.attachView(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;

    }

    private void showBackButton(boolean value) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(value);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            showSocialLogin();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSocialLogin() {

        mSocialContainer.setVisibility(View.VISIBLE);
        showBackButton(false);
    }

    @Override
    public void showProgress() {
        showProgress(true);
    }

    @Override
    public void hideProgress() {
        showProgress(false);
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showError(int messageRes) {

    }

    @Override
    public void showAskMobileScreen() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mSocialContainer.setVisibility(show ? View.GONE : View.VISIBLE);
        mSocialContainer.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mSocialContainer.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mSocialContainer.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

}

