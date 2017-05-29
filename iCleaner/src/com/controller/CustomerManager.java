package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CustomerDao;
import com.model.Customer;

/**
 * Servlet implementation class CustomerManager
 */
public class CustomerManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerManager() {
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
        HttpSession  session=request.getSession();
        session.setMaxInactiveInterval(60*500);
		if("customerregister".equals(request_type)){
			String customerid = request.getParameter("customerid");
			String customername = request.getParameter("customername");
			String customerpwd = request.getParameter("customerpwd");
			Customer customer = new Customer();
			customer.setCustomerid(customerid);
			customer.setCustomername(customername);
			customer.setCustomerpwd(customerpwd);
			new CustomerDao().customerRegister(customer);
			request.getRequestDispatcher("login4.html").forward(request, response);
		}else if("customerlogin".equals(request_type)){
			String customerid = request.getParameter("customerid");
			String customerpwd = request.getParameter("customerpwd");
			Customer cus = new Customer();
			cus.setCustomerid(customerid);
			cus.setCustomerpwd(customerpwd);
			Customer cust = new CustomerDao().doLogin(cus);
			if(cust!=null){
				session.setAttribute("cust", cust);
				request.getRequestDispatcher("main4.html").forward(request, response);
			}else{
				request.getRequestDispatcher("login4.html").forward(request, response);
			}
		}
	}

}
