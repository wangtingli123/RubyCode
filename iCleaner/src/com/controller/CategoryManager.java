package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CategoryDao;
import com.model.Category;
import com.model.Price;

/**
 * Servlet implementation class CategoryManager
 */
public class CategoryManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryManager() {
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
		if("addcategory".equals(request_type)){
			String cid = request.getParameter("cid").toString();
			String cname = request.getParameter("cname").toString();
			String cprice = request.getParameter("cprice").toString();
			Category cate = new Category();
			cate.setCid(cid);
			cate.setCname(cname);
			Price price = new Price();
			price.setPid(cprice);
			cate.setPrice(price);
			new CategoryDao().insertCategory(cate);
			response.sendRedirect("CategoryManager?type=getcatelist");
		} else if("getcatelist".equals(request_type)){
			System.out.println("????");
			ArrayList<Category> cateList = new CategoryDao().getCategoryList();
			request.setAttribute("cateList", cateList);
			request.getRequestDispatcher("getCateList.jsp").forward(request, response);
		} else if("getcatebyid".equals(request_type)){
			String cateid = request.getParameter("cateid").toString();
			Category cate = new CategoryDao().getCateById(cateid);
			request.setAttribute("cate", cate);
			request.getRequestDispatcher("modifycategory.jsp").forward(request, response);
		} else if("modifycate".equals(request_type)){
			String catepricegrade = request.getParameter("pricegrade").toString();
			String cateid = request.getParameter("cateid").toString();
			String catename = request.getParameter("catename").toString();
			Category cate = new Category();
			cate.setCid(cateid);
			Price price = new Price();
			price.setPid(catepricegrade);
			cate.setPrice(price);
			cate.setCname(catename);
			new CategoryDao().modifyCategory(cate);
			response.sendRedirect("CategoryManager?type=getcatelist");
		} else if("deletecate".equals(request_type)){
			String cateid = request.getParameter("cateid").toString();
			new CategoryDao().deleteCategory(cateid);
			response.sendRedirect("CategoryManager?type=getcatelist");
		}
	
	}

}
