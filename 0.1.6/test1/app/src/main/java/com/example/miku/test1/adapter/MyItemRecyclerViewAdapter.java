package com.example.miku.test1.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miku.test1.Book_List_Activity;
import com.example.miku.test1.FragmentMain;
import com.example.miku.test1.FragmentSpinner.ItemFragment.OnListFragmentInteractionListener;
import com.example.miku.test1.ItemActivity;
import com.example.miku.test1.R;
import com.example.miku.test1.bean.Book;
import com.example.miku.test1.dummy.DummyContent.DummyItem;
import com.example.miku.test1.view.MyImageView;

import java.util.ArrayList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Book> mValues;
    private final OnListFragmentInteractionListener mListener;
    public MyItemRecyclerViewAdapter(ArrayList<Book> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getNAME().trim());
        holder.mIdView.setText(mValues.get(position).getISBN());
        holder.mContentView.setText(mValues.get(position).getBORROWER());
        holder.setISBN(mValues.get(position).getISBN());
        Book book = mValues.get(position);
        String tmp = "http://192.168.43.166:8080/DemoWeb/GetBookImage?dir=Cover&img="+book.getCOVER();
        holder.myImageView.setImageUrl(tmp);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final MyImageView myImageView;
        public String ISBN;
//        public final TextView item_tv_title;
        public Book mItem;
        public Context mContext;
        public ViewHolder( View view) {
            super(view);
            mView = view;
            mContext = view.getContext();
            mTitleView = (TextView) view.findViewById(R.id.item_tv_title);
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            myImageView = (MyImageView) view.findViewById(R.id.item_img_icon);
//            item_tv_title = (TextView) view.findViewById(R.id.item_number);

//        TextView item_tv_comment = (TextView) view.findViewById(R.id.item_tv_comment);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"clicked="+ mIdView.getText().toString(),Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(mContext,ItemActivity.class);
                    intent.putExtra("ISBN",ISBN);
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
        public void setISBN(String isbn) {ISBN=isbn;}
    }
    /*
    }
    */
}
