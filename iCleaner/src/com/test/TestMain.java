package com.test;

import com.dao.AdminDao;
import com.dao.CategoryDao;
import com.dao.SendPeopleDao;
import com.dao.StationDao;
import com.model.Category;
import com.model.Price;
import com.model.SendPeople;
import com.model.Station;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Category cate = new Category();
		CategoryDao cd = new CategoryDao();
		cd.getCateById("001");
	}

}
