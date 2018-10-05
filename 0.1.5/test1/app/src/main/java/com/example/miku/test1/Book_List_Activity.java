package com.example.miku.test1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.example.miku.test1.adapter.BookAdapter;
import com.example.miku.test1.bean.Book;
import com.example.miku.test1.util.NewsUtils;

import java.util.ArrayList;

public class Book_List_Activity extends Activity {
    private Context mContext;
    private ListView lv_book;
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {

            ArrayList<Book> allNews = (ArrayList<Book>) msg.obj;

            if(allNews != null && allNews.size()>0)
            {
                //3.创建一个adapter设置给listview
                BookAdapter newsAdapter = new BookAdapter(mContext, allNews);
                lv_book.setAdapter(newsAdapter);
            }

        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a);
        mContext = this;
        lv_book = (ListView) findViewById(R.id.lv_book);
        ArrayList<Book> allBook = NewsUtils.getAllNewsForNetWork(mContext);
        if(allBook !=null && allBook.size()>0) {
            BookAdapter bookAdapter = new BookAdapter(mContext, allBook);
            lv_book.setAdapter(bookAdapter);
        }
    }

}
