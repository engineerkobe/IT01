package com.example.miku.test1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.miku.test1.ItemFragment.OnListFragmentInteractionListener;
import com.example.miku.test1.R;
import com.example.miku.test1.bean.Book;
import com.example.miku.test1.dummy.DummyContent.DummyItem;
import com.example.miku.test1.view.MyImageView;

import java.util.ArrayList;
import java.util.List;

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
        holder.mIdView.setText(mValues.get(position).getISBN());
        holder.mContentView.setText(mValues.get(position).getBORROWER());
        Book book = mValues.get(position);
        String tmp = "http://192.168.43.166:8080/DemoWeb/GetBookImage?dir=Cover&img="+book.getCOVER();
        holder.myImageView.setImageUrl(tmp);
        System.out.print(position);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final MyImageView myImageView;
        public final TextView item_tv_title;
        public Book mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            myImageView = (MyImageView) view.findViewById(R.id.item_img_icon);
            item_tv_title = (TextView) view.findViewById(R.id.item_number);
//        TextView item_tv_comment = (TextView) view.findViewById(R.id.item_tv_comment);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
    /*
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = null;
        //1.复用converView优化listview,创建一个view作为getview的返回值用来显示一个条目
        if(convertView != null){
            view = convertView;
        }else {
            //context:上下文, resource:要转换成view对象的layout的id, root:将layout用root(ViewGroup)包一层作为codify的返回值,一般传null
//			view = View.inflate(context, R.layout.activity_book__list_, null);//将一个布局文件转换成一个view对象
            //通过LayoutInflater将布局转换成view对象
//			view =  LayoutInflater.from(context).inflate(R.layout.activity_book__list_, null);
            //通过context获取系统服务得到一个LayoutInflater，通过LayoutInflater将一个布局转换为view对象
            //LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = layoutInflater.inflate(R.layout.fragment_item, null);
        }
        //2.获取view上的子控件对象
        MyImageView item_img_icon = (MyImageView) view.findViewById(R.id.item_img_icon);
        //TextView item_tv_des = (TextView) view.findViewById(R.id.item_tv_des);
        TextView item_tv_title = (TextView) view.findViewById(R.id.item_number);
//        TextView item_tv_comment = (TextView) view.findViewById(R.id.item_tv_comment);
//        TextView item_tv_type = (TextView) view.findViewById(R.id.item_tv_type);


        //3.获取postion位置条目对应的list集合中的新闻数据，Bean对象
        Book book = list.get(position);
        String tmp = "http://192.168.43.166:8080/DemoWeb/GetBookImage?dir=Cover&img="+book.getCOVER();
        item_img_icon.setImageUrl(tmp);
        //4.将数据设置给这些子控件做显示
        //item_img_icon.setIm
        item_tv_title.setText(book.getNAME());
//        item_tv_des.setText(book.getbook_name());
        //item_tv_comment.setText("评论："+newsBean.comment);




        return view;
    }
    */
}
