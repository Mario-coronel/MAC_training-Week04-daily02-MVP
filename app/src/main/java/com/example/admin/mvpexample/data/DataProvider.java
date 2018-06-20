package com.example.admin.mvpexample.data;

import android.provider.ContactsContract;

import com.example.admin.mvpexample.entities.Result;

import java.util.List;

public class DataProvider {

    private static DataProvider instance;
    public List<Result> users;
    public int posicion;

    private DataProvider() {

    }

    public static DataProvider getDataProvider() {
        if (instance == null) {
            instance = new DataProvider();
        }
        return instance;
    }


}
