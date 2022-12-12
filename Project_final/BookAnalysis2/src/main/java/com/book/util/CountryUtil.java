package com.book.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class CountryUtil {
	
	HashMap<String, String> map = new HashMap<String, String>();
	
	//从数据文件中读取国家映射关系，并存在map中返回
	public HashMap<String,String> getCountryData() {
		Properties prop = new Properties();
		try {
			//读取country.properties数据文件
			InputStream in = new BufferedInputStream(new FileInputStream("./country_jianxie.properties"));
			
//			URL fileURL=this.getClass().getResource("country_jianxie.properties"); 
//	        InputStream in = new BufferedInputStream(new FileInputStream(fileURL.getFile()));
	        
			prop.load(new InputStreamReader(in, "utf-8"));
			Iterator<String> it = prop.stringPropertyNames().iterator();
			int i = 0;
			while(it.hasNext()) {
				String key = it.next();
				i++;
				//将数据封装到map中并返回出去
				map.put(prop.getProperty(key), key);
			}
			return map;
		}catch (Exception e) {   
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	//将数据保存入数据库中
	public void saveToDB() {
		//将国家中文名和英文名的对应关系存入数据库中
		Map<String,String> m2 = getCountryData();
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO country_name_map(country_en,country_ch) value (?,?)";
		
		try {
			for (String v : m2.keySet()) {
				 ps = conn.prepareStatement(sql);
				 ps.setString(1, m2.get(v));
				 ps.setString(2, v);
				 int b = ps.executeUpdate();
				 if(b<0) {
					 System.out.println("插入失败！！！");
				 }else {
					 System.out.println(v);
				 }
				 
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	 
	}
	
	
	//从数据中读取国家映射关系，存入map中并返回，供mapreduce运行时使用
	public Map<String,String> getCountryDataFromDB(){
		
		Map<String,String> map = new HashMap<String, String>();
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT COUNTRY_CH,COUNTRY_EN FROM country_name_map");
			rs = ps.executeQuery();
			while(rs.next()) {
				map.put(rs.getString(1), rs.getString(2));
			}
			return map;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		CountryUtil c = new CountryUtil();
		c.saveToDB();
		
	}
}
