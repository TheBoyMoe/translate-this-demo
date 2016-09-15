package com.example.translatethis.main.discover.model;


import com.example.translatethis.main.discover.MainMVP;
import com.example.translatethis.model.Item;

public class DiscoverModel implements MainMVP.ProvidedModelOps{

    // impl ProvidedModelOps contract methods
    @Override
    public int getItemCount() {
        // TODO
        return 0;
    }

    @Override
    public Item getItem(int position) {
        // TODO retrieve item from database
        return null;
    }

    @Override
    public int insertItem(Item item) {
        // TODO insert item into database
        return 0;
    }

    @Override
    public void onDestroy(boolean isConfigurationChanging) {
        if (isConfigurationChanging) mPresenter = null;
    }
    // END

    private MainMVP.RequiredPresenterOps mPresenter;

    public DiscoverModel(MainMVP.RequiredPresenterOps presenter) {
        mPresenter = presenter;
    }


}
