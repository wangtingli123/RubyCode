package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.SendPeople;
import com.model.Station;
import com.util.SqlHelper;

public class SendPeopleDao {
	/**
	 * 取送人员登陆
	 */
	public SendPeople doLogin(SendPeople sp){
		String sql = "select * from sendpeople_list where sendid =? and sendpeoplepwd =? ; ";
		String[] parameters = { sp.getSendId(),sp.getSendPeoplePwd() };
		ArrayList<Object[]> query_res=(ArrayList<Object[]>)SqlHelper.executeQuery2("use icleaner", sql, parameters);
		if(query_res!=null)
		{
			if(query_res.size()==1)
			{
				return sp;
			}else
			{
				return null;
			}
		}else
		{
			return null;
		}
	}
	
	/**
	 * 取送人员注册
	 */
	public void sendRegister(SendPeople sendPeople){
		String sql = "insert into sendpeople_list values(?,?,?,?,?,?)";
		
		String[] parameters = { sendPeople.getSendId(),sendPeople.getSendName(),
				sendPeople.getSendTel(),sendPeople.getSendHomeAddr(),
				sendPeople.getStation().getStation_id(),sendPeople.getSendPeoplePwd()};
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}
	/**
	 * 查全部
	 */
	public ArrayList<SendPeople> getSendPeopleList(){
		ArrayList<SendPeople> sendPeopleList = new ArrayList<SendPeople>();
		String sql = "select sendid,sendname,sendpeoplepwd,sendtel,sendhomeaddr,station_name from sendpeople_list as send " +
				"left join station_list station on send.sendbound_stationid=station.station_id;";
		String[] parameters = {};
		@SuppressWarnings("unchecked")
		ArrayList<Object[]> query_res = (ArrayList<Object[]>) SqlHelper
				.executeQuery2("use icleaner", sql, parameters);
		if (query_res.size() > 0) {
			for (Object[] each : query_res) {
				SendPeople send = new SendPeople();
				if(each[0] != null){
					send.setSendId(each[0].toString());
				}
				if(each[1] != null){
					send.setSendName(each[1].toString());
				}
				if(each[2] !=null){
					send.setSendTel(each[2].toString());
				}
				if(each[3]!=null){
					send.setSendPeoplePwd(each[3].toString());
				}
				if(each[4] !=null){
					send.setSendHomeAddr(each[4].toString());
				}
				if(each[5] !=null){
					Station s = new Station();
					s.setStation_name(each[5].toString());
					send.setStation(s);
				}
				sendPeopleList.add(send);
				System.out.println(send.getSendName());
			}
			return sendPeopleList;
		}else{
			return sendPeopleList;
		}
	}
	/**
	 * 编辑取送人员信息
	 */
	public void modifySendPeopleInfo(SendPeople sp){
		String sql = "update sendpeople_list set sendname=?,sendtel=?,sendhomeaddr=?,sendbound_stationid=? "
				+ "where sendid=?;";
		String[] parameters = {sp.getSendName(),sp.getSendTel(),sp.getSendHomeAddr(),sp.getStation().getStation_id(),sp.getSendId()};
		System.out.println("更改=============取送");
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}
	/**
	 * 查看单个取送人信息
	 */
	public SendPeople getSendPeopleById(String sendPeopleId){
		String sql = "select sendid,sendname,sendtel,sendhomeaddr,station_name,station_id from sendpeople_list as send " +
				"left join station_list station on send.sendbound_stationid=station.station_id where sendid=?;";
		String[] parameters = {sendPeopleId};
		ResultSet rs = SqlHelper.executeQuery("use icleaner", sql, parameters);
		SendPeople sp = new SendPeople();
		try {
			while(rs.next()){
				String sendId = rs.getString("sendid");
				String sendName = rs.getString("sendname");
				String sendTel = rs.getString("sendtel");
				String sendHomeAddr = rs.getString("sendhomeaddr");
				String stationName = rs.getString("station_name");
				String stationId = rs.getString("station_id");
				sp.setSendId(sendId);
				sp.setSendName(sendName);
				sp.setSendTel(sendTel);
				sp.setSendHomeAddr(sendHomeAddr);
				Station s = new Station();
				s.setStation_name(stationName);
				s.setStation_id(stationId);
				sp.setStation(s);
				return sp;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sp;
	}
	
	/**
	 * 删除取送人员
	 */
	public void deleteSendPeople(String sendId){
		String sql = "delete from sendpeople_list where sendid=?";
		String[] parameters = {sendId};
		SqlHelper.executeUpdate("use icleaner", sql, parameters); 
	}
	
	
	
	
}
