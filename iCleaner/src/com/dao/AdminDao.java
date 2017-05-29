package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Admin;
import com.model.Company;
import com.model.SendPeople;
import com.util.SqlHelper;

public class AdminDao {

	public Admin doLogin(Admin admin){
		String sql = "select adminid,adminpwd from admin_list";
		String[] parameters = {};
		ResultSet rs =  SqlHelper.executeQuery("use icleaner", sql, parameters);
		try {
			while(rs.next()){
				String adminId = rs.getString("adminid").trim();
				String adminPwd = rs.getString("adminpwd").trim();
				if(admin.getAdminId().equals(adminId)&&admin.getAdminPwd().equals(adminPwd)){
					System.out.println("µÇÂ¼³É¹¦");
					return admin;
				}else{
					System.out.println("µÇÂ¼Ê§°Ü");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ÆóÒµ×¢²á
	 */
//	public void createSendPeople(SendPeople sp){
//		String sql = "insert into company(compid,compname,compwd) values(?,?,?);";
//		String[] parameters={comp.getCompId(),comp.getCompName(),comp.getCompPwd()};
//		SqlHelper.executeUpdate("use icleaner", sql, parameters);
//	}
}

