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

package com.ryeeeeee.gitx.module.activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Ryeeeeee on 12/29/15.
 */
public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.BindingHolder> {
    private List<UserActivity> mActivities;

    public ActivityAdapter(List<UserActivity> activities) {
        mActivities = activities;

        mActivities.add(new UserActivity());
        mActivities.add(new UserActivity());
        mActivities.add(new UserActivity());
        mActivities.add(new UserActivity());
        mActivities.add(new UserActivity());
        mActivities.add(new UserActivity());
        mActivities.add(new UserActivity());
        mActivities.add(new UserActivity());
        mActivities.add(new UserActivity());
        mActivities.add(new UserActivity());
        mActivities.add(new UserActivity());
        mActivities.add(new UserActivity());
    }

    @Override public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override public void onBindViewHolder(BindingHolder holder, int position) {

    }

    @Override public int getItemCount() {
        return 0;
    }

    class BindingHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
