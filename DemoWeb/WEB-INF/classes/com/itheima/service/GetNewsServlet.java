package com.itheima.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.itheima.service.bean.Book;
import com.itheima.service.dao.MyDB;


public class GetNewsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetNewsServlet() {
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
		//強制使用者用utf8
		response.setContentType("text/html;charset=UTF-8");
		//http://192.168.43.166:8080/DemoWeb/GetNewsServlet
		/*

	{
	    "newss": [
	        {
	            "id": 2,
	            "time": "2015-08-07",
	            "des": "7��29�գ�����9�������������ڲ�����֮��΢�����ڷ���Win10��ʽ��ϵͳ�����ǿ��ܶ��ڲ����û����ԣ�Win7��Ȼ�Ǿ��Եľ��䡢��Ϸ��ҵĲ���֮ѡ��Ϊ�η�Ҫ������Win10ϵͳ�أ�Windows10���ܺ͹������Windows7��������������IT֮�Ҿ�Ϊ��Ҵ���Win7��Win10���������ܵ�����PK�����Ż�����ԥ�������û����걾������ͻ����˴𰸡�",
	            "title": "�����ǲ�����Win7��Win10ȫ��Ա�����",
	            "news_url": "http://toutiao.com/a5229867988/",
	            "icon_url": "http://p2.pstatp.com/large/6850/6105376239",
	            "comment": 5000,
	            "type": 1
	        },
	        {
	            "id": 1,
	            "time": "2015-08-09",
	            "des": "ƻ��iPhone6s�����ľ���ʱ��Խ��Խ���ˣ�������û�йٷ���׼�Ŷ������컹�������ǹ�עһ�»��©���ɡ���������������Mozilla�����û��������������°汾����û�Ҫ�޸�����͵�¼��Ϣ��",
	            "title": "ƻ������9��9�շ���iPhone6s ���������©��",
	            "news_url": "http://m.jiemian.com/article/347958.html",
	            "icon_url": "http://img.jiemian.com/101/original/20150808/143899303035536900_a580x330.jpg",
	            "comment": 1200,
	            "type": 3
	        },
	        {
	            "id": 0,
	            "time": "2015-08-10",
	            "des": "��˿Ƽ�Ѷ 8��8����Ϣ�����������ѱ��ϣ�������ʼ�˼�CEO��ǿ�������̲������������ڳ�������������֤��顣",
	            "title": "��ǿ�����̲�������֤��� ��ͼΪ֤",
	            "news_url": "http://i.ifeng.com/news/sharenews.f?aid=100435430",
	            "icon_url": "http://d.ifengimg.com/mw604/y3.ifengimg.com/ifengimcp/pic/20150808/ce1b80056cfc584fafbf_size20_w450_h800.jpg",
	            "comment": 3000,
	            "type": 2
	        }
	    ]
	}
	


		 */

	            /*
	             * 資料表|"ISBN"|"NAME"|"COVER"|"Borrower"|
	             * ----|------|------|-------|----------|
	             * 	    number text	  Blob	  Text
	            */
		try{
			ArrayList<Book> news = MyDB.getBookList();
			JSONArray jsonArray = new JSONArray();
			for (Book newsBean : news) {
				JSONObject newsJson = new JSONObject();
				newsJson.put("ISBN", newsBean.getISBN());
//////				newsJson.put("NAME", newsBean.getNAME());
////				newsJson.put("COVER", newsBean.getCOVER());
//				newsJson.put("BORROWER",newsBean.getBORROWER());
//				newsJson.put("BOOKCASE", newsBean.getBOOKCASE());
				jsonArray.put(newsJson);
			}

			JSONObject allNewsJson = new JSONObject();
			allNewsJson.put("newss", jsonArray);
			//對使用者寫入字串
			System.out.println("com.itheima.service.GetNewsSerlet:"+allNewsJson.toString());
			response.getOutputStream().write(allNewsJson.toString().getBytes("utf-8"));
			
		}catch (Exception e) {
			e.printStackTrace();
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
