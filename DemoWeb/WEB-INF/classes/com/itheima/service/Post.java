package com.itheima.service;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.service.dao.MyDB;

/**
 * Servlet implementation class Post
 */
@WebServlet("/Post")
public class Post extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Post() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
//		doGet(request, response);
//		request.getParameter("username");
		/*
		 *  +---------+--------------+------+-----+---------+-------+
			| Field   | Type         | Null | Key | Default | Extra |
			+---------+--------------+------+-----+---------+-------+
			| poster  | varchar(255) | YES  | MUL | NULL    |       |
			| rootid  | int(11)      | YES  |     | NULL    |       |
			| pid     | int(11)      | YES  |     | NULL    |       |
			| title   | varchar(255) | YES  |     | NULL    |       |
			| content | text         | YES  |     | NULL    |       |
			| pdate   | datetime     | YES  |     | NULL    |       |
			+---------+--------------+------+-----+---------+-------+
		 */
		String function = request.getParameter("function");
		System.out.println(function);
		switch(function) {
		case "setUserName":
			setUserName(request,response);
			break;
		case "postArticle":
			postArticle(request,response);
			break;
		}
	}
	
	private void setUserName(HttpServletRequest request, HttpServletResponse response) {
		String account = request.getParameter("account");
		String name = request.getParameter("name");
		System.out.println(account);
		System.out.println(name);
		MyDB.setUserName(account, name);
	}
	private void postArticle(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String account = request.getParameter("account");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String rootid = request.getParameter("rootid");
		System.out.println(account);
		System.out.println(title);
		System.out.println(content);
		if(rootid == null)
			MyDB.addArticle(account,title,content);
		else
			MyDB.addArticle(account,title,content,rootid);
		/*
		Map<String,String[]> par = request.getParameterMap();
		Set<Entry<String,String[]>>set = par.entrySet();
		for(Entry<String,String[]> entry: set) {
			System.out.print(entry.getKey() +" : ");
			for(String tmp :entry.getValue()) {
				System.out.print(tmp + ",");
			}
			System.out.println();
		}
		*/
	}
}
