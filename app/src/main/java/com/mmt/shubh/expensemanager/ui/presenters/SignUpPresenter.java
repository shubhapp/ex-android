package com.mmt.shubh.expensemanager.ui.presenters;

import android.content.Context;
import android.text.TextUtils;

import com.mmt.shubh.expensemanager.R;
import com.mmt.shubh.expensemanager.debug.Logger;
import com.mmt.shubh.expensemanager.ui.models.SigUpModelImpl;
import com.mmt.shubh.expensemanager.ui.models.api.ISignUpModel;
import com.mmt.shubh.expensemanager.ui.mvp.MVPAbstractPresenter;
import com.mmt.shubh.expensemanager.ui.mvp.MVPPresenter;
import com.mmt.shubh.expensemanager.ui.views.ISignUpViews;

/**
 * Created by Subham Tyagi,
 * on 17/Aug/2015,
 * 7:41 AM
 * TODO:Add class comment.
 */
public class SignUpPresenter extends MVPAbstractPresenter<ISignUpViews> implements MVPPresenter<ISignUpViews>,  ISignUpModel.SignUpModelCallback {

    private final String TAG = getClass().getName();

    private ISignUpModel mSignUpModel;


    private Context mContext;

    public SignUpPresenter(Context context) {
        mContext = context;
        mSignUpModel = new SigUpModelImpl(context, this);
    }

    public void userSignUp(String fullName, String emailAddress, String password, String mobileNo) {
        Logger.debug(TAG, "Manually registering user");
        getView().showProgress();
        if (!isEmpty(fullName, emailAddress, password, mobileNo)) {
            mSignUpModel.registerUser(fullName, emailAddress, password, mobileNo);
        }else {
            getView().hideProgress();
        }
    }

    private boolean isEmpty(String fullName, String emailAddress, String password, String mobileNo) {
        if (isViewAttached()) {
            boolean isEmpty = false;
            if (TextUtils.isEmpty(fullName)) {
                isEmpty = true;
                getView().setFullNameError(R.string.error_field_required);
            }
            if (TextUtils.isEmpty(emailAddress)) {
                isEmpty = true;
                getView().setEmailAddressError(R.string.error_field_required);
            }
            if (TextUtils.isEmpty(password)) {
                isEmpty = true;
                getView().setPasswordError(R.string.error_field_required);
            }
            if (TextUtils.isEmpty(fullName)) {
                isEmpty = true;
                getView().setEmailAddressError(R.string.error_field_required);
            }
            return isEmpty;
        }
        return false;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }


    @Override
    public void onSuccess() {
        Logger.debug(TAG, "launching home activity");
        getView().hideProgress();
        getView().navigateToHome();
    }

}
