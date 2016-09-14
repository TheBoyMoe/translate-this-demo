package com.example.translatethis.main.review.model;


import com.example.translatethis.main.review.MainMVP;
import com.example.translatethis.model.Item;
import com.example.translatethis.model.data.DummyItemDataCache;

import java.util.ArrayList;
import java.util.Arrays;

public class ReviewModel implements MainMVP.ProvidedModelOps{

    // currently using the DummyItemDataCache

    private MainMVP.RequiredPresenterOps mPresenter;
    private ArrayList<Item> mList;

    public ReviewModel(MainMVP.RequiredPresenterOps presenter) {
        mPresenter = presenter;
        mList = new ArrayList<>(Arrays.asList(DummyItemDataCache.dataCache));
    }



    // ProvidedModelOps available to the Presenter
    @Override
    public boolean loadItems() {
        // TODO
        return mList != null;
    }

    @Override
    public ArrayList<Item> getItems() {
        // remove once database is in operation
        if (mList != null) {
            return mList;
        }
        return null;
    }

    @Override
    public Item getItem(int position) {
        if (mList != null) {
            return mList.get(position);
        }
        return null;
    }

    @Override
    public boolean deleteItem(Item item, int adapterPosition) {
        // TODO build database 1st
        return false;
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        if (isChangingConfiguration) {
            mPresenter = null;
            mList = null;
        }
    }


}
