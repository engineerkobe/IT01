package com.example.miku.test1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;

import com.example.miku.test1.bean.Book;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BookDaoUtils {
    private BookOpenHelper newsOpenHelper;

    public BookDaoUtils(Context context){

        //创建一个帮助类对象
        newsOpenHelper = new BookOpenHelper(context);

    }

    //删除数据库中缓存的旧数据
    public void delete(){

        //通过帮助类对象获取一个数据库操作对象
        SQLiteDatabase db = newsOpenHelper.getReadableDatabase();
        db.delete("book", null, null);
        db.close();
    }

    //向数据库中添加新闻数据
    public void saveNews(ArrayList<Book> list){

        //通过帮助类对象获取一个数据库操作对象
        SQLiteDatabase db = newsOpenHelper.getReadableDatabase();
        for (Book book : list) {
            ContentValues values = new ContentValues();
            values.put("ISBN",book.getISBN() );
            values.put("NAME", book.getNAME());
            values.put("COVER", book.getCOVER());
            db.insert("book", null, values);

        }

        db.close();
    }

    //从数据库中获取缓存的新闻数据
    public ArrayList<Book> getNews(){
        ArrayList<Book> list = new ArrayList<Book>();
        //通过帮助类对象获取一个数据库操作对象
        SQLiteDatabase db = newsOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from book", null);//查询获取数据

        if(cursor != null && cursor.getCount() > 0){

            while(cursor.moveToNext()){

                Book book = new Book();
                book.setISBN(cursor.getString(0));
                book.setNAME(cursor.getString(1));
                book.setCOVER(cursor.getString(2));
                //book.setBORROWER(cursor.getString(3));

                list.add(book);
            }
        }

        db.close();
        cursor.close();


        return list;

    }



}
