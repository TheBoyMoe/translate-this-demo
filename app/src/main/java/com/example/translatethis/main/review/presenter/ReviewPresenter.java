package com.example.translatethis.main.review.presenter;

import android.content.Context;

import com.example.translatethis.main.review.MainMVP;
import com.example.translatethis.main.review.view.recycler.ItemViewHolder;
import com.example.translatethis.model.Item;


public class ReviewPresenter implements MainMVP.ProvidedPresenterOps, MainMVP.RequiredPresenterOps{

    // RequiredPresenterOps available to the Model
    @Override
    public Context getAppContext() {
        return null;
    }

    @Override
    public Context getActivityContext() {
        return null;
    }

    // ProvidedPresenterOps available to the View,
    @Override
    public void playItem(Item item) {

    }

    @Override
    public void deleteItem(Item item, int adapterPosition, int layoutPosition) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void bindViewHolder(ItemViewHolder holder, int position) {

    }

    @Override
    public void setView(MainMVP.RequiredViewOps view) {

    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {

    }


}
