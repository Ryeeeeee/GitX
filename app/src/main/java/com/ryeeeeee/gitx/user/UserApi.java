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

package com.ryeeeeee.gitx.user;

import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Path;
import rx.Observable;

/**
 * https://developer.github.com/v3/users/
 *
 * Created by Ryeeeeee on 12/23/15.
 */
public interface UserApi {

    @Headers("Accept: application/json")
    @GET("/user")
    Observable<User> getAuthedUser(@Header("Authorization") String token);

    @Headers("Accept: application/json")
    @GET("/users/{username}")
    Observable<User> getSingleUser(@Header("Authorization") String token,
                                   @Path("username") String username);

}
