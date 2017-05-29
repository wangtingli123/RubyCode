package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.CleanStatus;
import com.util.SqlHelper;

public class CleanStatusDao {
	/**
	 * 根据id查加工状态
	 */
	public CleanStatus getCleanStatusById(String cleanStatusId){

		String sql = "select cleanstatusid,cleanstatus from cleaning_status where cleanstatusid=?";
		String[] parameters = {cleanStatusId};
		ResultSet rs = SqlHelper.executeQuery("use icleaner", sql, parameters);
		CleanStatus cs = null;
		try {
			while(rs.next()){
				String cleanId = rs.getString("cleanstatusid");
				String cleanStatus = rs.getString("cleanstatus");
				cs = new CleanStatus();
				cs.setCleanStatusId(cleanId);
				cs.setCleanStatus(cleanStatus);
				return cs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cs;
	}
	/**
	 * 获取全部加工状态
	 */
	public ArrayList<CleanStatus> getCleanStatusList(){
		ArrayList<CleanStatus> cleanStatusList = new ArrayList<CleanStatus>();
		String sql = "select * from cleaning_status";
		String[] parameters = {};
		@SuppressWarnings("unchecked")
		ArrayList<Object[]> query_res = (ArrayList<Object[]>) SqlHelper.executeQuery2("use icleaner", sql, parameters);
		if (query_res.size() > 0) {
			for (Object[] each : query_res) {
				CleanStatus cs = new CleanStatus();
				if(each[0]!=null){
					cs.setCleanStatusId(each[0].toString());
				}
				if(each[1]!=null){
					cs.setCleanStatus(each[1].toString());
				}
				cleanStatusList.add(cs);
			}
				return cleanStatusList;
			}else{
				return cleanStatusList;
			}
	}
}
