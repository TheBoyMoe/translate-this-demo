package com.example.translatethis.main;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.translatethis.R;
import com.example.translatethis.common.Utils;
import com.example.translatethis.custom.ViewPagerAdapter;
import com.example.translatethis.main.discover.view.DiscoverFragment;
import com.example.translatethis.main.review.view.ReviewFragment;

public class MainActivity extends AppCompatActivity implements
        ReviewFragment.Contract, DiscoverFragment.Contract{

    // impl of DiscoverFragment contract
    @Override
    public void showDiscoverMessage(String message) {
        // Utils.showSnackbar(mLayout, message);
        Utils.showToast(this, message);
    }

    // impl ReviewFragment contract
    @Override
    public void showReviewMessage(String message) {
        Utils.showSnackbar(mLayout, message);
    }

    private CoordinatorLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        setupView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Utils.showSnackbar(mLayout, "Clicked on settings");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(DiscoverFragment.newInstance(), "Discover");
        adapter.addFragment(ReviewFragment.newInstance(), "Review");
        viewPager.setAdapter(adapter);
    }

    private void setupView() {
        mLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }


}
