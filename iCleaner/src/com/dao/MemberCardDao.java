package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.MemberCard;
import com.model.Station;
import com.util.SqlHelper;
//HMM:0:36 2017/5/12
public class MemberCardDao {
	private String TAG="MemberCardDao";
	public void createStation(MemberCard member) {
		String sql = "insert into membercard_list (card_name,card_phone,card_money,card_integral) values (?,?,?,?);";
		String[] parameters = { member.getCard_name(),member.getCard_phone(),member.getCard_money(),member.getCard_integral() };
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}
	
	public void modifyStationInfo(MemberCard member) {
		String sql = "update membercard_list set card_phone=?, card_name=?,card_money=?,card_integral=? where card_phone=? ";
		String[] parameters = { member.getCard_phone(),member.getCard_name(),member.getCard_money(),member.getCard_integral(),member.getCard_phone()};
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}
	
	public void reChangeStationInfo(MemberCard member) {
		MemberCard oldMemberCard=findStationById(member.getCard_phone());
		int moneyAll=Integer.parseInt(oldMemberCard.getCard_money())+Integer.parseInt(member.getCard_money());
		member.setCard_money(moneyAll+"");
		member.setCard_integral(moneyAll*10+"");
		String sql = "update membercard_list set card_phone=?,card_money=?,card_integral=? where card_phone=? ";
		String[] parameters = { member.getCard_phone(),member.getCard_money(),member.getCard_integral(),member.getCard_phone()};
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}

	public ArrayList<MemberCard> getStationList() {
		ArrayList<MemberCard> station_list = new ArrayList<MemberCard>();
		String sql = "select * from membercard_list;";
		String[] parameters = {};
		@SuppressWarnings("unchecked")
		ArrayList<Object[]> query_res = (ArrayList<Object[]>) SqlHelper
				.executeQuery2("use icleaner", sql, parameters);
		if (query_res.size() > 0) {
			for (Object[] each : query_res) {
				MemberCard station = new MemberCard();
				if (each[0] != null) {
					station.setCard_name(each[1].toString());
				}
				if (each[1] != null) {
					station.setCard_phone(each[0].toString());
				}
				if (each[2] != null) {
					station.setCard_money(each[2].toString());
				}
				if (each[3] != null) {
					station.setCard_integral(each[3].toString());
				}
				station_list.add(station);
				System.out.println(station.getCard_name());
			}
			return station_list;
		} else {
			return station_list;
		}
	}
	/**
	 * ɾ��վ��
	 */
	public void deleteStation(String phone){
		String sql = "delete from membercard_list where card_phone=?";
		String[] parameters = {phone};
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}
	/**
	 * ���id��ѯ����վ��
	 */
	public MemberCard findStationById(String phone){
		//card_name,card_phone,card_money,card_integral
		System.out.println("-----"+phone);
		String sql = "select * from membercard_list where card_phone=?";
		String[] parameters = {phone};
		ResultSet rs = SqlHelper.executeQuery("use icleaner", sql, parameters);
		MemberCard station = new MemberCard();
		try {
			while(rs.next()){
				String card_name = rs.getString("card_name");
				String card_phone = rs.getString("card_phone");
				String card_money = rs.getString("card_money");
				String card_integral = rs.getString("card_integral");
				station.setCard_name(card_name);
				station.setCard_phone(card_phone);
				station.setCard_money(card_money);
				station.setCard_integral(card_integral);
				System.out.println("-----"+station.getCard_name());
				return station;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return station;
		
	}
}
