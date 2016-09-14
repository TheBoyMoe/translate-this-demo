package com.example.translatethis.main.review.view.recycler;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.translatethis.R;

public class ItemViewHolder extends RecyclerView.ViewHolder{

    public TextView mOriginalText;
    public TextView mTranslatedText;
    public ImageView mPlay;
    public ImageView mDelete;

    public ItemViewHolder(View itemView) {
        super(itemView);
        mOriginalText = (TextView) itemView.findViewById(R.id.text_original);
        mTranslatedText = (TextView) itemView.findViewById(R.id.text_translation);
        mPlay = (ImageView) itemView.findViewById(R.id.action_play);
        mDelete = (ImageView) itemView.findViewById(R.id.action_delete);
    }



}
