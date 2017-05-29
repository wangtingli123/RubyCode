package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AdminDao;
import com.model.Admin;

/**
 * Servlet implementation class AdminManager1
 */
public class AdminManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String request_type = request.getParameter("type").toString();
		if("adminlogin".equals(request_type)){
			String adminId = request.getParameter("adminId").toString();
			String adminPwd = request.getParameter("adminPwd").toString();
			Admin admin = new Admin();
			admin.setAdminId(adminId);
			admin.setAdminPwd(adminPwd);
			Admin adm = new AdminDao().doLogin(admin);
			if(adm!=null){
				request.getRequestDispatcher("main.html").forward(request, response);
			}else{
				request.getRequestDispatcher("login1.html").forward(request, response);
			}
		}
	
	}

}
