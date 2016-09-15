package com.example.translatethis.main.discover.model;


import com.example.translatethis.main.discover.MainMVP;
import com.example.translatethis.model.Item;

public class DiscoverModel implements MainMVP.ProvidedModelOps{

    // impl ProvidedModelOps contract methods
    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public Item getItem(int position) {
        return null;
    }

    @Override
    public int insertItem(Item item) {
        return 0;
    }

    @Override
    public void onDestroy(boolean isConfigurationChanging) {

    }
    // END

    private MainMVP.RequiredPresenterOps mPresenter;

    public DiscoverModel(MainMVP.RequiredPresenterOps presenter) {
        mPresenter = presenter;
    }


}
