package com.itheima.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itheima.service.bean.Book;
import com.itheima.service.dao.MyDB;

/**
 * Servlet implementation class getBookList
 */
//@WebServlet("/getBookList")
public class GetBookList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBookList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * http://192.168.43.166:8080/DemoWeb/getBookList
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// TODO Auto-generated method stub
		/**
		 *  +---------+--------------+------+-----+---------+-------+
			| Field   | Type         | Null | Key | Default | Extra |
			+---------+--------------+------+-----+---------+-------+
			| isbn    | varchar(255) | NO   | PRI | NULL    |       |
			| name    | varchar(255) | NO   |     | NULL    |       |
			| content | text         | NO   |     | NULL    |       |
			| count   | int(11)      | NO   |     | NULL    |       |
			+---------+--------------+------+-----+---------+-------+
		 */
		ArrayList<Book> bookArrayList = null;
		try {
			bookArrayList = MyDB.getBookList();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONArray jsonArray = new JSONArray();
		for(Book book:bookArrayList) {
			JSONObject json = new JSONObject();
				try {
					json.put("isbn",book.getISBN() );
					json.put("name", book.getName());
					json.put("content", book.getContent());
					json.put("count", book.getCount());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated catch block
			jsonArray.put(json);
		}
		response.getOutputStream().write(jsonArray.toString().getBytes("UTF-8"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
