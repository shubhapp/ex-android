package com.mmt.shubh.expensemanager.ui.login;

public interface LoginCallback {

    /**
     * Called when the PlusClient is successfully connected.
     */
    void onSignInComplete(BaseLoginHelper.Type type);

    void onSignInFailed(String message);

    void onSignInCanceled();

    /**
     * Called when the is blocking the UI.  If you have a progress bar widget,
     * this tells you when to show or hide it.
     */
    void onBlockingUI(boolean show);

}