package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.CleanStatus;
import com.model.Order;
import com.model.PayStatus;
import com.model.Product;
import com.model.Station;
import com.util.SqlHelper;

public class OrderDao {
	/**
	 * 生成订单
	 */
	public void createOrder(Order order){
		String sql = "insert into order_list(orderid,cleaning_status_id,pay_status_id,total_price,order_product_id,order_station_id,order_station_foreign,customer_order_id) values(?,?,?,?,?,?,?,?)";
		String[] parameters = {order.getOrderId(),order.getCleanStatus().getCleanStatusId(),order.getPayStatus().getPayStatusId(),order.getTotalPrice(),order.getProduct().getProid(),order.getOrderAddr(),order.getStation().getStation_id(),order.getCustomer_order_id()};
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}
	/**
	 * 查看全部订单
	 */
	public ArrayList<Order> getOrderList(){
		ArrayList<Order> orderList = new ArrayList<Order>();
		String sql = "select orderid,cleanstatus,paystatus,total_price,proname,order_station_id,station_name from order_list o " +
				"left join pay_status p on p.paystatusid=o.pay_status_id " +
				"left join cleaning_status c on c.cleanstatusid=o.cleaning_status_id " +
				"left join station_list sl on sl.station_id=o.order_station_foreign " +
				"left join product pc on pc.proid=o.order_product_id;";
		String[] parameters = {};
		@SuppressWarnings("unchecked")
		ArrayList<Object[]> query_res = (ArrayList<Object[]>) SqlHelper.executeQuery2("use icleaner", sql, parameters);
		Order order = null;
		CleanStatus cs = null;
		PayStatus ps = null;
		Product p = null;
		Station s = null;
		if(query_res.size()>0){
			for (Object[] each : query_res) {
				order = new Order();
				if(each[0]!=null){
					order.setOrderId(each[0].toString());
				}
				if(each[1]!=null){
					cs = new CleanStatus();
					cs.setCleanStatus(each[1].toString());
					order.setCleanStatus(cs);
				}
				if(each[2]!=null){
					ps = new PayStatus();
					ps.setPayStatus(each[2].toString());
					order.setPayStatus(ps);
				}
				if(each[3]!=null){
					order.setTotalPrice(each[3].toString());
				}
				if(each[4]!=null){
					p = new Product();
					p.setProname(each[4].toString());
					order.setProduct(p);
				}
				if(each[5]!=null){
					order.setOrderAddr(each[5].toString());
				}
				if(each[6]!=null){
					s = new Station();
					s.setStation_name(each[6].toString());
					order.setStation(s);
					System.out.println(order.getStation().getStation_name()+"======");
				}
				orderList.add(order);
			}
//			for(Order each1:orderList){
//				System.out.println("@@@@@@@@"+each1.getStation().getStation_name());
//			}
				return orderList;
		}else{
				return orderList;
		}
	}
	
	/**
	 * 根据订单号获取一个订单信息
	 */
	public Order getOrderInfo(String orderid){
		String sql = "select orderid,order_station_id from order_list where orderid=?;";
		String[] parameters = {orderid};
		ResultSet rs = SqlHelper.executeQuery("use icleaner", sql, parameters);
		Order o = new Order();
		try {
			while(rs.next()){
				String oid = rs.getString("orderid");
				String orderaddr = rs.getString("order_station_id");
				o.setOrderId(oid);
				o.setOrderAddr(orderaddr);
				return o;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	/**
	 * 删除订单
	 */
	public void deleteOrder(String orderId){
		String sql = "delete from order_list where orderid=?";
		String[] parameters = {orderId};
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}
	
/**
 * 查看自己的全部订单
 */
public ArrayList<Order> getOrderList1(String id){
	ArrayList<Order> orderList = new ArrayList<Order>();
	String sql = "select orderid,cleanstatus,paystatus,total_price,proname,order_station_id,station_name from order_list o " +
			"left join pay_status p on p.paystatusid=o.pay_status_id " +
			"left join cleaning_status c on c.cleanstatusid=o.cleaning_status_id " +
			"left join station_list sl on sl.station_id=o.order_station_foreign " +
			"left join product pc on pc.proid=o.order_product_id where  o.customer_order_id = ?;";
	String[] parameters = {id};
	@SuppressWarnings("unchecked")
	ArrayList<Object[]> query_res = (ArrayList<Object[]>) SqlHelper.executeQuery2("use icleaner", sql, parameters);
	Order order = null;
	CleanStatus cs = null;
	PayStatus ps = null;
	Product p = null;
	Station s = null;
	if(query_res.size()>0){
		for (Object[] each : query_res) {
			order = new Order();
			if(each[0]!=null){
				order.setOrderId(each[0].toString());
			}
			if(each[1]!=null){
				cs = new CleanStatus();
				cs.setCleanStatus(each[1].toString());
				order.setCleanStatus(cs);
			}
			if(each[2]!=null){
				ps = new PayStatus();
				ps.setPayStatus(each[2].toString());
				order.setPayStatus(ps);
			}
			if(each[3]!=null){
				order.setTotalPrice(each[3].toString());
			}
			if(each[4]!=null){
				p = new Product();
				p.setProname(each[4].toString());
				order.setProduct(p);
			}
			if(each[5]!=null){
				order.setOrderAddr(each[5].toString());
			}
			if(each[6]!=null){
				s = new Station();
				s.setStation_name(each[6].toString());
				order.setStation(s);
				System.out.println(order.getStation().getStation_name()+"======");
			}
			orderList.add(order);
		}
//		for(Order each1:orderList){
//			System.out.println("@@@@@@@@"+each1.getStation().getStation_name());
//		}
			return orderList;
	}else{
			return orderList;
	}
}
	
	
	
}
