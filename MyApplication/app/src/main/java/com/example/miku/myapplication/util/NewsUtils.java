package com.example.miku.myapplication.util;

import android.content.Context;

import com.example.miku.myapplication.bean.Book;
import com.example.miku.myapplication.dao.BookDaoUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NewsUtils {


	public static String newsPath_url = "http://192.168.43.166:8080/DemoWeb/GetNewsServlet";
	//封装新闻的假数据到list中返回
	public static ArrayList<Book> getAllNewsForNetWork(Context context) {
		ArrayList<Book> arrayList = new ArrayList<Book>();
		try{
			//1.请求服务器获取新闻数据
			//获取一个url对象，通过url对象得到一个urlconnnection对象
			URL url = new URL(newsPath_url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			//设置连接的方式和超时时间
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(10*1000);
			//获取请求响应码
			int code = connection.getResponseCode();
			if(code == 200){
				//获取请求到的流信息
				InputStream inputStream = connection.getInputStream();
				String result = StreamUtils.streamToString(inputStream);

				//2.解析获取的新闻数据到List集合中。

					JSONObject root_json = new JSONObject(result);//将一个字符串封装成一个json对象。
					JSONArray jsonArray = root_json.getJSONArray("newss");//获取root_json中的newss作为jsonArray对象

					for (int i = 0 ;i < jsonArray.length();i++){//循环遍历jsonArray
						JSONObject Json_book = jsonArray.getJSONObject(i);//获取一条新闻的json
						Book books = new Book();
						books.setISBN(Json_book.getString("ISBN"));
						books.setNAME(Json_book.getString("NAME"));
						books.setCOVER( Json_book.getString("COVER"));
						books.setBORROWER(Json_book.getString("BORROWER"));
						arrayList.add(books);

					}

				//3.清楚数据库中旧的数据，将新的数据缓存到数据库中
					new BookDaoUtils(context).delete();
					new BookDaoUtils(context).saveNews(arrayList);
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	//从数据库中获取上次缓存的新闻数据做listview的展示
	public  static ArrayList<Book> getAllNewsForDatabase(Context context) {
		
		return new BookDaoUtils(context).getNews();

	}
}
