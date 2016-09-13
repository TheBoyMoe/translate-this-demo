package com.example.translatethis.main.discover.presenter;


import android.content.Context;

import com.example.translatethis.main.discover.MainMVP;
import com.example.translatethis.model.Item;

public class DiscoverPresenter implements MainMVP.ProvidedPresenterOps, MainMVP.RequiredPresenterOps{

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
    public Context getAppContext() {
        return null;
    }

    @Override
    public Context getActivityContext() {
        return null;
    }
    // END





}
