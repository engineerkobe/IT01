package com.itheima.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploaderServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UploaderServlet() {
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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		//�����ж�һ�� �ϴ��������Ǳ����ݻ���һ�����ļ������� 
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {   //���Ϊtrue ˵����һ�������ļ�������
			//�õ�servlet����ʵ·�� 
			String realpath = request.getSession().getServletContext().getRealPath("/files");
			//��ӡһ��·��
			System.out.println("realpath-"+realpath);
			File dir = new File(realpath);
			if (!dir.exists())
				dir.mkdirs(); //���Ŀ¼������ �����Ŀ¼���������� 
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory); //��ȡ���ϴ��ļ��Ķ���upload
			upload.setHeaderEncoding("UTF-8");
			try {
				//�ж�һ���ϴ�����������
				List<FileItem> items = upload.parseRequest(request);
				for (FileItem item : items) {
					if (item.isFormField()) { //�ϴ����������� ��һ��������
						String name1 = item.getFieldName();// �õ��������������
						String value = item.getString("UTF-8");// �õ�����ֵ
						System.out.println(name1 + "=" + value);
					} else {
						item.write(new File(dir, System.currentTimeMillis()
								+ item.getName().substring(item.getName().lastIndexOf("."))));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
			}
		}
	}
	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
}
