package com.example.translatethis.main.discover.task;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.translatethis.common.Constants;
import com.example.translatethis.common.SharedPrefsUtils;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class TranslationTask extends AsyncTask<Void, Void, String>{

    private Context mContext;
    private String mFromText;
    private String mTranslatedText;
    private TranslationTaskListener mPresenter;

    public TranslationTask(TranslationTaskListener listener, Context context, String fromText) {
        mPresenter = listener;
        mContext = context;
        mFromText = fromText;
        mTranslatedText = "";
    }

        @Override
    protected String doInBackground(Void... voids) {
        Language from = Constants.LANGUAGES[SharedPrefsUtils.getFromLanguage(mContext)];
        Language to = Constants.LANGUAGES[SharedPrefsUtils.getToLanguage(mContext)];
        Translate.setClientId(Constants.CLIENT_ID_VALUE);
        Translate.setClientSecret(Constants.CLIENT_SECRET_VALUE);
        try {
            mTranslatedText = Translate.execute(mFromText, from, to);
        } catch (Exception e) {
            Log.e(Constants.LOG_TAG, e.getMessage());
        }
        return mTranslatedText;
    }

    @Override
    protected void onPostExecute(String toText) {
        // forward the translated text back to the Presenter
        mPresenter.translationResult(toText);
    }

}
