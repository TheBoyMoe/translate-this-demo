package com.example.translatethis.main.discover.task;

import android.os.AsyncTask;
import android.widget.TextView;


public class UpdateTextFieldTask extends AsyncTask<Void, Void, Void>{

    private TextView mView;
    private String mText;

    public UpdateTextFieldTask(TextView view, String text) {
        mView = view;
        mText = text;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        mView.setText(mText);
    }

}
