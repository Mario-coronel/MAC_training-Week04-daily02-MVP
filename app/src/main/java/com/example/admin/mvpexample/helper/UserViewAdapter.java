package com.example.admin.mvpexample.helper;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mvpexample.R;
import com.example.admin.mvpexample.data.DataProvider;
import com.example.admin.mvpexample.detail.DetailActivity;
import com.example.admin.mvpexample.entities.Result;
import com.example.admin.mvpexample.home.HomeContract;
import com.example.admin.mvpexample.home.HomePresenter;

import java.util.ArrayList;
import java.util.List;

public class UserViewAdapter extends RecyclerView.Adapter<UserViewAdapter.UsersViewHolder> {

    ArrayList<Result> users;
    DataProvider dataProvider;
    HomeContract.View view;


    public UserViewAdapter(List<Result> users, HomeContract.View view) {
        this.users = new ArrayList(users);
        this.view = view;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_row, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, int position) {

        holder.title.setText(users.get(position).getName().getTitle());
        holder.first.setText(users.get(position).getName().getFirst());
        holder.last.setText(users.get(position).getName().getLast());
        holder.idtv.setText(String.valueOf(position));

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView title, first, last,idtv;

        public UsersViewHolder(final View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.userTitleTV);
            first = itemView.findViewById(R.id.userFirstTV);
            last = itemView.findViewById(R.id.userLast);
            idtv = itemView.findViewById(R.id.IDTV);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataProvider = DataProvider.getDataProvider();
                    dataProvider.posicion = Integer.valueOf(idtv.getText().toString());
                    view.showUser();

                }
            });
        }
    }




}

