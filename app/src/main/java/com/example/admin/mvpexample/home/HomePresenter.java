package com.example.admin.mvpexample.home;

import android.util.Log;

import com.example.admin.mvpexample.data.DataProvider;
import com.example.admin.mvpexample.data.RandomAPI;
import com.example.admin.mvpexample.entities.UserResponse;
import com.example.admin.mvpexample.helper.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View homeView;
    public static final String TAG = HomePresenter.class.getSimpleName() + "_TAG";
    private RandomAPI randomAPI;
    DataProvider dataProvider = DataProvider.getDataProvider();

    public HomePresenter(HomeContract.View homeView) {
        this.homeView = homeView;
        randomAPI = RetrofitHelper.getInstance().getRandomAPI();
    }


    @Override
    public void getResult(int users) {
        //Here we call the model / service /  managers / iteractor /use cases, etc
        getRandomUsers(users);

    }

    @Override
    public void onNavigateToDetail() {
        //Chance to preparte data to be shared

    }

    @Override
    public void onViewDestroyed() {
        this.homeView = null;
    }


    private void getRandomUsers(int count) {
        randomAPI.getUsers(count).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    dataProvider.users = response.body().getResults();
                    homeView.showResult(dataProvider.users);
                } else {
                    homeView.showError();
                    Log.d(TAG, "on Response : error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d(TAG, "on Failure: " + t);

            }
        });


    }

}
