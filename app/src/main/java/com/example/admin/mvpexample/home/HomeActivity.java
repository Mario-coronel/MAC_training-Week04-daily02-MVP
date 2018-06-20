package com.example.admin.mvpexample.home;

import android.content.Context;
import android.content.Intent;
import android.service.autofill.RegexValidator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mvpexample.R;
import com.example.admin.mvpexample.data.DataProvider;
import com.example.admin.mvpexample.detail.DetailActivity;
import com.example.admin.mvpexample.entities.Result;
import com.example.admin.mvpexample.helper.UserViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeContract.View, View.OnClickListener {

    public static final String TAG = HomeActivity.class.getSimpleName() + "_TAG";
    private TextView resultTV;
    private EditText searchET;
    private Button resultBT;
    private Button detailBT;
    private RecyclerView recyclerView;
    private DataProvider dataProvider;
    private HomePresenter homePresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        homePresenter = new HomePresenter(this);
        recyclerView = findViewById(R.id.recycler_view_users_list);
        dataProvider = DataProvider.getDataProvider();

    }

    private void initViews() {
        resultBT = findViewById(R.id.btResult);
        searchET = findViewById(R.id.userCountET);
        resultBT.setOnClickListener(this);
    }

    @Override
    public void showResult(List<Result> results) {
        generateUsersList(results);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void navigateToDetail() {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        startActivity(detailIntent);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btResult:
                homePresenter.getResult(Integer.valueOf(searchET.getText().toString().trim()));
                break;
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.onViewDestroyed();
        homePresenter = null;
    }





    private void generateUsersList(List<Result> results) {
        recyclerView =  findViewById(R.id.recycler_view_users_list);

        UserViewAdapter adapter = new UserViewAdapter(new ArrayList(results), this );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
        Toast.makeText(this, String.valueOf(results.size()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUser() {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
}

