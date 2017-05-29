package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Company;
import com.util.SqlHelper;

public class CompanyDao {
	/**
	 * ��ҵע��
	 */
	public void createCompany(Company comp){
		String sql = "insert into company(compid,compname,compwd) values(?,?,?);";
		String[] parameters={comp.getCompId(),comp.getCompName(),comp.getCompPwd()};
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}
	/**
	 * ��ҵ��½
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
					System.out.println("��¼�ɹ�");
					return comp;
				}else{
					System.out.println("��¼ʧ��");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
}
