package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Station;
import com.util.SqlHelper;

public class StationDao {

	public void createStation(Station station) {
		String sql = "insert into station_list (station_id,station_name,latitude,longitude) values (?,?,?,?);";
		String[] parameters = { station.getStation_id(),
				station.getStation_name(), station.getLatitude(),
				station.getLongitude() };
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}

	public void modifyStationInfo(Station station) {
		String sql = "update station_list set station_name=?,latitude=?,longitude=? where station_id=? ";
		String[] parameters = { station.getStation_name(),
				station.getLatitude(), station.getLongitude(),
				station.getStation_id() };
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}

	public ArrayList<Station> getStationList() {
		ArrayList<Station> station_list = new ArrayList<Station>();
		String sql = "select * from station_list;";
		String[] parameters = {};
		@SuppressWarnings("unchecked")
		ArrayList<Object[]> query_res = (ArrayList<Object[]>) SqlHelper
				.executeQuery2("use icleaner", sql, parameters);
		if (query_res.size() > 0) {
			for (Object[] each : query_res) {
				Station station = new Station();
				if (each[0] != null) {
					station.setStation_id(each[0].toString());
				}
				if (each[1] != null) {
					station.setStation_name(each[1].toString());
				}
				if (each[2] != null) {
					station.setLatitude(each[2].toString());
				}
				if (each[3] != null) {
					station.setLongitude(each[3].toString());
				}
				station_list.add(station);
				System.out.println(station.getStation_name());
			}
			return station_list;
		} else {
			return station_list;
		}
	}
	/**
	 * 删除站点
	 */
	public void deleteStation(String stationId){
		String sql = "delete from station_list where station_id=?";
		String[] parameters = {stationId};
		SqlHelper.executeUpdate("use icleaner", sql, parameters);
	}
	/**
	 * 根据id查询单个站点
	 */
	public Station findStationById(String stationId){
		String sql = "select station_id,station_name,latitude,longitude from station_list where station_id=?";
		String[] parameters = {stationId};
		ResultSet rs = SqlHelper.executeQuery("use icleaner", sql, parameters);
		Station station = new Station();
		try {
			while(rs.next()){
				String sid = rs.getString("station_id");
				String stationName = rs.getString("station_name");
				String latitude = rs.getString("latitude");
				String longtitude = rs.getString("longitude");
				station.setStation_id(sid);
				station.setStation_name(stationName);
				station.setLatitude(latitude);
				station.setLongitude(longtitude);
				System.out.println(station.getStation_name());
				return station;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return station;
		
	}
}
