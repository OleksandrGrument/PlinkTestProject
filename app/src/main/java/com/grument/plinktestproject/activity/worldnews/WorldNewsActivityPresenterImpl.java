package com.grument.plinktestproject.activity.worldnews;



import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.grument.plinktestproject.dto.MultimediaDTO;
import com.grument.plinktestproject.dto.NewsDTO;
import com.grument.plinktestproject.dto.WorldNewsResultsDTO;
import com.grument.plinktestproject.model.News;
import com.grument.plinktestproject.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;


import static com.grument.plinktestproject.util.Constants.NY_TIMES_API_KEY;

public class WorldNewsActivityPresenterImpl implements WorldNewsActivityPresenter {

    public WorldNewsActivityPresenterImpl(WorldNewsActivityView worldNewsActivityView,
                                          RetrofitService retrofitService,
                                          Realm realm) {
        this.worldNewsActivityView = worldNewsActivityView;
        this.retrofitService = retrofitService;
        this.realm = realm;
    }

    private final RetrofitService retrofitService;

    private final WorldNewsActivityView worldNewsActivityView;

    private final Realm realm;

    @Override
    public void onCreated() {

        worldNewsActivityView.showProgress();

        retrofitService.getWorldNews(NY_TIMES_API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsFeedResponse -> {

                    if (newsFeedResponse.isSuccessful()) {

                        WorldNewsResultsDTO worldNewsResultsDTO = newsFeedResponse.body();
                        ArrayList<NewsDTO> newsDTOList = worldNewsResultsDTO.getNewsDTOArrayList();

                        List<News> newsList = Stream.of(newsDTOList)
                                .filter(dto -> !dto.getMultimediaDTOs().isEmpty())
                                .map(this::createNewsFromDto)
                                .collect(Collectors.toList());

                        realm.beginTransaction();
                        realm.delete(News.class);
                        realm.insert(newsList);
                        realm.commitTransaction();
                        afterRequestUpdateUI(true);

                    } else {
                        afterRequestUpdateUI(false);
                    }

                });
    }

    private void afterRequestUpdateUI(boolean isRequestSuccess) {
        worldNewsActivityView.hideProgress();
        worldNewsActivityView.showNews(realm.where(News.class).findAll());

        if (!isRequestSuccess) worldNewsActivityView.showError();
    }

    private News createNewsFromDto(NewsDTO newsDTO){
        ArrayList <MultimediaDTO> multimediaDTOs = newsDTO.getMultimediaDTOs();
        return new News(newsDTO.getTitle(), newsDTO.getUrl(),multimediaDTOs.get(multimediaDTOs.size()-1).getUrl());
    }


}
