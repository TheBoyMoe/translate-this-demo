package com.example.translatethis.main.review.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.translatethis.R;
import com.example.translatethis.common.Constants;
import com.example.translatethis.main.review.MainMVP;
import com.example.translatethis.main.review.view.recycler.ItemViewHolder;
import com.example.translatethis.model.Item;

import java.lang.ref.WeakReference;
import java.util.Locale;

import timber.log.Timber;


public class ReviewPresenter implements MainMVP.ProvidedPresenterOps, MainMVP.RequiredPresenterOps{

    // cache references to both the model and view
    private WeakReference<MainMVP.RequiredViewOps> mView;
    private MainMVP.ProvidedModelOps mModel;

    // constructor
    public ReviewPresenter(MainMVP.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    // ensure that when you call the view, it is available
    protected MainMVP.RequiredViewOps getView() {
        if (mView != null) {
            return mView.get();
        } else {
            throw new NullPointerException("View is unavailable");
        }
    }

    // cache a reference to the model
    public void setModel(MainMVP.ProvidedModelOps model) {
        mModel = model;
        if (mModel.loadItems()) {
            getView().notifyDataSetChanged();
        } else {
            getView().showMessage("Error loading data");
        }
    }

    // RequiredPresenterOps available to the Model
    @Override
    public Context getAppContext() {
        try {
            return getView().getAppContext();
        } catch (NullPointerException e) {
            Timber.e("%s %s", Constants.LOG_TAG, e.getMessage());
            return null;
        }
    }

    @Override
    public Context getActivityContext() {
        try {
            return getView().getActivityContext();
        } catch (NullPointerException e) {
            Timber.e("%s %s", Constants.LOG_TAG, e.getMessage());
            return null;
        }
    }

    // ProvidedPresenterOps available to the View,
    @Override
    public void playItem(Item item) {
        //TODO
        getView().showMessage(String.format(Locale.ENGLISH, "Play audio clip %s", item.getOriginalText()));
    }

    @Override
    public void deleteItem(Item item, int adapterPosition, int layoutPosition) {
        // TODO
        getView().showMessage(String.format(Locale.ENGLISH, "Delete item %s", item.getOriginalText()));
    }

    @Override
    public int getItemCount() {
        if (mModel != null) {
            return mModel.getItemCount();
        }
        return 0;
    }

    @Override
    public void bindViewHolder(final ItemViewHolder holder, int position) {
        final Item item = mModel.getItem(position);
        holder.mOriginalText.setText(item.getOriginalText());
        holder.mTranslatedText.setText(item.getTranslatedText());
        holder.mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playItem(item);
            }
        });
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem(item, holder.getAdapterPosition(), holder.getLayoutPosition());
            }
        });
    }

    @Override
    public ItemViewHolder createViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void setView(MainMVP.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        mView = null;
        if (mModel != null) {
            mModel.onDestroy(isChangingConfiguration); // forward to model
            if (isChangingConfiguration) mModel = null;
        }
    }



}
