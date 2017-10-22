package com.grument.plinktestproject.activity.worldnews;


import com.grument.plinktestproject.activity.base.BaseView;
import com.grument.plinktestproject.model.News;

import java.util.List;

public interface WorldNewsActivityView extends BaseView {

    void showProgress();

    void hideProgress();

    void showError();

    void showNews(List<News> news);

}
