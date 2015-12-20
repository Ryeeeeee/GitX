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

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ryeeeeee on 12/20/15.
 */
public class Token {
    @SerializedName("access_token") public String accessToken;
    @SerializedName("scope") public String scope;
    @SerializedName("token_type") public String type;

    public Token(String accessToken, String scope, String type) {
        this.accessToken = accessToken;
        this.scope = scope;
        this.type = type;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Token{" +
                "accessToken='" + accessToken + '\'' +
                ", scope='" + scope + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
