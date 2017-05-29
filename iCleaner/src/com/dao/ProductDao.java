package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Category;
import com.model.Product;
import com.util.SqlHelper;

public class ProductDao {
	/**
	 * ��Ʒ��ӣ�
	 */
	public void insertProduct(Product pro){
		String sql = "insert into product(proid,proname,product_price,pro_cate_id) values(?,?,?,?);";
		String[] parameters = {pro.getProid(),pro.getProname(),pro.getProduct_price(),pro.getCategory().getCid()};
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}
	/**
	 * ��Ʒ�鿴
	 */
	public ArrayList<Product> getProductList(){
		ArrayList<Product> productList = new ArrayList<Product>();
		String sql = "select proid,proname,product_price,cname from product p left join category c on pro_cate_id=c.cid;";
		String[] parameters = {};
		ArrayList<Object[]> query_res = (ArrayList<Object[]>) SqlHelper.executeQuery2("use icleaner", sql, parameters);
		Category cate = null;
		if(query_res.size()>0){
			for(Object[] each : query_res){
				Product pro = new Product();
				cate = new Category();
				if(each[0] != null){
					pro.setProid(each[0].toString());
				}
				if(each[1] != null){
					pro.setProname(each[1].toString());
				}
				if(each[2] != null){
					pro.setProduct_price(each[2].toString());
				}
				if(each[3] != null){
					cate.setCname(each[3].toString());
					pro.setCategory(cate);
				}
				productList.add(pro);
			}
				return productList;
		}else{
			return productList;
		}
	}
	/**
	 * �������id�鿴��Ʒ��Ϣ
	 */
	public Product getProductById(String proCatecoryId){
		String sql = "select proid,proname,product_price,cname from product p "
				+ "left join category c on c.cid=p.pro_cate_id where proid=?;";
		String[] parameters = {proCatecoryId};
		ResultSet rs = SqlHelper.executeQuery("use icleaner", sql, parameters);
		Product pro = new Product();
		try {
			while(rs.next()){
				String proid = rs.getString("proid");
				String proname = rs.getString("proname");
				String product_price = rs.getString("product_price");
				String procategoty = rs.getString("cname");
				pro.setProid(proid);
				pro.setProname(proname);
				pro.setProduct_price(product_price);
				Category cate = new Category();
				cate.setCname(procategoty);
				pro.setCategory(cate);
				System.out.println("dao�������⣿"+pro.getProname()+pro.getProduct_price());
				return pro;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pro;
	}
	/**
	 * ��Ʒɾ��
	 */
	public void deleteProduct(String proid){
		String sql = "delete from product where proid=?";
		String[] parameters = {proid};
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}
	/**
	 * ��Ʒ�༭
	 */
	public void modifyProduct(Product pro){
		String sql = "update product set proname=?,product_price=? where proid=?";
		String[] parameters = {pro.getProname(),pro.getProduct_price(),pro.getProid()};
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}	
	
	
	
	
}
