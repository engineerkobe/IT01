package com.itheima.service;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String account = request.getParameter("poster");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		/*
		Map<String,String[]>parameterMap =  request.getParameterMap();
		for(Entry<String,String[]>tmp : parameterMap.entrySet()) {
			System.out.println(tmp.getKey()+":");
			response.getOutputStream().write((tmp.getKey()+":").getBytes("UTF-8"));
			for(String tmp2 :tmp.getValue()) {
				System.out.print(tmp2);
				response.getOutputStream().write(tmp2.getBytes("UTF-8"));
			}
			System.out.println();
		}
		*/
	}
}
