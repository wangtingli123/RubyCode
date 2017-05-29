package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.SendPeopleDao;
import com.model.SendPeople;
import com.model.Station;

/**
 * Servlet implementation class SendPeopleManager
 */
public class SendPeopleManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendPeopleManager() {
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
		if("addsendpeople".equals(request_type)){
			String sendPeopleId = request.getParameter("sendpeopleid");
			String sendPeopleName = request.getParameter("sendpeoplename");
			String sendPeopleTel = request.getParameter("sendpeopletel");
			String sendPeopleHomeAddr  = request.getParameter("sendpeoplehome");
			String sendStationId = request.getParameter("stationid");
			String sendPeoplePwd = request.getParameter("sendpwd");
			SendPeople sp = new SendPeople();
			sp.setSendId(sendPeopleId);
			sp.setSendName(sendPeopleName);
			sp.setSendTel(sendPeopleTel);
			sp.setSendPeoplePwd(sendPeoplePwd);//初始密码设为一样的
			Station s = new Station();
			s.setStation_id(sendStationId);
			sp.setSendHomeAddr(sendPeopleHomeAddr);
			sp.setStation(s);
			new SendPeopleDao().sendRegister(sp);
			request.getRequestDispatcher("SendPeopleManager?type=getSendPeopleList").forward(request, response);
		} else if("getSendPeopleList".equals(request_type)){
			ArrayList<SendPeople> sendList = new SendPeopleDao().getSendPeopleList();
			request.setAttribute("sendList", sendList);
			System.out.println("全部");
			request.getRequestDispatcher("getSendList.jsp").forward(request, response);
		} else if("getsendpeople".equals(request_type)){
			String sendpeopleid = request.getParameter("sendpeopleid").toString();
			SendPeople sp = new SendPeopleDao().getSendPeopleById(sendpeopleid);
			System.out.println("lalalla"+sp.getStation().getStation_id());
			request.setAttribute("sp", sp);
			request.getRequestDispatcher("getsendpeople.jsp").forward(request, response);
		} else if("modifysendpeople".equals(request_type)){
			String sendPeopleId = request.getParameter("sendid").toString();
			String sendName = request.getParameter("sendname").toString();
			String sendTel = request.getParameter("sendtel").toString();
			String sendHomeAddr = request.getParameter("sendhomeaddr").toString();
			String sendboundstation = request.getParameter("sendboundstation").toString();
			SendPeople sp = new SendPeople();
			sp.setSendId(sendPeopleId);
			sp.setSendName(sendName);
			sp.setSendTel(sendTel);
			sp.setSendHomeAddr(sendHomeAddr);
			Station s = new Station();
			s.setStation_id(sendboundstation);
			sp.setStation(s);
			new SendPeopleDao().modifySendPeopleInfo(sp);
			response.sendRedirect("SendPeopleManager?type=getSendPeopleList");
		}else if("deletesendpeople".equals(request_type)){
			String sendId = request.getParameter("sendpeopleid").toString();
			new SendPeopleDao().deleteSendPeople(sendId);
			response.sendRedirect("SendPeopleManager?type=getSendPeopleList");
		}else if("sendpeoplelogin".equals(request_type)){
			String sendid = request.getParameter("sendid").toString();
			String sendpwd = request.getParameter("sendpwd").toString();
			SendPeople sp = new SendPeople();
			sp.setSendId(sendid);
			sp.setSendPeoplePwd(sendpwd);
			SendPeople sendpeople = new SendPeopleDao().doLogin(sp);
			if(sendpeople!=null){
				request.getRequestDispatcher("main.html").forward(request, response);
			}else{
				request.getRequestDispatcher("login1.html").forward(request, response);
			}
		}else if("sendpeopleregister".equals(request_type)){
			
		}
		
	}

}
