package com.virtualacademy.training.commons;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {

	
	public static Connection openConnection(){
		Connection con = null;
		try{  
			InputStream fis = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
			Properties prop = new Properties();
			prop.load(fis);
			fis.close();
			String url = prop.getProperty("url");
			String driverClass = prop.getProperty("driverClass");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			Class.forName(driverClass);  
			  
			//step2 create  the connection object  
			con=DriverManager.getConnection(url,user,password); 
			}catch(Exception ex){
				ex.printStackTrace();
			}
		return con;
}
	public static void closeConnection(Connection con){
		try{
			if(!con.isClosed()){
				con.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}