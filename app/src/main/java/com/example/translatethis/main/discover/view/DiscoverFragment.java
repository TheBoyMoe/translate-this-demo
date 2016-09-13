package com.example.translatethis.main.discover.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.translatethis.R;
import com.example.translatethis.main.ContractFragment;
import com.example.translatethis.main.discover.MainMVP;


public class DiscoverFragment extends ContractFragment<DiscoverFragment.Contract>
        implements MainMVP.RequiredViewOps{

    // impl RequiredViewOps contract
    @Override
    public Context getAppContext() {
        return null;
    }

    @Override
    public Context getActivityContext() {
        return null;
    }
    // END

    public interface Contract {
        // communicate upto the hosting activity
        void showMessage(String message);
    }

    public DiscoverFragment() {}

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText("Discover Fragment");
        title.setOnClickListener(titleClick);
        return view;
    }

    private View.OnClickListener titleClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getContract().showMessage("Clicked on Discover Title");
        }
    };


}
