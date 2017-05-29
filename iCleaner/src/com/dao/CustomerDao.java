package com.dao;

import java.util.ArrayList;

import com.model.Customer;
import com.util.SqlHelper;

public class CustomerDao {
	/**
	 * 用户注册
	 */
	public void customerRegister(Customer cus){
		String sql = "insert into customer values(?,?,?)";
		String[] parameters = {cus.getCustomerid(),cus.getCustomername(),cus.getCustomerpwd()};
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}
	/**
	 * 取送人员登陆
	 */
	public Customer doLogin(Customer customer){
		String sql = "select * from customer where customerid=? and customerpwd=?";
		String[] parameters = {customer.getCustomerid(),customer.getCustomerpwd()};
		ArrayList<Object[]> query_res=(ArrayList<Object[]>)SqlHelper.executeQuery2("use icleaner", sql, parameters);
		if(query_res!=null){
			if(query_res.size()==1){
				return customer;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
}
