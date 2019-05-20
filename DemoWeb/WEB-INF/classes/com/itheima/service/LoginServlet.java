package com.itheima.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.itheima.service.bean.Entity;
import com.itheima.service.bean.UserInfo;
import com.itheima.service.dao.MyDB;
import com.itheima.service.user.GetUser;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//強制用戶網頁使用UTF8
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		
		System.out.println("DemoWeb.LoginServlet.username:"+username);
		System.out.println("DemoWeb.LoginServlet.password:"+pwd);
		UserInfo userinfo = null;
		if(username != null && 
		   pwd != null &&
		   username.matches("[A-Za-z0-9]+") &&
		   pwd.matches("[A-Za-z0-9]+")){
			if((userinfo = MyDB.getUserInfo(username, pwd))!= null){
				JSONObject json  = null;
				try {
					json = Entity.UserInfoToJson(userinfo);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				response.getOutputStream().write("login success".getBytes());  //gbk����
				response.getOutputStream().write(json.toString().getBytes());  //gbk����
				
			}else{
				response.getOutputStream().write("login faile".getBytes());	
			}
		}else{
			response.getOutputStream().write("login fail; username or password is null".getBytes());	
		}
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
