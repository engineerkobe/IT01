package com.itheima.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.itheima.service.bean.Book;
import com.itheima.service.dao.MyDB;

/**
 * Servlet implementation class BorrowingBooks
 */
@WebServlet("/BorrowingBooks")
public class BorrowingBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowingBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		String UID= request.getParameter("UID");
		String ISBN = request.getParameter("ISBN");
		
		Book book = null;
		try {
			book = MyDB.getBook(ISBN,UID);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(book != null ) {
			try {
				JSONObject newsJson = new JSONObject();
				newsJson.put("ISBN", book.getISBN());
//				newsJson.put("NAME", book.getNAME());
//				newsJson.put("COVER", book.getCOVER());
//				newsJson.put("BORROWER",book.getBORROWER());
//				newsJson.put("BOOKCASE", book.getBOOKCASE());
//				JSONObject allNewsJson = new JSONObject();
//				allNewsJson.put("newss", newsJson);
				//對使用者寫入字串
				System.out.println(this.getClass().getName()+":"+newsJson.toString());
				response.getOutputStream().write(newsJson.toString().getBytes("utf-8"));
			}catch(Exception e) {e.printStackTrace();}
		}else {
			System.out.println(this.getClass().getName()+":"+"沒這本書");
			response.getOutputStream().write("NoBook".getBytes("utf-8"));
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
