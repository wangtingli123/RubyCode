package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Order;
import com.model.PickUp;
import com.model.SendOrderStatus;
import com.model.Station;
import com.util.SqlHelper;

public class PickUpDao {
	/**
	 * 生成取件单
	 */
	public void createPickUp(PickUp p){
	String sql = "insert into pickup(pickid,pickstation,pickstatus,realtime,pickup_order_id) values(?,?,?,?,?);";
	System.out.println(p.getOrder().getOrderId()+"??????????????????");
	String[] parameters = {p.getPickid(),p.getStation().getStation_id(),p.getPickStatus().getSendOrderId(),p.getRealTime(),p.getOrder().getOrderId()};
	SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}
	/**
	 * 查看全部取件单
	 */
	public ArrayList<PickUp> getPickUpList(){
		ArrayList<PickUp> pickUpList = new ArrayList<PickUp>();
		String sql = "select pickid,station_name,sendorderstatus,realtime,pickup_order_id,order_station_id from pickup p "
				+ "left join sendorder s on s.sendorderid=p.pickstatus "
				+ "left join station_list sl on sl.station_id=p.pickstation "
				+ "left join order_list o on o.orderid=pickup_order_id;";
		String[] parameters = {};
		@SuppressWarnings("unchecked")
		ArrayList<Object[]> query_res = (ArrayList<Object[]>) SqlHelper.executeQuery2("use icleaner", sql, parameters);
		Station s = null;
		SendOrderStatus sos = null;
		Order o = new Order();
		if (query_res.size() > 0) {
			for (Object[] each : query_res) {
				PickUp pu = new PickUp();
				if(each[0]!=null){
					pu.setPickid(each[0].toString());
				}
				if(each[1]!=null){
					s = new Station();
					s.setStation_name(each[1].toString());
					pu.setStation(s);
				}
				if(each[2]!=null){
					sos = new SendOrderStatus();
					sos.setSendOrderStatus(each[2].toString());
					pu.setPickStatus(sos);
				}
				if(each[3]!=null){
					pu.setRealTime(each[3].toString());
				}
				if(each[4]!=null){
					o.setOrderId(each[4].toString());
					pu.setOrder(o);
				}
				if(each[5]!=null){
					o.setOrderAddr(each[5].toString());
					pu.setOrder(o);
				}
				pickUpList.add(pu);
			}
			return pickUpList;
		}else{
			return pickUpList;
		}
	
}/**
	 * 查看单个取件单详情	
	 */
		public PickUp getPickUpById(String pickid){
			String sql = "select pickid,order_station_id,station_name,realtime,sendorderstatus from pickup p "
					+ " left join sendorder s on s.sendorderid=p.pickstatus "
					+ " left join station_list sl on sl.station_id=p.pickstation "
					+ " left join order_list o on o.orderid=pickup_order_id "
					+ " where pickid=?;";
			String[] parameters = {pickid};
			ResultSet rs = SqlHelper.executeQuery("use icleaner", sql, parameters);
			PickUp pu = new PickUp();
			try {
				while(rs.next()){
					String pickId = rs.getString("pickid");
					String orderAddr = rs.getString("order_station_id");
					String stationName = rs.getString("station_name");
					String realTime = rs.getString("realtime");
					String sendStatus = rs.getString("sendorderstatus");
					pu.setPickid(pickId);
					Order o = new Order();
					o.setOrderAddr(orderAddr);
					pu.setOrder(o);
					Station s = new Station();
					s.setStation_name(stationName);
					pu.setStation(s);
					pu.setRealTime(realTime);
					SendOrderStatus sos = new SendOrderStatus();
					sos.setSendOrderStatus(sendStatus);
					pu.setPickStatus(sos);
					return pu;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return pu;
		}
		/**
		 * 删除取件单
		 */
		public void deletePickup(String pickid){
			String sql = "delete from pickup where pickid=?";
			String[] parameters = {pickid};
			SqlHelper.executeUpdate("use icleaner", sql, parameters);
		}
		
		
		
		
		
}
