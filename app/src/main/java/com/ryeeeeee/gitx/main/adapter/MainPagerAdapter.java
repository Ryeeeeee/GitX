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

package com.ryeeeeee.gitx.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ryeeeeee.gitx.main.ActivityFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryeeeeee on 12/28/15.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mTitles = new ArrayList<>();
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);

        mFragments.add(ActivityFragment.newInstance("p11", "p12"));
        mFragments.add(ActivityFragment.newInstance("p21", "p22"));
        mFragments.add(ActivityFragment.newInstance("p31", "p32"));

        mTitles.add("ACTIVITIES");
        mTitles.add("FIND");
        mTitles.add("REPOS");
    }

    @Override public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override public int getCount() {
        return mFragments.size();
    }

    @Override public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
