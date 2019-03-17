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

import com.itheima.service.bean.Article;
import com.itheima.service.dao.MyDB;

/**
 * Servlet implementation class GetArticle
 */
@WebServlet("/GetArticle")
public class GetArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
		ArrayList<Article> articleArrayList = null;
		try {
			articleArrayList = MyDB.getArticleList();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		 * 
		 */
		JSONArray jsonarray = new JSONArray();
		for(Article article:articleArrayList) {
			JSONObject jsonobject = new JSONObject();
				try {
					jsonobject.put("poster",article.getPoster());
					jsonobject.put("pid",article.getPid());
					jsonobject.put("rootid",article.getRootid());
					jsonobject.put("content",article.getContent());
					jsonobject.put("title",article.getTitle());
					jsonobject.put("pdata",article.getPdate());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated catch block
			jsonarray.put(jsonobject);
		}
		
		response
		.getOutputStream()
		.write(jsonarray.toString().getBytes("UTF-8"));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
