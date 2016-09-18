package com.example.translatethis.main.discover.task;

import android.os.AsyncTask;

import com.example.translatethis.main.common.ContractFragment;
import com.example.translatethis.main.discover.view.DiscoverFragment;


public class ShowMessageTask extends AsyncTask<Void, Void, Void>{

    private String mMessage;
    private ContractFragment<DiscoverFragment.Contract> mFragment;

    public ShowMessageTask(ContractFragment<DiscoverFragment.Contract> fragment, String text) {
        mMessage = text;
        mFragment = fragment;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        // forward to the hosting activity
        mFragment.getContract().showDiscoverMessage(mMessage);
    }

}
