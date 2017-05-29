package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Category;
import com.model.Price;
import com.util.SqlHelper;

public class CategoryDao {

	/**
	 * 品类新增
	 */
	public void insertCategory(Category cate){
		String sql = "insert into category(cid,cname,price_cate_id) values(?,?,?);";
		String[] parameters = {cate.getCid(),cate.getCname(),cate.getPrice().getPid()};
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}
	/**
	 * 查看全部品类
	 */
	public ArrayList<Category> getCategoryList(){
		ArrayList<Category> cateList = new ArrayList<Category>();
		String sql = "select cid,cname,price_cate_id,pricelow,pricehigh from category a " +
				"left join price p on price_cate_id=p.pid;";
		String[] parameters = {};
		@SuppressWarnings("unchecked")
		ArrayList<Object[]> query_res = (ArrayList<Object[]>) SqlHelper.executeQuery2("use icleaner", sql, parameters);
		Price price = null;
		if (query_res.size() > 0) {
			for (Object[] each : query_res) {
				Category cate = new Category();
				price = new Price();
				if(each[0] !=null){
					cate.setCid(each[0].toString());
				}
				if(each[1] !=null){
					cate.setCname(each[1].toString());
				}
				if(each[2] !=null){
					price.setPid(each[2].toString());
					cate.setPrice(price);
				}
				if(each[3] != null){
					price.setPricelow(each[3].toString());
					cate.setPrice(price);
				}
				if(each[4] != null){
					price.setPricehigh(each[4].toString());
					cate.setPrice(price);
				}
				System.out.println(cate.getPrice().getPricelow());
				cateList.add(cate);
			}
				return cateList;
			}else{
				return cateList;
			}
	}
		/**
		 * 根据品类id查看信息
		 */
	public Category getCateById(String cateid){
		String sql = "select cid,cname,price_cate_id,pricelow,pricehigh from category c left join price p on price_cate_id=p.pid and cid=?;";
		String[] parameters = {cateid};
		ResultSet rs = SqlHelper.executeQuery("use icleaner", sql, parameters);
		Category cate = new Category();
		try {
			while(rs.next()){
				String cid = rs.getString("cid");
				String cname = rs.getString("cname");
				String price_cate_id = rs.getString("price_cate_id");
				String pricelow = rs.getString("pricelow");
				String pricehigh = rs.getString("pricehigh");
				cate.setCid(cid);
				cate.setCname(cname);
				Price price = new Price();
				price.setPid(price_cate_id);
				price.setPricelow(pricelow);
				price.setPricehigh(pricehigh);
				cate.setPrice(price);
				System.out.println(cate.getPrice().getPricelow());
				return cate;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cate;
	}
	/**
	 * 编辑品类
	 */
	public void modifyCategory(Category cate){
		String sql = "update category set cname=?,price_cate_id=? where cid=?";
		String[] parameters = {cate.getCname(),cate.getPrice().getPid(),cate.getCid()};
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}
	/**
	 * 品类删除
	 */
	public void deleteCategory(String cateid){
		String sql = "delete from category where cid=?";
		String[] parameters = {cateid};
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}
	}
