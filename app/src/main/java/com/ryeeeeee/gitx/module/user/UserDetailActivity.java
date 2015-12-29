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

package com.ryeeeeee.gitx.module.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ryeeeeee.gitx.R;
import com.ryeeeeee.gitx.databinding.UserDetailBinding;
import com.ryeeeeee.gitx.ui.base.BaseActivity;

/**
 * Created by Ryeeeeee on 12/24/15.
 */
public class UserDetailActivity extends BaseActivity {
    private UserDetailBinding mBinding;
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail);
        onBindView();
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(mBinding.toolbar);
    }

    private void onBindView() {

    }
}
