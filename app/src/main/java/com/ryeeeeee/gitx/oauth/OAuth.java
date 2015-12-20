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

package com.ryeeeeee.gitx.oauth;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;

import com.ryeeeeee.gitx.Config;

import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Ryeeeeee on 12/20/15.
 */
public class OAuth {

    private static final String TAG = "OAuth";

    private static final String ACCESS_TOKEN = "access_token";
    private static final String SCOPE = "scope";
    private static final String TOKEN_TYPE = "token_type";


    public static Token exchangeToken(String code) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OAuthApi api = retrofit.create(OAuthApi.class);
        Call<Token> call = api.exchangeAccessToken(Config.CLIENT_ID, Config.CLIENT_SECRET, code, "json");
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateLocalAccessToken(Context context, Token token) {
        Log.d(TAG, "updateLocalAccessToken() called with: " + "token = [" + token + "]");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(ACCESS_TOKEN, token.getAccessToken());
        edit.putString(SCOPE, token.getScope());
        edit.putString(TOKEN_TYPE, token.getType());
        edit.apply();
    }

    public static boolean checkLocalAccessToken(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(ACCESS_TOKEN, null) != null;
    }
}
