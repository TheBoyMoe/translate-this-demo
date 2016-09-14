package com.example.translatethis.main.review.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.translatethis.R;
import com.example.translatethis.main.ContractFragment;
import com.example.translatethis.main.StateMaintainer;
import com.example.translatethis.main.review.MainMVP;
import com.example.translatethis.main.review.model.ReviewModel;
import com.example.translatethis.main.review.presenter.ReviewPresenter;
import com.example.translatethis.main.review.view.recycler.ItemViewHolder;


public class ReviewFragment extends ContractFragment<ReviewFragment.Contract>
        implements MainMVP.RequiredViewOps{

    // communicate with the hosting activity
    public interface Contract {
        void showReviewMessage(String message);
    }

    private MainMVP.ProvidedPresenterOps mPresenter;
    private ProgressBar mProgressBar;
    private ItemListAdapter mAdapter;

    protected StateMaintainer mStateMaintainer;


    // RequiredViewOps available to the Presenter
    @Override
    public Context getAppContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public void notifyItemRemoved(int position) {
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyItemRangeChanged(int positionStart, int itemCount) {
        mAdapter.notifyItemRangeChanged(positionStart, itemCount);
    }

    @Override
    public void showMessage(String message) {
         getContract().showReviewMessage(message);
    }

    @Override
    public void showDialog(AlertDialog dialog) {
        dialog.show();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    public ReviewFragment() {}

    public static ReviewFragment newInstance() {
        return new ReviewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mStateMaintainer =
            new StateMaintainer(getActivity().getSupportFragmentManager(), ReviewFragment.class.getName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO update RecyclerView with AutoRecyclerView
        View view = inflater.inflate(R.layout.content_review, container, false);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        // initialize adapter and recycler
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter = new ItemListAdapter();
        recyclerView.setLayoutManager(manager);
//        recyclerView.addItemDecoration(new ItemSpacerDecoration(
//                getResources().getDimensionPixelOffset(R.dimen.item_vertical_margin),
//                getResources().getDimensionPixelOffset(R.dimen.item_horizontal_margin)
//        ));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        setupMVP();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) { // NPE on device rotation otherwise
            mPresenter.onDestroy(getActivity().isChangingConfigurations());
        }
    }

    private void setupMVP() {
        // instantiate the Model and Presenter instances, save their states
        if (mStateMaintainer.firstTimeIn()) {
            ReviewPresenter presenter = new ReviewPresenter(this);
            ReviewModel model = new ReviewModel(presenter);
            presenter.setModel(model);
            mStateMaintainer.put(presenter);
            mStateMaintainer.put(model);
            mPresenter = presenter;
        } else {
            // retrieve the presenter obj on device re-configuration, Model is only set once
            mPresenter = mStateMaintainer.get(ReviewPresenter.class.getName());
            // update the View
            mPresenter.setView(this);
        }
    }

    protected class ItemListAdapter extends RecyclerView.Adapter<ItemViewHolder> {

        // forward all adapter methods to the Presenter
        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return mPresenter.createViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            mPresenter.bindViewHolder(holder, position);
        }

        @Override
        public int getItemCount() {
            // FIXME ?? compare to original
            if (mPresenter != null) { // NPE on device rotation
                return mPresenter.getItemCount();
            }
            return 0;
        }
    }



}
