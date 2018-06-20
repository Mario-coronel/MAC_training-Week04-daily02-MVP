package com.example.admin.mvpexample.home;

import com.example.admin.mvpexample.base.BasePresesnter;
import com.example.admin.mvpexample.entities.Result;

import java.util.List;

public interface HomeContract {

    interface View{

        void showResult(List<Result> results);
        void showError();

        void navigateToDetail();

        void showUser();


    }

    interface Presenter extends BasePresesnter{


        void getResult(int users);

        void onNavigateToDetail();

    }

}
