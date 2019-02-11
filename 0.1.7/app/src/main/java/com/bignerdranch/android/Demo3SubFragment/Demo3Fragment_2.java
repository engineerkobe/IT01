package com.bignerdranch.android.Demo3SubFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.R;

public class Demo3Fragment_2 {
    private Context mContext;
    private View mView;
    private RecyclerView mRecyclerView;

    public Demo3Fragment_2( Context context ,View view) {
        mView = view;
        mContext = context;
    }
    public View Create() {

        mRecyclerView = mView.findViewById(R.id.main_bookshelf_list_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        mRecyclerView.setAdapter(new inAdapter(LayoutInflater.from(mContext)));

        return mView;
    }
    private class inItemDecoration  extends RecyclerView.ItemDecoration {


        @Override
        public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDraw(c, parent, state);
        }

        @Override
        public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//            super.getItemOffsets(outRect, view, parent, state);
        }
    }
    private class inAdapter extends RecyclerView.Adapter<inViewHolder> {
        private final LayoutInflater inflater;

        inAdapter(LayoutInflater inflater) {
            this.inflater =  inflater;
        }
        @NonNull
        @Override
        public inViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new inViewHolder(inflater,viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull inViewHolder mViewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }
    private class inViewHolder extends RecyclerView.ViewHolder {

        public inViewHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.main_bookshelf_fragment_2_item,container,false));
        }
    }
}
