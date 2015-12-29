/*
 *  Copyright 2015 Ryeeeeee
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.ryeeeeee.gitx.module.oauth;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ryeeeeee.gitx.Config;
import com.ryeeeeee.gitx.R;
import com.ryeeeeee.gitx.databinding.OAuthActivityBinding;
import com.ryeeeeee.gitx.ui.base.BaseActivity;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ryeeeeee on 12/19/15.
 */
public class OAuthActivity extends BaseActivity {
    private static final String TAG = "OAuthActivity";
    private OAuthActivityBinding mBinding;

    @SuppressLint("SetJavaScriptEnabled")
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_oauth);

        WebView authView = mBinding.authView;
        authView.getSettings().setJavaScriptEnabled(true);
        authView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        authView.setWebViewClient(new AuthWebViewClient());
        authView.loadUrl("https://github.com/login/oauth/authorize?scope=user:email&client_id=" + Config.CLIENT_ID);
    }

    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mBinding.authView.canGoBack()) {
            mBinding.authView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void notifyResultAndFinish(boolean success) {
        if (success) {
            setResult(RESULT_OK);
        } else {
            setResult(RESULT_CANCELED);
        }
        finish();
    }

    private class AuthWebViewClient extends WebViewClient {
        @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG, "shouldOverrideUrlLoading() called url = [" + url + "]");
            if (url.startsWith(Config.REDIRECT_URL)) {
                String httpParams = url.split("\\?")[1];
                String[] bundles = httpParams.split("=");
                if (bundles[0].equals("code")) {
                    // get authorization code
                    String code = bundles[1];
                    Log.i(TAG, "authorization code:" + code);
                    Observable.just(code)
                            .subscribeOn(Schedulers.newThread())
                            .map(c -> OAuth.exchangeToken(c))
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<Token>() {
                                @Override public void onCompleted() {}

                                @Override public void onError(Throwable e) {
                                    notifyResultAndFinish(false);
                                }

                                @Override public void onNext(Token token) {
                                    OAuth.updateLocalAccessToken(OAuthActivity.this, token);
                                    notifyResultAndFinish(true);
                                }
                            });
                }
                view.stopLoading();
            }
            // return false means the current WebView handles the url.
            // return true means the host application handles the url.
            return false;
        }
    }
}
