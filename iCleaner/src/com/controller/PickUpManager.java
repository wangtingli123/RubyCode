package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.OrderDao;
import com.dao.PickUpDao;
import com.dao.SendOrderStatusDao;
import com.dao.StationDao;
import com.model.Order;
import com.model.PickUp;
import com.model.SendOrderStatus;
import com.model.Station;

/**
 * Servlet implementation class PickUpManager
 */
public class PickUpManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PickUpManager() {
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
		if("getorderinfo".equals(request_type)){
			String orderid = request.getParameter("orderid").toString();
			Order order = new OrderDao().getOrderInfo(orderid);
			request.setAttribute("order", order);
			//同时获取站点列表
			ArrayList<Station> stationList = new StationDao().getStationList();
			request.setAttribute("stationList", stationList);
			//同时获取派单状态
			ArrayList<SendOrderStatus> soslist = new SendOrderStatusDao().getSendStatusList();
			request.setAttribute("soslist", soslist);
			request.getRequestDispatcher("dealpickup.jsp").forward(request, response);
		}else if("createpickup".equals(request_type)){
			String pickid = UUID.randomUUID().toString();//取件单号
			String orderid = request.getParameter("orderid").toString();//订单编号
			System.out.println("从页面获取的订单id???????"+orderid);
			String orderstationid = request.getParameter("orderstationid").toString();//这里不需要把订单地址存到取件单中
			String sendstationid = request.getParameter("stationId").toString();
			String sendstatusid = request.getParameter("sendstatusid").toString();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String realtime = df.format(new Date());
			PickUp pc = new PickUp();
			pc.setPickid(pickid);
			Order o = new Order();
			o.setOrderId(orderid);//pc中只存orderid
			pc.setOrder(o);
			Station s = new Station();
			s.setStation_id(sendstationid);
			pc.setStation(s);
			SendOrderStatus sd = new SendOrderStatus();
			sd.setSendOrderId(sendstatusid);
			pc.setPickStatus(sd);
			pc.setRealTime(realtime);
			new PickUpDao().createPickUp(pc);
			PickUp pu = new PickUpDao().getPickUpById(pickid);
			request.setAttribute("pu", pu);
			request.getRequestDispatcher("getpickup.jsp").forward(request, response);
		}else if("getpicklist".equals(request_type)){
			ArrayList<PickUp> pickuplist = new PickUpDao().getPickUpList();
			request.setAttribute("pickuplist", pickuplist);
			request.getRequestDispatcher("getPickUpList.jsp").forward(request, response);
		}else if("getpickup".equals(request_type)){
			String pickId = request.getParameter("pickid").toString();
			PickUp pu = new PickUpDao().getPickUpById(pickId);
			request.setAttribute("pu", pu);
			request.getRequestDispatcher("getpickup.jsp").forward(request, response);
		}else if("deletepick".equals(request_type)){
			String pickId = request.getParameter("pickid").toString();
			new PickUpDao().deletePickup(pickId);
			response.sendRedirect("PickUpManager?type=getpicklist");
		}
	}

}
