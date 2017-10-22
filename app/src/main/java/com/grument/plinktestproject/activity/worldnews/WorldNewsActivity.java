package com.grument.plinktestproject.activity.worldnews;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.grument.plinktestproject.R;
import com.grument.plinktestproject.activity.base.BaseActivity;
import com.grument.plinktestproject.adapter.WorldNewsItemAdapter;
import com.grument.plinktestproject.model.News;
import com.grument.plinktestproject.retrofit.RetrofitBuilder;
import com.grument.plinktestproject.retrofit.RetrofitService;
import com.grument.plinktestproject.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class WorldNewsActivity extends BaseActivity implements WorldNewsActivityView {


    @BindView(R.id.pb_world_news_loading)
    ProgressBar loadProgressBar;

    @BindView(R.id.iv_ny_times_logo)
    ImageView nyTimesLogoImageView;

    @BindView(R.id.rv_world_news)
    RecyclerView worldNewsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_news);
        ButterKnife.bind(this);

        worldNewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        worldNewsRecyclerView.setAdapter(new WorldNewsItemAdapter(this, new ArrayList<>()));


        RetrofitService retrofitService = new RetrofitBuilder().build().create(RetrofitService.class);

        Realm realm = Realm.getDefaultInstance();

        new WorldNewsActivityPresenterImpl(this, retrofitService, realm).onCreated();

    }

    @Override
    public void showProgress() {
        loadProgressBar.setVisibility(View.VISIBLE);
        nyTimesLogoImageView.setVisibility(View.VISIBLE);
        worldNewsRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        loadProgressBar.setVisibility(View.GONE);
        nyTimesLogoImageView.setVisibility(View.GONE);
        worldNewsRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        AppUtil.showToastError(this, getString(R.string.connection_error));
    }

    @Override
    public void showNews(List<News> news) {

        WorldNewsItemAdapter worldNewsItemAdapter = new WorldNewsItemAdapter(this, news);
        worldNewsRecyclerView.setAdapter(worldNewsItemAdapter);
        worldNewsItemAdapter.notifyAndShow();

    }
}
