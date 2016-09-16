package com.example.translatethis.main.discover.presenter;


import android.content.Context;

import com.example.translatethis.common.Constants;
import com.example.translatethis.common.Utils;
import com.example.translatethis.main.discover.MainMVP;
import com.example.translatethis.model.Item;
import com.microsoft.projectoxford.speechrecognition.ISpeechRecognitionServerEvents;
import com.microsoft.projectoxford.speechrecognition.MicrophoneRecognitionClient;
import com.microsoft.projectoxford.speechrecognition.RecognitionResult;
import com.microsoft.projectoxford.speechrecognition.SpeechRecognitionMode;

import java.lang.ref.WeakReference;

import timber.log.Timber;

public class DiscoverPresenter implements
        MainMVP.ProvidedPresenterOps,
        MainMVP.RequiredPresenterOps,
        ISpeechRecognitionServerEvents {

    // cache references to the Presenter and Model
    private WeakReference<MainMVP.RequiredViewOps> mView;
    private MainMVP.ProvidedModelOps mModel;

    private MicrophoneRecognitionClient mClient = null;
    private SpeechRecognitionMode mSpeechMode = SpeechRecognitionMode.ShortPhrase;
    private String mLanguageCode = Constants.LANGUAGE_CODES[0]; // default English-GB
    private String mKey = Constants.PRIMARY_SUBSCRIPTION_KEY;
    private boolean mHasStartedRecording;

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
        if (Utils.isClientConnected(getActivityContext())) {
            getView().updateResultTextField("Hello world!");
            getView().recordingStarted();
        } else {
            getView().isClientConnected(false);
        }
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

    // impl of MS Speech Recognition Service
    @Override
    public void onPartialResponseReceived(String s) {

    }

    @Override
    public void onFinalResponseReceived(RecognitionResult recognitionResult) {

    }

    @Override
    public void onIntentReceived(String s) {

    }

    @Override
    public void onError(int i, String s) {

    }

    @Override
    public void onAudioEvent(boolean b) {

    }
    // END





}
