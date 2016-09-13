package com.example.translatethis.main.review;


import android.content.Context;

public interface MainMVP {

    // implemented by the View, available to the Presenter
    // allowing the Presenter to Communicate back to the View
    interface RequiredViewOps {
        Context getAppContext();
        Context getActivityContext();
    }

    // implemented by the Presenter, available to the View
    interface ProvidedPresenterOps {

    }

    // implemented by the Presenter, available to the Model
    // allowing the Model to communicate back to the Presenter
    interface RequiredPresenterOps {
        Context getAppContext();
        Context getActivityContext();
    }

    // implemented by the model, available to the Presenter
    interface ProvidedModelOps {

    }


}
