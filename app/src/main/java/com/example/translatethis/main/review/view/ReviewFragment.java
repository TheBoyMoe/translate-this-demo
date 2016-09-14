package com.example.translatethis.main.review.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.translatethis.R;
import com.example.translatethis.main.ContractFragment;
import com.example.translatethis.main.review.MainMVP;


public class ReviewFragment extends ContractFragment<ReviewFragment.Contract>
        implements MainMVP.RequiredViewOps{

    // RequiredViewOps available to the Presenter
    @Override
    public Context getAppContext() {
        return null;
    }

    @Override
    public Context getActivityContext() {
        return null;
    }

    @Override
    public void notifyItemRemoved(int position) {

    }

    @Override
    public void notifyDataSetChanged() {

    }

    @Override
    public void notifyItemRangeChanged(int positionStart, int itemCount) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showDialog(AlertDialog dialog) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    // communicate with the hosting activity
    public interface Contract {

    }

    public ReviewFragment() {}

    public static ReviewFragment newInstance() {
        return new ReviewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_review, container, false);
        TextView content = (TextView) view.findViewById(R.id.title);
        content.setText(getString(R.string.long_text));

        return view;
    }


}
