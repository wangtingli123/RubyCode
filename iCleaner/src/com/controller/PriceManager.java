package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PriceDao;
import com.model.Price;

/**
 * Servlet implementation class PriceManager
 */
public class PriceManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PriceManager() {
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
		if("getpricelist".equals(request_type)){
			ArrayList<Price> priceList = new PriceDao().getPriceList();
			request.setAttribute("priceList", priceList);
			request.getRequestDispatcher("getpricelist.jsp").forward(request, response);
		} else if("getpricebyid".equals(request_type)){
			String pid = request.getParameter("pid").toString();
			Price price = new PriceDao().getPriceById(pid);
			request.setAttribute("price", price);
			request.getRequestDispatcher("updateprice.jsp").forward(request, response);
		} else if("modifyprice".equals(request_type)){
			String pid = request.getParameter("pid").toString();
			String pricelow = request.getParameter("pricelow").toString();
			String pricehigh = request.getParameter("pricehigh").toString();
			Price price = new Price();
			price.setPid(pid);
			price.setPricelow(pricelow);
			price.setPricehigh(pricehigh);
			new PriceDao().modifyPrice(price);
			response.sendRedirect("PriceManager?type=getpricelist");
		}
	}

}
