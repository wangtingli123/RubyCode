package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MemberCardDao;
import com.dao.PriceDao;
import com.dao.StationDao;
import com.model.MemberCard;
import com.model.Price;
import com.model.Station;

/**
 * Servlet implementation class StationManager
 */

public class MemberCardManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberCardManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String request_type = request.getParameter("type").toString();
		System.out.println("doPost"+request_type);
		if ("addstation".equals(request_type)){
			String station_name=request.getParameter("stationname").toString();
			System.out.println("hhhhh");
			String phone=request.getParameter("stationphone").toString();
			String money=request.getParameter("stationlMoney").toString();
			String integral=request.getParameter("stationlIntegral").toString();
			//String station_id=UUID.randomUUID().toString();
			MemberCard station=new MemberCard();
			station.setCard_name(station_name);
			station.setCard_phone(phone);
			station.setCard_money(money);
			station.setCard_integral(integral);
			
			new MemberCardDao().createStation(station);
			ArrayList<MemberCard> membercard_list=new MemberCardDao().getStationList();
			request.setAttribute("membercard_list", membercard_list);
			request.getRequestDispatcher("membercardlist.jsp").forward(request, response);
		}
		else if("modifystation".equals(request_type))
		{
			String station_name=request.getParameter("stationname").toString();
			String phone=request.getParameter("stationphone").toString();
			String money=request.getParameter("stationlMoney").toString();
			String integral=request.getParameter("stationlIntegral").toString();
			//String station_id=UUID.randomUUID().toString();
			MemberCard station=new MemberCard();
			station.setCard_name(station_name);
			station.setCard_phone(phone);
			station.setCard_money(money);
			station.setCard_integral(integral);
		
			new MemberCardDao().modifyStationInfo(station);
			response.sendRedirect("MemberCardManager?type=getstationlist");
//			PrintWriter out = response.getWriter();
//			out.write("success");
		} else if("getstationlist".equals(request_type))
		{
			ArrayList<MemberCard> membercard_list=new MemberCardDao().getStationList();
			request.setAttribute("membercard_list", membercard_list);
			request.getRequestDispatcher("membercardlist.jsp").forward(request, response);
		}  else if("stationlist".equals(request_type))
		{
			ArrayList<MemberCard> membercard_list=new MemberCardDao().getStationList();
			request.setAttribute("membercard_list", membercard_list);
			request.getRequestDispatcher("membercard_modify.jsp").forward(request, response);
		} else if("rechargestation".equals(request_type)){
			String phone=request.getParameter("stationphone").toString();
			String money=request.getParameter("stationlMoney").toString();
			//String station_id=UUID.randomUUID().toString();
			MemberCard station=new MemberCard();
			station.setCard_phone(phone);
			station.setCard_money(money);
			new MemberCardDao().reChangeStationInfo(station);
			ArrayList<MemberCard> membercard_list=new MemberCardDao().getStationList();
			request.setAttribute("membercard_list", membercard_list);
			request.getRequestDispatcher("membercardlist.jsp").forward(request, response);
			
		}
		else if("getstationforsendregis".equals(request_type))
		{
			ArrayList<MemberCard> membercard_list=new MemberCardDao().getStationList();
			request.setAttribute("membercard_list", membercard_list);
			System.out.println("heheda");
			request.getRequestDispatcher("membercardlist.jsp").forward(request, response);
		}else if("deletestation".equals(request_type)){
			String stationId = request.getParameter("stationphone").toString();
			new MemberCardDao().deleteStation(stationId);
			response.sendRedirect("MemberCardManager?type=getstationlist");
		} else if("findStationById".equals(request_type)){
			String stationId = request.getParameter("stationphone").toString();
			MemberCard station = new MemberCardDao().findStationById(stationId);
			request.setAttribute("station", station);
			request.getRequestDispatcher("membercard.jsp").forward(request, response);
		}
		
	}

}
