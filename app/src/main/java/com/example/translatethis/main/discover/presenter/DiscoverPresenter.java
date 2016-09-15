package com.example.translatethis.main.discover.presenter;


import android.content.Context;

import com.example.translatethis.common.Constants;
import com.example.translatethis.main.discover.MainMVP;
import com.example.translatethis.model.Item;

import java.lang.ref.WeakReference;

import timber.log.Timber;

public class DiscoverPresenter implements MainMVP.ProvidedPresenterOps, MainMVP.RequiredPresenterOps{

    // cache references to the Presenter and Model
    private WeakReference<MainMVP.RequiredViewOps> mView;
    private MainMVP.ProvidedModelOps mModel;

    // constructor
    public DiscoverPresenter(MainMVP.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    protected MainMVP.RequiredViewOps getView() {
        if (mView != null) {
            return mView.get();
        } else {
            throw new NullPointerException("View is unavailable");
        }
    }

    public void setModel(MainMVP.ProvidedModelOps model) {
        mModel = model;
    }

    // impl contract methods
    @Override
    public void recordSpeech() {
        // TODO
        getView().showMessage("Speech to Text");
    }

    @Override
    public void editResult() {
        // TODO
        getView().showMessage("Edit the resultant text");
    }

    @Override
    public void translateResult() {
        // TODO
        getView().showMessage("Translate the text to chosen language");
    }

    @Override
    public void playResult(String filePath) {
        // TODO
        getView().showMessage("Play the translated text");
    }

    @Override
    public int saveResult(Item item) {
        // TODO
        getView().showMessage("Save the phrase and translation");
        return 0;
    }

    @Override
    public void setView(MainMVP.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        mView = null;
        if (mModel != null) {
            mModel.onDestroy(isChangingConfiguration);
            if (isChangingConfiguration) mModel = null;
        }
    }

    @Override
    public Context getAppContext() {
        try {
            return getView().getAppContext();
        } catch (NullPointerException e) {
            Timber.e("%s %s", Constants.LOG_TAG, e.getMessage());
            return null;
        }
    }

    @Override
    public Context getActivityContext() {
        try {
            return getView().getActivityContext();
        } catch (NullPointerException e) {
            Timber.e("%s %s", Constants.LOG_TAG, e.getMessage());
            return null;
        }
    }
    // END





}
