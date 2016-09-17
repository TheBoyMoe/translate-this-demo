package com.example.translatethis.main.discover.task;


import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.translatethis.common.Constants;

import timber.log.Timber;

public class UpdateImageResourceTask extends AsyncTask<Void, Void, Void>{

    private ImageView mIcon;
    private int mDrawable;

    public UpdateImageResourceTask(ImageView icon, int drawable) {
        mIcon = icon;
        mDrawable = drawable;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Timber.i("%s recordingComplete called", Constants.LOG_TAG);
        mIcon.setImageResource(mDrawable);
    }
}
