package com.example.translatethis.main.discover.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        View view = inflater.inflate(R.layout.content_discover, container, false);
        setupBottomToolbarButtons(view);
        return view;
    }

    private void setupBottomToolbarButtons(View view) {
        view.findViewById(R.id.action_record).setOnClickListener(menuItemClickListener);
        view.findViewById(R.id.action_edit).setOnClickListener(menuItemClickListener);
        view.findViewById(R.id.action_translate).setOnClickListener(menuItemClickListener);
        view.findViewById(R.id.action_play).setOnClickListener(menuItemClickListener);
        view.findViewById(R.id.action_save).setOnClickListener(menuItemClickListener);
    }

    private View.OnClickListener menuItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.action_record:
                    getContract().showMessage("Click record");
                    break;
                case R.id.action_edit:
                    getContract().showMessage("Clicked edit");
                    break;
                case R.id.action_translate:
                    getContract().showMessage("Clicked translate");
                    break;
                case R.id.action_play:
                    getContract().showMessage("Clicked play");
                    break;
                case R.id.action_save:
                    getContract().showMessage("Clicked save");
                    break;
            }
        }

    };

}
