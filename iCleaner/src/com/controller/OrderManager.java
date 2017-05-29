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
			//������֧���Ķ����µ�
			String orderId = UUID.randomUUID().toString();
			String productTypeId = request.getParameter("productTypeId").toString();//��ȡ�µ���Ʒ�����
			String productPrice = request.getParameter("proprice").toString();//�۸�
			String orderAddress = request.getParameter("orderaddr").toString();//������ַ
			String stationId = request.getParameter("stationId").toString();
			String cleanStatus = request.getParameter("cleanstatus").toString();//Ĭ��ѡ��δ�ӹ�
			String payStatus = "2";//2Ϊ��֧����֧��״̬��ҳ��û����ʾ��ҳ��Ҫͨ����ť�����֣�
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
			//���¾��ǵ��ò���ķ���
			new OrderDao().createOrder(order);
			response.sendRedirect("OrderManager?type=getorderlist");
		}else if("addunpayedstatus".equals(request_type)){
			//����δ֧���Ķ���
			
		}else if("getproductprice".equals(request_type)){
			//��ʵ���ⲿ��
			String productTypeId = request.getParameter("protypeid").toString();//��ȡ�µ���Ʒ�����
			Product pro = new ProductDao().getProductById(productTypeId);
			String productPrice = pro.getProduct_price();
			PrintWriter pw = response.getWriter();
			pw.write(productPrice);
		}else if("getproductlist".equals(request_type)){
			ArrayList<Product> productList = new ProductDao().getProductList();
			//ͬʱҪ�������ȡ�ӹ�״̬
			ArrayList<CleanStatus> cslist = new CleanStatusDao().getCleanStatusList();
			//��ȡվ���б�
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
			ArrayList<Order> orderlist = new OrderDao().getOrderList();//����ʱд���⣬֮������д����ѯ�������ƶ���
			request.setAttribute("orderlist", orderlist);
			request.getRequestDispatcher("getorderlistforpick.jsp").forward(request, response);
		}else if("deleteorder".equals(request_type)){
			String orderId = request.getParameter("orderId").toString();
			new OrderDao().deleteOrder(orderId);
			response.sendRedirect("OrderManager?type=getorderlist");
		}
	}

}
