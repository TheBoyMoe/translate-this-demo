package com.example.translatethis.main.discover.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.translatethis.R;
import com.example.translatethis.common.Constants;
import com.example.translatethis.common.SharedPrefsUtils;
import com.example.translatethis.main.common.ContractFragment;
import com.example.translatethis.main.common.StateMaintainer;
import com.example.translatethis.main.discover.MainMVP;
import com.example.translatethis.main.discover.model.DiscoverModel;
import com.example.translatethis.main.discover.presenter.DiscoverPresenter;
import com.example.translatethis.main.discover.task.ShowMessageTask;
import com.example.translatethis.main.discover.task.UpdateImageResourceTask;
import com.example.translatethis.main.discover.task.UpdateTextFieldTask;


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

    @Override
    public void showMessage(String message) {
        new ShowMessageTask(this, message).execute();
    }

    @Override
    public void updateFromTextField(String update) {
        new UpdateTextFieldTask(mFromTextView, update).execute();
    }

    @Override
    public void updateFromSmallText(String update) {
        new UpdateTextFieldTask(mFromSmallTextView, update).execute();
    }

    @Override
    public void updateToTextField(String result) {
        new UpdateTextFieldTask(mToTextView, result).execute();
    }

    @Override
    public void isServiceRunning(boolean isRunning) {
        if (isRunning) {
            new UpdateImageResourceTask(mRecordIcon, R.drawable.ic_record_busy).execute();
            //new UpdateTextFieldTask(mFromSmallTextView, getString(R.string.from_field_small_text)).execute();
        }
        else {
            new UpdateImageResourceTask(mRecordIcon, R.drawable.ic_record_dark).execute();
            //new UpdateTextFieldTask(mFromSmallTextView, "").execute();
        }
    }

    @Override
    public void isClientConnected(boolean result) {
        if (!result) {
            getContract().showDiscoverMessage(getString(R.string.network_error_message));
        }
    }
    // END

    public interface Contract {
        // forward to the hosting activity
        void showDiscoverMessage(String message);
    }

    private MainMVP.ProvidedPresenterOps mPresenter;
    private TextView mFromTextView;
    private TextView mFromSmallTextView;
    private TextView mToTextView;
    private Spinner mFromSpinner;
    private Spinner mToSpinner;
    private ImageView mRecordIcon;
    private boolean mHasOptionChanged = false; // FIXME ?? req'd
    private String mFromLanguage = Constants.LANGUAGE_CODES[0];
    private String mToLanguage = Constants.LANGUAGE_CODES[0];

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
        initFromSpinner(view);
        initToSpinner(view);
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
        mFromTextView = (TextView) view.findViewById(R.id.text_original);
        mFromSmallTextView = (TextView) view.findViewById(R.id.from_field_small_text);
        mToTextView = (TextView) view.findViewById(R.id.text_translation);
        mRecordIcon = (ImageView) view.findViewById(R.id.recording_icon);
    }

    private void initFromSpinner(View view) {
        mFromSpinner = (Spinner) view.findViewById(R.id.spinner_from);
        mFromSpinner.setSaveEnabled(true);
        mFromSpinner.setSelection(SharedPrefsUtils.getFromLanguage(getActivityContext()));
        mFromLanguage = Constants.LANGUAGE_CODES[SharedPrefsUtils.getFromLanguage(getActivityContext())];
        mFromSpinner.post(fromLanguageRunnable);
    }

    Runnable fromLanguageRunnable = new Runnable() {
        @Override
        public void run() {
            mFromSpinner.setOnItemSelectedListener(fromLanguageListener);
        }
    };

    AdapterView.OnItemSelectedListener fromLanguageListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            mFromLanguage = Constants.LANGUAGE_CODES[position];
            mHasOptionChanged = true; // FIXME ?? req'd
            // notify the presenter the language option has changed and update SharedPrefs
            mPresenter.hasLanguageOptionsChanged(true);
            SharedPrefsUtils.updateFromLanguage(getActivityContext(), position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            // no-op
        }
    };

    private void initToSpinner(View view) {
        mToSpinner = (Spinner) view.findViewById(R.id.spinner_to);
        mToSpinner.setEnabled(true);
        mToSpinner.setSelection(SharedPrefsUtils.getToLanguage(getActivityContext()));
        mToLanguage = Constants.LANGUAGE_CODES[SharedPrefsUtils.getToLanguage(getActivityContext())];
        mToSpinner.post(toLanguageRunnable);
    }

    Runnable toLanguageRunnable = new Runnable() {
        @Override
        public void run() {
            mToSpinner.setOnItemSelectedListener(toLanguageListener);
        }
    };

    AdapterView.OnItemSelectedListener toLanguageListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            mToLanguage = Constants.LANGUAGE_CODES[position];
            mHasOptionChanged = true; // FIXME ?? req'd
            // notify the presenter the language option has changed and update SharedPrefs
            mPresenter.hasLanguageOptionsChanged(true);
            SharedPrefsUtils.updateToLanguage(getActivityContext(), position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            // no-op
        }
    };

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
                    mPresenter.recordSpeech();
                    break;
                case R.id.action_edit:
                    mPresenter.editResult();
                    break;
                case R.id.action_translate:
                    mPresenter.translateResult();
                    break;
                case R.id.action_play:
                    // TODO
                    mPresenter.playResult("");
                    break;
                case R.id.action_save:
                    // TODO
                    mPresenter.saveResult(null);
                    break;
            }
        }

    };


}
