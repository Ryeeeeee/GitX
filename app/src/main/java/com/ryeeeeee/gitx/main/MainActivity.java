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

package com.ryeeeeee.gitx.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ryeeeeee.gitx.App;
import com.ryeeeeee.gitx.R;
import com.ryeeeeee.gitx.databinding.MainActivityBinding;
import com.ryeeeeee.gitx.main.adapter.MainPagerAdapter;
import com.ryeeeeee.gitx.oauth.OAuth;
import com.ryeeeeee.gitx.oauth.OAuthActivity;
import com.ryeeeeee.gitx.ui.base.BaseActivity;
import com.ryeeeeee.gitx.user.UserDetailActivity;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ActivityFragment.OnFragmentInteractionListener {

    private static final int OAUTH_REQUEST_CODE = 1;

    Toolbar mToolbar;
    TabLayout mTabLayout;
    ViewPager mViewpager;
    FloatingActionButton mFab;
    NavigationView mNavView;
    DrawerLayout mDrawerLayout;
    ImageView mAvatarView;

    private MainActivityBinding mBinding;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        onBindView();

        initToolbar();
        initTabLayout();
        mBinding.navView.setNavigationItemSelectedListener(this);

        if (!OAuth.checkLocalAccessToken(this)) {
            startActivityForResult(new Intent(this, OAuthActivity.class), OAUTH_REQUEST_CODE);
        }

        mAvatarView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserDetailActivity.class));
            }
        });
    }

    @Override public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case OAUTH_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(App.getContext(), "OAuth success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(App.getContext(), "OAuth Failed", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void onBindView() {
        View headerView = mBinding.navView.getHeaderView(0);
        mAvatarView = (ImageView) headerView.findViewById(R.id.avatar_view);
    }

    private void initToolbar() {
        Toolbar toolbar = mBinding.appBar.toolbar;
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mBinding.drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBinding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void initTabLayout() {
        TabLayout tabLayout = mBinding.appBar.tabLayout;
        ViewPager viewpager = mBinding.appBar.viewpager;
        viewpager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
