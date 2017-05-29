package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CategoryDao;
import com.dao.ProductDao;
import com.model.Category;
import com.model.Price;
import com.model.Product;

/**
 * Servlet implementation class ProductManager
 */
public class ProductManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */ 
    public ProductManager() {
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
		System.out.println("YYYYYYYYYYYYYYYY"+request_type);
		if("addproduct".equals(request_type)){
			CategoryDao cateDao = new CategoryDao();
			String proid = request.getParameter("proid").toString();
			String proname = request.getParameter("proname").toString();
			String proprice = request.getParameter("proprice").toString();
			String procateid = request.getParameter("procateid").toString();//品类id
			Product product = new Product();
			product.setProid(proid);
			product.setProname(proname);
			product.setProduct_price(proprice);
			Category category = new Category();
			category.setCid(procateid);
			product.setCategory(category);
			new ProductDao().insertProduct(product);//服务端不再做验证
			response.sendRedirect("ProductManager?type=getproductlist");
		}else if("getcategory".equals(request_type)){
			//调用查找所有品类的方法
			ArrayList<Category> catelist = new CategoryDao().getCategoryList();
			request.setAttribute("catelist", catelist);
			request.getRequestDispatcher("addproduct.jsp").forward(request, response);
		}else if("verifyprice".equals(request_type)){
			//根据品类id查到该品类的最低最高价格
			String[] values=request.getParameter("idprice").toString().split(",");
			Category cate = new CategoryDao().getCateById(values[0]); 
			int proprice=Integer.parseInt(values[1].toString());
			int pricelow = Integer.parseInt(cate.getPrice().getPricelow());//biaozhun 
			int pricehigh = Integer.parseInt(cate.getPrice().getPricehigh());
			String ans=proprice<pricelow?"lower":(proprice>pricehigh?"higher":"proper");
			PrintWriter pw=response.getWriter();
			System.out.println(ans+"=================");
			pw.write(ans);
		}else if("getproductlist".equals(request_type)){
			ArrayList<Product> productList = new ProductDao().getProductList();
			request.setAttribute("productList", productList);
			request.getRequestDispatcher("getproductlist.jsp").forward(request, response);
		}else if("deleteproduct".equals(request_type)){
			String proid = request.getParameter("proid");
			new ProductDao().deleteProduct(proid);
			response.sendRedirect("ProductManager?type=getproductlist");
		}else if("getproductbyid".equals(request_type)){
			String productid = request.getParameter("proid").toString();
			Product product = new ProductDao().getProductById(productid);
			request.setAttribute("product", product);
			request.getRequestDispatcher("getproduct.jsp").forward(request, response);
		}else if("modifyproduct".equals(request_type)){
			String proid = request.getParameter("proid").toString();
			String proname = request.getParameter("proname").toString();
			String proprice = request.getParameter("proprice").toString();
			Product pro = new Product();
			pro.setProname(proname);
			pro.setProid(proid);
			pro.setProduct_price(proprice);
			new ProductDao().modifyProduct(pro);
			response.sendRedirect("ProductManager?type=getproductlist");
		}
	}

}
