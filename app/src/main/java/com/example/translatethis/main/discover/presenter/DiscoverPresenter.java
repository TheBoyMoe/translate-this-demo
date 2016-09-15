package com.example.translatethis.main.discover.presenter;


import android.content.Context;

import com.example.translatethis.main.discover.MainMVP;
import com.example.translatethis.model.Item;

import java.lang.ref.WeakReference;

public class DiscoverPresenter implements MainMVP.ProvidedPresenterOps, MainMVP.RequiredPresenterOps{

    // cache references to the Presenter and Model
    private WeakReference<MainMVP.RequiredViewOps> mView;
    private MainMVP.ProvidedModelOps mModel;

    // constructor
    public DiscoverPresenter(MainMVP.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    protected MainMVP.RequiredViewOps getView() {
        // TODO
        return null;
    }

    public void setModel(MainMVP.ProvidedModelOps model) {
        mModel = model;
    }

    // impl contract methods
    @Override
    public void recordSpeech() {

    }

    @Override
    public void editResult() {

    }

    @Override
    public void translateResult() {

    }

    @Override
    public void playResult(String filePath) {

    }

    @Override
    public int saveResult(Item item) {
        return 0;
    }

    @Override
    public void setView(MainMVP.RequiredViewOps view) {

    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {

    }

    @Override
    public Context getAppContext() {
        return null;
    }

    @Override
    public Context getActivityContext() {
        return null;
    }
    // END





}
