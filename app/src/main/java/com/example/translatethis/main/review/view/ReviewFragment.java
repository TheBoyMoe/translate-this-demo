package com.example.translatethis.main.review.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.translatethis.R;
import com.example.translatethis.main.ContractFragment;


public class ReviewFragment extends ContractFragment<ReviewFragment.Contract>{

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
