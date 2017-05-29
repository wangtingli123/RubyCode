package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Company;
import com.util.SqlHelper;

public class CompanyDao {
	/**
	 * ÆóÒµ×¢²á
	 */
	public void createCompany(Company comp){
		String sql = "insert into company(compid,compname,compwd) values(?,?,?);";
		String[] parameters={comp.getCompId(),comp.getCompName(),comp.getCompPwd()};
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}
	/**
	 * ÆóÒµµÇÂ½
	 */
	public Company doLogin(Company comp){
		String sql = "select compname,compwd from company;";
		String[] parameters = {};
		ResultSet rs =  SqlHelper.executeQuery("use icleaner", sql, parameters);
		try {
			while(rs.next()){
				String compname = rs.getString("compname").trim();
				String compwd = rs.getString("compwd").trim();
				if(comp.getCompName().equals(compname)&&comp.getCompPwd().equals(compwd)){
					System.out.println("µÇÂ¼³É¹¦");
					return comp;
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
}
