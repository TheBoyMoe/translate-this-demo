package com.example.translatethis.main.discover.task;

import android.os.AsyncTask;
import android.widget.TextView;

import com.example.translatethis.common.Constants;

import timber.log.Timber;


public class AppendFromTextFieldTask extends AsyncTask<Void, Void, Void>{

    private String mText;
    private TextView mView;

    public AppendFromTextFieldTask(TextView view, String text) {
        this.mText = text;
        mView = view;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Timber.i("%s appendFromField called", Constants.LOG_TAG);
        mView.append(mText);
    }

}
