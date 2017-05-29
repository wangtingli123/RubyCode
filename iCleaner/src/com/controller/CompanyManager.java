package com.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CompanyDao;
import com.model.Company;

/**
 * Servlet implementation class CompanyManager
 */
public class CompanyManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String request_type = request.getParameter("type").toString();
		if("createcompany".equals(request_type)){
			String compId = UUID.randomUUID().toString();
			String compName = request.getParameter("compname");
			String comPwd = request.getParameter("compwd");
			Company comp = new Company();
			comp.setCompId(compId);
			comp.setCompName(compName);
			comp.setCompPwd(comPwd);
			new CompanyDao().createCompany(comp);
			request.getRequestDispatcher("login3.html").forward(request, response);
		}else if("logincompany".equals(request_type)){
			String compname = request.getParameter("compname").toString();
			String compwd = request.getParameter("compwd").toString();
			Company comp = new Company();
			comp.setCompName(compname);
			comp.setCompPwd(compwd);
			Company company = new CompanyDao().doLogin(comp);
			if(company!=null){
				request.setAttribute("company", company);
				request.getRequestDispatcher("main2.html").forward(request, response);
			}else{
				request.getRequestDispatcher("login3.html").forward(request, response);
			}
		}
	}

}
