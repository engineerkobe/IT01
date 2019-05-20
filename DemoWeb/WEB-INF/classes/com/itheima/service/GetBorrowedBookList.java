package com.itheima.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itheima.service.bean.Book;
import com.itheima.service.dao.MyDB;

/**
 * Servlet implementation class GetBorrowedBook
 */
@WebServlet("/GetBorrowedBookList")
public class GetBorrowedBookList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBorrowedBookList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String account = request.getParameter("account");
		System.out.println(account);
		ArrayList<Book> bookArrayList = null;
		try {
			bookArrayList = MyDB.getBorrowedBookList(account);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (bookArrayList != null) {
			JSONArray jsonArray = new JSONArray();
			for (Book book : bookArrayList) {
				JSONObject json = new JSONObject();
				try {
					json.put("isbn", book.getISBN());
					json.put("name", book.getName());
					json.put("content", book.getContent());
					json.put("count", book.getCount());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jsonArray.put(json);
			}
			response.getOutputStream().write(jsonArray.toString().getBytes("UTF-8"));
		}else {
			response.getOutputStream().write("NoArrayBorrowedBook".getBytes("UTF-8"));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
