package com.example.translatethis.main.discover.presenter;


import android.content.Context;
import android.util.Log;

import com.example.translatethis.R;
import com.example.translatethis.common.Constants;
import com.example.translatethis.common.SharedPrefsUtils;
import com.example.translatethis.common.Utils;
import com.example.translatethis.common.VoicesUtils;
import com.example.translatethis.main.discover.MainMVP;
import com.example.translatethis.main.discover.task.TranslationTask;
import com.example.translatethis.main.discover.task.TranslationTaskListener;
import com.example.translatethis.model.Item;
import com.microsoft.projectoxford.speechrecognition.ISpeechRecognitionServerEvents;
import com.microsoft.projectoxford.speechrecognition.MicrophoneRecognitionClient;
import com.microsoft.projectoxford.speechrecognition.RecognitionResult;
import com.microsoft.projectoxford.speechrecognition.SpeechRecognitionMode;
import com.microsoft.projectoxford.speechrecognition.SpeechRecognitionServiceFactory;
import com.microsoft.speech.tts.Synthesizer;
import com.microsoft.speech.tts.Voice;

import java.lang.ref.WeakReference;
import java.util.Locale;

import timber.log.Timber;

public class DiscoverPresenter implements
        MainMVP.ProvidedPresenterOps,
        MainMVP.RequiredPresenterOps,
        ISpeechRecognitionServerEvents,
        TranslationTaskListener {

    // cache references to the Presenter and Model
    private WeakReference<MainMVP.RequiredViewOps> mView;
    private MainMVP.ProvidedModelOps mModel;

    private MicrophoneRecognitionClient mClient = null;
    private SpeechRecognitionMode mSpeechMode = SpeechRecognitionMode.ShortPhrase;
    private String mLanguageCode = Constants.LANGUAGE_CODES[0]; // default English-GB
    private String mKey = Constants.PRIMARY_SUBSCRIPTION_KEY;
    private boolean mHasStartedRecording = false;
    private boolean mHasOptionsChanged = false;
    private String mTranslatedText;

    // constructor
    public DiscoverPresenter(MainMVP.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    protected MainMVP.RequiredViewOps getView() {
        if (mView != null) {
            return mView.get();
        } else { // FIXME NPE when device is rotated due to view being unavailable
            throw new NullPointerException("View is unavailable");
        }
    }

    public void setModel(MainMVP.ProvidedModelOps model) {
        mModel = model;
    }

    // impl contract methods
    @Override
    public void hasLanguageOptionsChanged(boolean value) {
        mHasOptionsChanged = value;
    }

    @Override
    public void recordSpeech() {
        if (Utils.isClientConnected(getActivityContext())) {
            initRecording(); // initialize speech service
            // start the recording as long as the client exists and is not already recording
            // recording stops automatically after 15sec, using ShortPhrase speech mode
            if (mClient != null && !mHasStartedRecording) {
                mClient.startMicAndRecognition();
                getView().isServiceRunning(true); // set record light 'on'
            } else {
                getView().showMessage(getActivityContext().getString(R.string.speech_service_busy));
            }
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
    public void translateResult(String fromText) {
        if (Utils.isClientConnected(getActivityContext())) {
            // execute the translation passing in a reference to the listener
            new TranslationTask(this, getActivityContext(), fromText).execute();
        } else {
            getView().isClientConnected(false);
        }
    }

    @Override
    public void translationResult(String translatedText) {
        // forward the translated text to the fragment to be displayed
        // FIXME translated text lost on device rotation
        getView().updateToTextField(String.format(Locale.ENGLISH, "RESULT:\n%s", translatedText));
        mTranslatedText = translatedText;
    }

    @Override
    public void playResult() {
        if (mTranslatedText != null && !mTranslatedText.isEmpty()) {
            String spokenLanguage = Constants.LANGUAGE_CODES[SharedPrefsUtils.getToLanguage(getActivityContext())];
            Synthesizer synthesizer = new Synthesizer(getActivityContext().getString(R.string.app_name),
                    Constants.PRIMARY_SUBSCRIPTION_KEY);
            Voice voice = VoicesUtils.getVoice(spokenLanguage, 0);
            if (voice!= null) {
                synthesizer.SetVoice(voice, voice);
                synthesizer.SpeakToAudio(mTranslatedText);
            }
        } else {
            getView().showMessage(getActivityContext().getString(R.string.translation_not_available));
        }
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

    // impl of MS Speech Recognition Server Event methods
    @Override
    public void onPartialResponseReceived(final String response) {
        if (getView() != null && getActivityContext() != null) {
            getView().updateFromTextField(String.format(Locale.ENGLISH, "%s%s",
                    getActivityContext().getString(R.string.partial_text_result), response));
        }
    }

    @Override
    public void onFinalResponseReceived(final RecognitionResult recognitionResult) {
        if (mClient != null) {
            mClient.endMicAndRecognition();
        }
        getView().isServiceRunning(false); // set record icon to 'off'
        getView().updateFromSmallText("");
        if (recognitionResult.Results.length > 0) {
            String result = recognitionResult.Results[recognitionResult.Results.length -1].DisplayText;
            getView().updateFromTextField(String.format(Locale.ENGLISH, "RESULT:\n%s", result));
        } else {
            getView().updateFromTextField(getActivityContext().getString(R.string.unknown_error_has_occurred));
        }
    }

    @Override
    public void onIntentReceived(String s) {
        // no-op
    }

    @Override
    public void onError(final int errorCode, final String response) {
        getView().isServiceRunning(false);
        getView().showMessage(getActivityContext().getString(R.string.server_error));
        getView().updateFromTextField(String.format(Locale.ENGLISH, "Error: %d, %s", errorCode, response));
        getView().updateFromSmallText("");
        mClient = null; // force re-initialization of client
        mKey = Constants.SECONDARY_SUBSCRIPTION_KEY;
    }

    @Override
    public void onAudioEvent(final boolean isRecording) {
        mHasStartedRecording = isRecording;
        if (!isRecording) {
            if (mClient != null) {
                mClient.endMicAndRecognition();
            }
            getView().isServiceRunning(false);
            getView().updateFromSmallText(
                    getActivityContext().getString(R.string.speech_service_still_processing));
        } else {
            getView().isServiceRunning(true);
            getView().updateFromSmallText(getActivityContext().getString(R.string.speech_service_stops_automatically));
        }
    }
    // END

    // Helper methods
    private void initRecording() {
        if (mHasOptionsChanged || mClient == null) {
            getView().showMessage("Connecting to server");
            getView().updateFromTextField("");
            if (mKey.equals(Constants.PRIMARY_SUBSCRIPTION_KEY)) {
                Log.i(Constants.LOG_TAG, "Connecting with " + Constants.PRIMARY_SUBSCRIPTION_KEY);
            } else {
                Log.i(Constants.LOG_TAG, "Connecting with " + Constants.SECONDARY_SUBSCRIPTION_KEY);
            }
            int fromLanguageIndex = SharedPrefsUtils.getFromLanguage(getActivityContext());
            String fromLanguage = Constants.LANGUAGE_CODES[fromLanguageIndex];
            mClient = SpeechRecognitionServiceFactory.createMicrophoneClient(mSpeechMode, fromLanguage, this, mKey);
            getView().updateFromTextField(getActivityContext().getString(R.string.recording_speech));
            mHasOptionsChanged = false;
        }
    }


}
