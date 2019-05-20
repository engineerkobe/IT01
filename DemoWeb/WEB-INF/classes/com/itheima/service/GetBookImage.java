package com.itheima.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class GetBookImage
 */
@WebServlet("/GetBookImage")
public class GetBookImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBookImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * http://192.168.43.166:8080/DemoWeb/GetBookImage?dir=Image&img=1
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//			response.setContentType("image/png");
			response.setContentType("charset=utf-8");
	        ServletContext sc = getServletContext();
	        String ImageDir = request.getParameter("dir");
	        String ImageName = request.getParameter("img");
	        System.out.println("DemoWeb.LoginServlet.GetBookImage.getImage:"+ImageDir+"/"+ImageName+".png");
	        //
	        File file = new File("Image/" +ImageDir+"/"+ImageName+".png");
	        if(file.exists()) {
	        	response.getOutputStream().write("image is null".getBytes("UTF-8"));
	        }else {
	        	InputStream is = sc.getResourceAsStream(file.getPath());
	        	//InputStream is = sc.getResourceAsStream("Image/2.PNG");
	        		
	        	BufferedImage bi = ImageIO.read(is);
	        	OutputStream os = response.getOutputStream();
	        	ImageIO.write(bi, "png", os);
	        
	        	bi.flush();
	        	is.close();
	        	os.close();
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
