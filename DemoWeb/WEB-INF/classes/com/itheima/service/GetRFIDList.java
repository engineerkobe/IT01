package com.itheima.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.itheima.service.bean.RFID;
import com.itheima.service.dao.MyDB;

/**
 * Servlet implementation class GetRFIDList
 */
@WebServlet("/GetRFIDList")
public class GetRFIDList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRFIDList() {
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
		String ISBN = request.getParameter("ISBN");
		
//		Book book = null;
		List<RFID> rfids = null;
		try {
//			book = MyDB.getBookForRFID(RFID);
			rfids = MyDB.getRFIDList(RFID);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(rfids != null ) {
			try {
				JSONArray jsonArray = new JSONArray();
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
				for(int i=0; i<rfids.size(); i++) {
					RFID rfid = rfids.get(i);
					JSONObject json = new JSONObject();
					json.put("rfid",rfid.getRfid());
					json.put("is_borrow",rfid.getIsBorrow());
					json.put("borrower",rfid.getBorrower());
					json.put("log",rfid.getLog());
					json.put("isbn",rfid.getIsbn());
					json.put("book_case_name",rfid.getBook_case_name());
					jsonArray.put(json);
				}
				//對使用者寫入字串
				System.out.println(this.getClass().getName()+":"+jsonArray.toString());
				response.getOutputStream().write(jsonArray.toString().getBytes("utf-8"));
			}catch(Exception e) {e.printStackTrace();}
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
