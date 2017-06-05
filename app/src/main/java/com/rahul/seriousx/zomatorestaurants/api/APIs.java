package com.rahul.seriousx.zomatorestaurants.api;

import android.util.Log;
import android.widget.Toast;

import com.rahul.seriousx.zomatorestaurants.models.CuisinesResponseModel;
import com.rahul.seriousx.zomatorestaurants.models.RestaurantSearchResponseModel;
import com.rahul.seriousx.zomatorestaurants.retrofit.RetrofitClent;
import com.rahul.seriousx.zomatorestaurants.retrofit.ZomatoAPI;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

import static com.rahul.seriousx.zomatorestaurants.utilities.Utils.getOrderBy;
import static com.rahul.seriousx.zomatorestaurants.utilities.Utils.getSortBy;

/**
 * Created by root on 6/5/17.
 */

public class APIs {
    private Callbacks callbacks;
    private ZomatoAPI zomatoAPI;

    public interface Callbacks {
        void onStart();
        void onSuccess(Object response);
        void onFailure(Throwable t);
    }

    public APIs(Callbacks callbacks) {
        this.callbacks = callbacks;
        zomatoAPI = RetrofitClent.getInstance().create(ZomatoAPI.class);
    }

    public void getCuisines(int cityId) {
        Observable<CuisinesResponseModel> response = zomatoAPI.getCuisines(cityId);
        response.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<CuisinesResponseModel>() {
                    @Override
                    public void onNext(CuisinesResponseModel cuisinesResponseModel) {
                        callbacks.onSuccess(cuisinesResponseModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callbacks.onFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getRestaurantsByQuery(String query, String sortBy, String orderBy, int offset) {
        Observable<RestaurantSearchResponseModel> response = zomatoAPI.getRestaurants(query, sortBy, orderBy, offset);
        response.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<RestaurantSearchResponseModel>() {
                    @Override
                    public void onNext(RestaurantSearchResponseModel restaurantSearchResponseModel) {
                        callbacks.onSuccess(restaurantSearchResponseModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callbacks.onFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getMoreRestaurantsByQueryAndCuisine(String query, int cuisine_id, int offset) {
        ZomatoAPI zomatoAPI = RetrofitClent.getInstance().create(ZomatoAPI.class);
        Observable<RestaurantSearchResponseModel> response = zomatoAPI.getRestaurantsFilterByCuisine(query, cuisine_id, getSortBy(), getOrderBy(), offset);
        response.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<RestaurantSearchResponseModel>() {
                    @Override
                    public void onNext(RestaurantSearchResponseModel restaurantSearchResponseModel) {
                        callbacks.onSuccess(restaurantSearchResponseModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callbacks.onFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
