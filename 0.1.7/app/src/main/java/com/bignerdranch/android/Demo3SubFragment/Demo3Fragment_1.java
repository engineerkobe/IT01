package com.bignerdranch.android.Demo3SubFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bignerdranch.android.R;

import static com.bignerdranch.android.Util.DensityUtil.dip2px;
import static java.lang.Thread.*;

public class Demo3Fragment_1 {
    private Context mContext;
    private View mView;
    private RecyclerView mRecyclerView;
    private Inadapter mInAdapter;
    private CheckBox mDelAllSelect;
    private CheckBox mDelyes;

    public Demo3Fragment_1( Context context ,View view) {
        mView = view;
        mContext = context;
        mInAdapter = new Inadapter(LayoutInflater.from(mContext));
    }
    public View Create() {

        mDelAllSelect =(CheckBox) mView.findViewById(R.id.delstatus_all_select);
        mDelyes =(CheckBox) mView.findViewById(R.id.delstatus_yes);
        initView();

        //mRecyclerView = mView.findViewById(R.id.main_bookshelf_list_recyclerview);
        mRecyclerView = mView.findViewById(R.id.main_bookshelf_list_recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        mRecyclerView.setAdapter(mInAdapter);
        return mView;
    }

    private void initView() {
        mDelAllSelect.setVisibility(View.GONE);
        mDelyes.setVisibility(View.GONE);
    }

    public void openDelstatus(boolean delstatus) {
        mInAdapter.openDelstatus(delstatus);
        mInAdapter.notifyDataSetChanged();
        if(delstatus) {
           mDelAllSelect.setVisibility(View.VISIBLE);
           mDelyes.setVisibility(View.VISIBLE);
        }else{
            mDelAllSelect.setVisibility(View.GONE);
            mDelyes.setVisibility(View.GONE);

        }

    }

    private class Inadapter extends RecyclerView.Adapter<InViewHolder> {
        private final LayoutInflater inflater;
        private boolean mDelstatus;

        Inadapter(LayoutInflater inflater) {
            this.inflater =  inflater;
        }
        @NonNull
        @Override
        public InViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new InViewHolder(inflater,viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull InViewHolder mViewHolder, int i) {
            mViewHolder.setTitle(String.valueOf(i));
            mViewHolder.bind(this.mDelstatus);
        }

        public void openDelstatus(boolean delstatus) {
            this.mDelstatus = delstatus;
        }
        @Override
        public int getItemCount() {
            return 20;
        }

    }
    private class InViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private String mtitle;
        private boolean mDelstatus = false;

        public InViewHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.main_bookshelf_fragment_1_item,container,false));
        }

        public void setTitle(String mtitle) {
            this.mtitle = mtitle;
        }

        public String getTitle() {
            return mtitle;
        }


        public void bind(boolean delstatus) {
            mImageView = (ImageView) itemView.findViewById(R.id.main_bookshelf_list_recyclerview_item_imageview);
            this.mDelstatus = delstatus;
            if(delstatus)
                mImageView.setImageResource(R.drawable.book_mask_select_bg);
            else
                mImageView.setImageDrawable(null);

            itemView.setOnClickListener(new View.OnClickListener() {
                private boolean mBoolean = false;
                @Override
                public void onClick(View view) {
                    mBoolean = !mBoolean;
                    if(mDelstatus)
                        if(mBoolean)
                            mImageView.setImageResource(R.drawable.book_mask_select_bg_select);
                        else
                            mImageView.setImageResource(R.drawable.book_mask_select_bg);
                }
            });
        }
    }
}
