package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CleanStatusDao;
import com.dao.OrderDao;
import com.dao.ProductDao;
import com.dao.StationDao;
import com.model.CleanStatus;
import com.model.Customer;
import com.model.Order;
import com.model.PayStatus;
import com.model.Product;
import com.model.Station;

/**
 * Servlet implementation class OrderManager
 */
public class OrderManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderManager() {
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
		Customer cust = (Customer) session.getAttribute("cust");
		if("addpayedorder".equals(request_type)){
			//这是已支付的订单下单
			String orderId = UUID.randomUUID().toString();
			String productTypeId = request.getParameter("productTypeId").toString();//获取下单商品的类别
			String productPrice = request.getParameter("proprice").toString();//价格
			String orderAddress = request.getParameter("orderaddr").toString();//订单地址
			String stationId = request.getParameter("stationId").toString();
			String cleanStatus = request.getParameter("cleanstatus").toString();//默认选择未加工
			String payStatus = "2";//2为已支付，支付状态在页面没有显示（页面要通过按钮来区分）
			Order order = new Order();
			order.setOrderId(orderId);
			Product p = new Product();
			p.setProid(productTypeId);
			order.setProduct(p);
			order.setTotalPrice(productPrice);
			order.setOrderAddr(orderAddress);
			CleanStatus cs = new CleanStatus();
			cs.setCleanStatusId(cleanStatus);
			order.setCleanStatus(cs);
			PayStatus ps = new PayStatus();
			ps.setPayStatusId(payStatus);
			order.setPayStatus(ps);
			Station s = new Station();
			s.setStation_id(stationId);
			order.setStation(s);
			order.setCustomer_order_id(cust.getCustomerid());
			//以下就是调用插入的方法
			new OrderDao().createOrder(order);
			response.sendRedirect("OrderManager?type=getorderlist");
		}else if("addunpayedstatus".equals(request_type)){
			//这是未支付的订单
			
		}else if("getproductprice".equals(request_type)){
			//先实现这部分
			String productTypeId = request.getParameter("protypeid").toString();//获取下单商品的类别
			Product pro = new ProductDao().getProductById(productTypeId);
			String productPrice = pro.getProduct_price();
			PrintWriter pw = response.getWriter();
			pw.write(productPrice);
		}else if("getproductlist".equals(request_type)){
			ArrayList<Product> productList = new ProductDao().getProductList();
			//同时要在这个获取加工状态
			ArrayList<CleanStatus> cslist = new CleanStatusDao().getCleanStatusList();
			//获取站点列表
			ArrayList<Station> stationlist = new StationDao().getStationList();
			request.setAttribute("productList", productList);
			request.setAttribute("cslist", cslist);
			request.setAttribute("stationlist", stationlist);
			request.getRequestDispatcher("addorder.jsp").forward(request, response);
		}else if("getorderlist".equals(request_type)){
			ArrayList<Order> orderlist = new OrderDao().getOrderList1(cust.getCustomerid());
//			for(Order each:orderlist){
//				System.out.println("$$$$$"+each.getStation().getStation_name());
//			}
			request.setAttribute("orderlist", orderlist);
			request.getRequestDispatcher("getorderlist.jsp").forward(request, response);
		}else if("pickup".equals(request_type)){
			ArrayList<Order> orderlist = new OrderDao().getOrderList();//先暂时写成这，之后你再写个查询所，复制而已
			request.setAttribute("orderlist", orderlist);
			request.getRequestDispatcher("getorderlistforpick.jsp").forward(request, response);
		}else if("deleteorder".equals(request_type)){
			String orderId = request.getParameter("orderId").toString();
			new OrderDao().deleteOrder(orderId);
			response.sendRedirect("OrderManager?type=getorderlist");
		}
	}

}
