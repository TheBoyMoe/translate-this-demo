package com.example.translatethis.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import timber.log.Timber;

public class StateMaintainer {

    private static final String TAG = "StateMaintainer";
    private final WeakReference<FragmentManager> mFragmentManager;
    private final String mStateMaintainerTag;
    private StateMaintainerFragment mStateFragment;
    private boolean mIsRecreating;

    public StateMaintainer(FragmentManager fm, String stateMaintainerTag) {
        mFragmentManager = new WeakReference<FragmentManager>(fm);
        mStateMaintainerTag = stateMaintainerTag;
    }

    public boolean firstTimeIn() {
        try {
            // create the fragment responsible for saving state, true fragment just created
            mStateFragment = (StateMaintainerFragment) mFragmentManager.get().findFragmentByTag(mStateMaintainerTag);
            if (mStateFragment ==  null) { // first time in
                Timber.i("%s First time in, creating new state fragment: %s", TAG, mStateMaintainerTag);
                mStateFragment = new StateMaintainerFragment();
                mFragmentManager.get().beginTransaction()
                        .add(mStateFragment, mStateMaintainerTag)
                        .commit();
                mIsRecreating = false;
                return true;
            } else {
                Timber.i("%s Re-creating state fragment: %s", TAG, mStateMaintainerTag);
                mIsRecreating = true;
                return false;
            }

        } catch (NullPointerException e) {
            Timber.e("%s Error creating state fragment, %s", TAG, e.getMessage());
            return false;
        }
    }

    // helper methods to put/check/get state
    public boolean wasRecreated() {
        return mIsRecreating;
    }

    public void put(String key, Object object) {
        mStateFragment.put(key, object);
    }

    public void put(Object object) {
        mStateFragment.put(object);
    }

    public <T> T get(String key) {
        return mStateFragment.get(key);
    }

    public boolean hasKey(String key) {
        // check if the state has already been saved
        return mStateFragment.get(key) != null;
    }

    // fragment responsible for saving state via HashMap
    public static class StateMaintainerFragment extends Fragment {

        private HashMap<String, Object> mState = new HashMap<>();

        public StateMaintainerFragment() {}

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }

        public void put(String key, Object object) {
            mState.put(key, object);
        }

        public void put(Object object) {
            put(object.getClass().getName(), object);
        }

        @SuppressWarnings("unchecked")
        public <T> T get(String key) {
            return (T) mState.get(key);
        }

    }


}

