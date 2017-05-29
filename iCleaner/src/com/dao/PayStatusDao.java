package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.CleanStatus;
import com.model.PayStatus;
import com.util.SqlHelper;

public class PayStatusDao {
	/**
	 * 根据id查支付状态
	 */
	public PayStatus getPayStatusById(String payStatusId){
		String sql = "select paystatusid,paystatus from pay_status where paystatusid=?";
		String[] parameters = {payStatusId};
		ResultSet rs = SqlHelper.executeQuery("use icleaner", sql, parameters);
		PayStatus ps = null;
		try {
			while(rs.next()){
				String payId = rs.getString("paystatusid");
				String payStatus = rs.getString("paystatus");
				ps = new PayStatus();
				ps.setPayStatusId(payId);
				ps.setPayStatus(payStatus);
				return ps;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
	}
}
