package com.bignerdranch.android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Demo extends Fragment {
    private RecyclerView mDemoRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_home_book_list,container,false);
        mDemoRecyclerView = (RecyclerView) v.findViewById(R.id.main_home_book_list_recyclerview);
        mDemoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDemoRecyclerView.setAdapter(new DemoBookAdapter());
        return v;

    }
    private class DemoBookAdapter extends RecyclerView.Adapter<DemoBookHolder> {

        @NonNull
        @Override
        public DemoBookHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            return new DemoBookHolder(layoutInflater,viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull DemoBookHolder demoBookHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }
    private class DemoBookHolder extends RecyclerView.ViewHolder {

        public DemoBookHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.main_home_book_list_item, viewGroup,false));
        }
    }
}
