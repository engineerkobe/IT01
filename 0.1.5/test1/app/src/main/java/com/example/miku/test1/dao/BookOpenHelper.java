package com.example.miku.test1.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookOpenHelper extends SQLiteOpenHelper {
    public BookOpenHelper(Context context) {
        super(context, "BookCase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
/*		newsJson.put("id", newsBean.getId());
		newsJson.put("title", newsBean.getTitle());
		newsJson.put("des", newsBean.getDes());
		newsJson.put("icon_url", newsBean.getIcon_url());
		newsJson.put("news_url", newsBean.getNews_url());
		newsJson.put("type", newsBean.getType());
		newsJson.put("time", newsBean.getTime());
		newsJson.put("comment", newsBean.getComment());*/
        //db.execSQL("create table book (_id integer  ,title varchar(200),des varchar(300),icon_url varchar(200),news_url varchar(200)," +
                //" type integer , time varchar(100),comment integer)");
        db.execSQL("CREATE TABLE BOOK ( ISBN TEXT NOT NULL, NAME TEXT NOT NULL, COVER TEXT NOT NULL, Borrower TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
}
