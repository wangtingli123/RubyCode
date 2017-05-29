package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.StationDao;
import com.model.Station;

/**
 * Servlet implementation class StationManager
 */

public class StationManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StationManager() {
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
		if ("addstation".equals(request_type)){
			String station_name=request.getParameter("stationname").toString();
			String station_lati=request.getParameter("stationlati").toString();
			String station_long=request.getParameter("stationlong").toString();
			String station_id=UUID.randomUUID().toString();
			Station station=new Station();
			station.setLatitude(station_lati);
			station.setLongitude(station_long);
			station.setStation_id(station_id);
			station.setStation_name(station_name);
			new StationDao().createStation(station);
			ArrayList<Station> station_list=new StationDao().getStationList();
			request.setAttribute("stationlist", station_list);
			request.getRequestDispatcher("finishpage.jsp").forward(request, response);
		}
		else if("modifystation".equals(request_type))
		{
			String station_id = request.getParameter("stationId").toString();
			String station_name=request.getParameter("stationname").toString();
			String station_lati=request.getParameter("stationlati").toString();
			String station_long=request.getParameter("stationlong").toString();
			Station station=new Station();
			station.setStation_id(station_id);
			station.setLatitude(station_lati);
			station.setLongitude(station_long);
			station.setStation_name(station_name);
			new StationDao().modifyStationInfo(station);
			response.sendRedirect("StationManager?type=getstationlist");
//			PrintWriter out = response.getWriter();
//			out.write("success");
		} else if("getstationlist".equals(request_type))
		{
			ArrayList<Station> station_list=new StationDao().getStationList();
			request.setAttribute("stationlist", station_list);
			request.getRequestDispatcher("finishpage.jsp").forward(request, response);
		} else if("getstationforsendregis".equals(request_type))
		{
			ArrayList<Station> station_list=new StationDao().getStationList();
			request.setAttribute("stationlist", station_list);
			System.out.println("heheda");
			request.getRequestDispatcher("sendpeople_register.jsp").forward(request, response);
		}else if("deletestation".equals(request_type)){
			String stationId = request.getParameter("stationId").toString();
			new StationDao().deleteStation(stationId);
			response.sendRedirect("StationManager?type=getstationlist");
		} else if("findStationById".equals(request_type)){
			String stationId = request.getParameter("stationId").toString();
			Station station = new StationDao().findStationById(stationId);
			request.setAttribute("station", station);
			request.getRequestDispatcher("station_modify.jsp").forward(request, response);
		}
		
	}

}
