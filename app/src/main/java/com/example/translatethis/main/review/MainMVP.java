package com.example.translatethis.main.review;


import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.example.translatethis.main.review.view.recycler.ItemViewHolder;
import com.example.translatethis.model.Item;

import java.util.ArrayList;

public interface MainMVP {

    // implemented by the View, available to the Presenter
    // allowing the Presenter to Communicate back to the View
    interface RequiredViewOps {
        Context getAppContext();
        Context getActivityContext();
        void notifyItemRemoved(int position);
        void notifyDataSetChanged();
        void notifyItemRangeChanged(int positionStart, int itemCount);
        void showMessage(String message);
        void showDialog(AlertDialog dialog);
    }

    // implemented by the Presenter, available to the View
    interface ProvidedPresenterOps {
        void playItem(Item item);
        void deleteItem(Item item, int adapterPosition, int layoutPosition);
        int getItemCount();
        void bindViewHolder(ItemViewHolder holder, int position);
        void setView(RequiredViewOps view);
        void onDestroy(boolean isChangingConfiguration);
    }

    // implemented by the Presenter, available to the Model
    // allowing the Model to communicate back to the Presenter
    interface RequiredPresenterOps {
        Context getAppContext();
        Context getActivityContext();
    }

    // implemented by the model, available to the Presenter
    interface ProvidedModelOps {
        ArrayList<Item> getItems();
        boolean loadItems();
        Item getItem(int position);
        boolean deleteItem(Item item, int adapterPosition);
        int getItemCount();
        void onDestroy(boolean isChangingConfiguration);
    }


}
