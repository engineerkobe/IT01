package com.itheima.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.itheima.service.bean.Book;
import com.itheima.service.bean.RFID;
import com.itheima.service.dao.MyDB;

/**
 * Servlet implementation class BorrowingBooks
 */
@WebServlet("/GetBookForRFID")
public class GetBookForRFID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBookForRFID() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		String RFID = request.getParameter("RFID");
		
		Book book = null;
		RFID rfid = null;
		try {
			book = MyDB.getBookForRFID(RFID);
			rfid = MyDB.getRFID(RFID);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(book != null ) {
			try {
				JSONObject json = new JSONObject();
			/*
			 *  +----------------+--------------+------+-----+---------+-------+
				| Field          | Type         | Null | Key | Default | Extra |
				+----------------+--------------+------+-----+---------+-------+
				| rfid           | varchar(255) | NO   | PRI | NULL    |       |
				| is_borrow      | varchar(1)   | NO   |     | NULL    |       |
				| borrower       | varchar(255) | NO   |     | NULL    |       |
				| log            | datetime     | NO   |     | NULL    |       |
				| isbn           | varchar(255) | NO   | MUL | NULL    |       |
				| book_case_name | text         | YES  |     | NULL    |       |
				+----------------+--------------+------+-----+---------+-------+	
			 */
				json.put("isbn",book.getISBN() );
				json.put("name", book.getName());
				json.put("content", book.getContent());
				json.put("count", book.getCount());
				
				
				json.put("rfid",rfid.getRfid());
				json.put("is_borrow",rfid.getIsBorrow());
				json.put("borrower",rfid.getBorrower());
				json.put("log",rfid.getLog());
				json.put("book_case_name",rfid.getBook_case_name());
				
				json.put("servertime",new Date().getTime());
//				newsJson.put("COVER", book.getCOVER());
//				newsJson.put("BORROWER",book.getBORROWER());
//				newsJson.put("BOOKCASE", book.getBOOKCASE());
//				JSONObject allNewsJson = new JSONObject();
//				allNewsJson.put("newss", newsJson);
				//對使用者寫入字串
				System.out.println(this.getClass().getName()+":"+json.toString());
				response.getOutputStream().write(json.toString().getBytes("utf-8"));
			}catch(Exception e) {e.printStackTrace();}
		}else {
			System.out.println(this.getClass().getName()+":"+"Can't find");
			response.getOutputStream().write("Can't find".getBytes("utf-8"));
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
