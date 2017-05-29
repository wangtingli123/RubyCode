package com.dao;

import java.util.ArrayList;

import com.model.SendOrderStatus;
import com.util.SqlHelper;

public class SendOrderStatusDao {
	/**
	 * 查看全部状态
	 */
	public ArrayList<SendOrderStatus> getSendStatusList(){
		ArrayList<SendOrderStatus> sendStatusList = new ArrayList<SendOrderStatus>();
		String sql = "select * from sendorder";
		String[] parameters = {};
		@SuppressWarnings("unchecked")
		ArrayList<Object[]> query_res = (ArrayList<Object[]>) SqlHelper.executeQuery2("use icleaner", sql, parameters);
		if (query_res.size() > 0) {
			for (Object[] each : query_res) {
				SendOrderStatus sos = new SendOrderStatus();
				if(each[0]!=null){
					sos.setSendOrderId(each[0].toString());
				}
				if(each[1]!=null){
					sos.setSendOrderStatus(each[1].toString());
				}
				sendStatusList.add(sos);
			}
				return sendStatusList;
			}else{
				return sendStatusList;
			}
	}
}
