package com.example.translatethis.main.discover;


import android.content.Context;

import com.example.translatethis.model.Item;

public interface MainMVP {

    // implemented by the View, available to the Presenter
    // allowing the Presenter to Communicate back to the View
    interface RequiredViewOps {
        Context getAppContext();
        Context getActivityContext();
        void showMessage(String message);
        void updateResultTextField(String update);
        void updateTranslatedTextField(String result);
        void recordingStarted();
        void recordingComplete();
        void isClientConnected(boolean result);
    }

    // implemented by the Presenter, available to the View
    interface ProvidedPresenterOps {
        void recordSpeech();
        // void onCompletion();
        void editResult();
        void translateResult();
        void playResult(String filePath);
        int saveResult(Item item);
        void setView(RequiredViewOps view);
        void onDestroy(boolean isChangingConfiguration);
    }

    // implemented by the Presenter, available to the Model
    // allowing the Model to communicate back to the Presenter
    interface RequiredPresenterOps {
        Context getAppContext();
        Context getActivityContext();
    }

    // implemented by the model, available to the Presenter
    interface ProvidedModelOps {
        int getItemCount();
        Item getItem(int position);
        int insertItem(Item item);
        void onDestroy(boolean isConfigurationChanging);
    }

}
