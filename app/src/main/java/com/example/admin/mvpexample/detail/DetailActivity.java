package com.example.admin.mvpexample.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.admin.mvpexample.R;
import com.example.admin.mvpexample.data.DataProvider;
import com.example.admin.mvpexample.entities.Result;

public class DetailActivity extends AppCompatActivity {

    TextView title,first,last,gender,username;
    Result result;
    DataProvider dataProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dataProvider = DataProvider.getDataProvider();
        init();


    }

    private void init() {
        title = findViewById(R.id.showTitle);
        first = findViewById(R.id.showFirst);
        last = findViewById(R.id.showLast);
        gender = findViewById(R.id.showGender);
        username = findViewById(R.id.showUserName);
        result = dataProvider.users.get(dataProvider.posicion);
        title.setText(result.getName().getTitle());
        first.setText(result.getName().getFirst());
        last.setText(result.getName().getLast());
        gender.setText(result.getGender());
        username.setText(result.getLogin().getUsername());
    }
}

