package com.itheima.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.service.bean.UserInfo;
import com.itheima.service.dao.MyDB;

/**
 * Servlet implementation class JoinAccount
 */
@WebServlet("/JoinAccount")
public class JoinAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		response.setContentType("charset=utf-8");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		UserInfo userinfo = null;
		try {
			userinfo = MyDB.getUserInfo(account);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(userinfo != null) {
			System.out.println(account+"已經存在");
			response.getOutputStream().write("The account is existentce".getBytes("UTF-8"));
		} else {
			System.out.println(account+"創建");
			MyDB.addUserInfo(account,password);
			response.getOutputStream().write("The account was created successfully".getBytes("UTF-8"));
		}
//		request.getParameter()
	}

}
