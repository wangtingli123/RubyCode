package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Price;
import com.util.SqlHelper;

public class PriceDao {
	/**
	 * 查看价目表
	 */
	public ArrayList<Price> getPriceList(){
		ArrayList<Price> priceList = new ArrayList<Price>();
		String sql = "select * from price";
		String[] parameters = {};
		ArrayList<Object[]> query_res = (ArrayList<Object[]>) SqlHelper
				.executeQuery2("use icleaner", sql, parameters);
		if(query_res.size()>0){
			for (Object[] each : query_res) {
				Price p = new Price();
				if(each[0]!=null){
					p.setPid(each[0].toString());
				}
				if(each[1]!=null){
					p.setPricelow(each[1].toString());
				}
				if(each[2]!=null){
					p.setPricehigh(each[2].toString());
				}
				priceList.add(p);
			}
			return priceList;
		}else{
			return priceList;
		}
	}

	/**
	 * 根据id查
	 */

	public Price getPriceById(String pid){
		String sql = "select pid,pricelow,pricehigh from price where pid=?";
		String[] parameters = {pid}; 
		ResultSet rs = SqlHelper.executeQuery("use icleaner", sql, parameters);
		Price p = new Price();
		try {
			while(rs.next()){
				String priceid = rs.getString("pid");
				String pricelow = rs.getString("pricelow");
				String pricehigh = rs.getString("pricehigh");
				p.setPid(priceid);
				p.setPricelow(pricelow);
				p.setPricehigh(pricehigh);
				return p;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	/**
	 * 编辑价目表
	 */
	public void modifyPrice(Price price){
		String sql = "update price set pid=?,pricelow=?,pricehigh=? where pid=?";
		String[] parameters = {price.getPid(),price.getPricelow(),price.getPricehigh(),price.getPid()};
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
		
	}
}
