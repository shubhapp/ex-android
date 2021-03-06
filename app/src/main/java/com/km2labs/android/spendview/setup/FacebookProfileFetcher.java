package com.km2labs.android.spendview.setup;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.km2labs.android.spendview.database.content.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Subham Tyagi,
 * on 25/Oct/2015,
 * 5:13 PM
 * TODO:Add class comment.
 */
public class FacebookProfileFetcher extends ProfileFetcher {

    private static final String TAG = FacebookProfileFetcher.class.getSimpleName();

    @Override
    public UserInfo fetchUserAccountDetails() {

        Profile profile = Profile.getCurrentProfile();
        UserInfo userInfo = new UserInfo();

        userInfo.setDisplayName(profile.getName());
        userInfo.setProfilePhotoUrl(profile.getProfilePictureUri(512, 512).toString());
        userInfo.setEmailAddress("");
        userInfo.setStatus(UserInfo.Status.ACTIVE);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> new GraphRequest(
                AccessToken.getCurrentAccessToken(), "/" + Profile.getCurrentProfile().getId(),
                null, HttpMethod.GET, response -> {
            Profile profile1 = Profile.getCurrentProfile();
            Log.d(TAG, response.toString());

            try {
                JSONObject jsonObject = response.getJSONObject();
                String emailID = jsonObject.getString("email");
                userInfo.setEmailAddress(emailID);
                userInfo.setProfilePhotoUrl(profile1.getProfilePictureUri(512, 512).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).executeAndWait());
        return userInfo;
    }
}
