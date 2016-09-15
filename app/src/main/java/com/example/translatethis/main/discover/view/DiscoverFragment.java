package com.example.translatethis.main.discover.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.translatethis.R;
import com.example.translatethis.main.common.ContractFragment;
import com.example.translatethis.main.common.StateMaintainer;
import com.example.translatethis.main.discover.MainMVP;
import com.example.translatethis.main.discover.model.DiscoverModel;
import com.example.translatethis.main.discover.presenter.DiscoverPresenter;


public class DiscoverFragment extends ContractFragment<DiscoverFragment.Contract>
        implements MainMVP.RequiredViewOps{

    // impl RequiredViewOps contract
    @Override
    public Context getAppContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }
    // END

    public interface Contract {
        // forward to the hosting activity
        void showDiscoverMessage(String message);
    }

    private MainMVP.ProvidedPresenterOps mPresenter;
    private TextView mOriginalText;
    private TextView mTranslatedText;

    protected StateMaintainer mStateMaintainer;

    public DiscoverFragment() {}

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStateMaintainer =
            new StateMaintainer(getActivity().getSupportFragmentManager(), DiscoverFragment.class.getName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_discover, container, false);
        initView(view);
        setupBottomToolbarButtons(view);
        setupMVP();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy(getActivity().isChangingConfigurations());
        }
    }

    private void setupMVP() {
        // instantiate the Model and Presenter instances and save their states
        if (mStateMaintainer.firstTimeIn()) {
            DiscoverPresenter presenter = new DiscoverPresenter(this);
            DiscoverModel model = new DiscoverModel(presenter);
            presenter.setModel(model);
            mStateMaintainer.put(presenter);
            mStateMaintainer.put(model);
            mPresenter = presenter;
        } else {
            mPresenter = mStateMaintainer.get(DiscoverPresenter.class.getName());
            mPresenter.setView(this);
        }
    }

    private void initView(View view) {
        mOriginalText = (TextView) view.findViewById(R.id.text_original);
        mTranslatedText = (TextView) view.findViewById(R.id.text_translation);
    }

    private void setupBottomToolbarButtons(View view) {
        view.findViewById(R.id.action_record).setOnClickListener(buttonClickListener);
        view.findViewById(R.id.action_edit).setOnClickListener(buttonClickListener);
        view.findViewById(R.id.action_translate).setOnClickListener(buttonClickListener);
        view.findViewById(R.id.action_play).setOnClickListener(buttonClickListener);
        view.findViewById(R.id.action_save).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // forward these to the presenter for processing
            switch (view.getId()) {
                case R.id.action_record:
                    //getContract().showMessage("Click record");
                    break;
                case R.id.action_edit:
                    //getContract().showMessage("Clicked edit");
                    break;
                case R.id.action_translate:
                    //getContract().showMessage("Clicked translate");
                    break;
                case R.id.action_play:
                    //getContract().showMessage("Clicked play");
                    break;
                case R.id.action_save:
                    //getContract().showMessage("Clicked save");
                    break;
            }
        }

    };

}
