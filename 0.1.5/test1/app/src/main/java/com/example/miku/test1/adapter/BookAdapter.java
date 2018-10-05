package com.example.miku.test1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.miku.test1.R;
import com.example.miku.test1.bean.Book;
import com.example.miku.test1.view.MyImageView;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {

    private ArrayList<Book> list;
    private Context context;

    //通过构造方法接受要显示的新闻数据集合
    public BookAdapter(Context context,ArrayList<Book> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
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
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.fragment_item, null);
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

}
