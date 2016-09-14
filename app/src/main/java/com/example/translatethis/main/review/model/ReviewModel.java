package com.example.translatethis.main.review.model;


import com.example.translatethis.main.review.MainMVP;
import com.example.translatethis.model.Item;

public class ReviewModel implements MainMVP.ProvidedModelOps{

    // ProvidedModelOps available to the Presenter
    @Override
    public boolean loadItems() {
        return false;
    }

    @Override
    public Item getItem(int position) {
        return null;
    }

    @Override
    public boolean deleteItem(Item item, int adapterPosition) {
        return false;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {

    }


}
