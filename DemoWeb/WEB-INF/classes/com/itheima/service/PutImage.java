package com.itheima.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;



/**
 * Servlet implementation class PutImage
 */
@WebServlet("/PutImage")
public class PutImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PutImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		InputStream imageIS = request.getInputStream();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
//		showHeader(request);
		
//		showFiled(request);
//		System.out.println(request.getHeader("name"));
//		System.out.println(request.getParameter("text"));
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		ServletContext sc = request.getServletContext();
		
		String path = sc.getRealPath("Image/");
		System.out.println(path);
		File tree = new File(path);
		dfif.setRepository(tree);
		ServletFileUpload sf = new ServletFileUpload(dfif);
		try {
			showFiles(sf,request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().append("Photo upload succeeded").flush();
	}
	
	private void showFiles(ServletFileUpload sf, HttpServletRequest request) throws IOException, FileUploadException {
		// TODO Auto-generated method stub
		ServletContext sc = request.getServletContext();
		String path = sc.getRealPath("Image/HeadSticker/").toString();
		FileItemIterator tmp = sf.getItemIterator(request);
		
		while (tmp.hasNext()) {
			FileItemStream fis = tmp.next();
			if (fis.isFormField() && fis.getFieldName().equals("text")) {
				InputStreamReader isr=new InputStreamReader(fis.openStream(),"UTF-8");
				BufferedReader br = new BufferedReader(isr);
				System.out.println(br.readLine());
				br.close();
			} else {
				try (InputStream is = fis.openStream()) {
					Files.copy(is, Paths.get(path + fis.getName()), StandardCopyOption.REPLACE_EXISTING);
				}
				System.out.println(fis.getName());
			}
		}
	}

	private void showFileItem(FileItemStream fis) {
		// TODO Auto-generated method stub
		Iterator<String> fisHeader = fis.getHeaders().getHeaderNames();
		System.out.println(fis.getFieldName());
		System.out.println(fis.getContentType());
		System.out.println(fis.getName());
		while (fisHeader.hasNext()) {
			String tmp = fisHeader.next();
			System.out.print(tmp + ":");
			Iterator<String> fisit = fis.getHeaders().getHeaders(tmp);
			while (fisit.hasNext()) {
				System.out.println(fisit.next());
			}
		}
	}

	private void showFiled(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Set<Entry<String, String[]>> postMap = request.getParameterMap().entrySet();
		for(Entry<String, String[]> tmp:postMap) {
			for(String tmp2:tmp.getValue()) {
				System.out.println(tmp.getKey()  +tmp2);
			}
		}
	}
	private void showHeader(HttpServletRequest request) {
		Enumeration<String> headerEnmeration = request.getHeaderNames();
		while(headerEnmeration.hasMoreElements()) {
			String tmp = headerEnmeration.nextElement();
			System.out.print(tmp +":");
			System.out.println(request.getHeader(tmp));
		}
	}

}
