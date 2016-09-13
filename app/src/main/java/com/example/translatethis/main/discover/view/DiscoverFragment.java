package com.example.translatethis.main.discover.view;

import android.content.Context;

import com.example.translatethis.main.ContractFragment;
import com.example.translatethis.main.discover.MainMVP;


public class DiscoverFragment extends ContractFragment<DiscoverFragment.Contract>
        implements MainMVP.RequiredViewOps{

    // impl RequiredViewOps contract
    @Override
    public Context getAppContext() {
        return null;
    }

    @Override
    public Context getActivityContext() {
        return null;
    }
    // END

    public interface Contract {
        // communicate upto the hosting activity

    }

    public DiscoverFragment() {}

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

}
