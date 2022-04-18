package com.virtualacademy.training.dao;


import com.virtualacademy.training.commons.DBUtil;
import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.model.Shipper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class ShipperDAO {
	 private Connection con = null;
	public void saveShipper(Shipper shipper) throws NorthwindException {
		try{
		 con = DBUtil.openConnection();
		 String sql = "insert into shippers(shipperID,companyName,phone) values(?,?,?)";
		 PreparedStatement stmt = con.prepareStatement(sql);
		 stmt.setInt(1, shipper.getShipperId());
		 stmt.setString(2, shipper.getCompanyName());
		 stmt.setString(3, shipper.getPhone());
		  
		 stmt.executeUpdate();
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 }
	
	public Shipper findShipper(int shipperId) throws NorthwindException{
		 Shipper shipper = null;
		 try{
			 con = DBUtil.openConnection();
			 String sql = "select * from shippers where shipperID = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, shipperId);
			  
			 ResultSet rs =  stmt.executeQuery();
			 if(rs.next()){
				 shipper = new Shipper();
				 shipper.setShipperId(rs.getInt("shipperID"));
				 shipper.setCompanyName(rs.getString("companyName"));
				 shipper.setPhone(rs.getString("phone"));
				  
			 }else{
				 throw new NorthwindException("Record not found");
			 }
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 return shipper;
	 }
	 
	 public List<Shipper> getAllShipper() throws NorthwindException{
	 List<Shipper> shippers = new ArrayList<Shipper>();
	 Shipper shipper = null;
	 try{
		 con = DBUtil.openConnection();
		 String sql = "select * from shippers";
		 PreparedStatement stmt = con.prepareStatement(sql);
	 
		 ResultSet rs =  stmt.executeQuery();
		 while(rs.next()){
			 shipper = new Shipper();
			 shipper.setShipperId(rs.getInt("shipperID"));
			 shipper.setCompanyName(rs.getString("companyName"));
			 shipper.setPhone(rs.getString("phone"));

			 shippers.add(shipper);
		 } 
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 
	 return shippers;
	 }
	 
	 public void deleteShipper(int shipperId) throws NorthwindException{
		 try{
			 con = DBUtil.openConnection();
			 String sql = "delete from shippers where shipperId = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, shipperId);
			  
			 stmt.executeUpdate();
			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
	 }
	 
	 public void updateShipper(Shipper shipper) throws NorthwindException{
		 
		 try{
			 con = DBUtil.openConnection();
			 String sql = "update shippers set companyName= ?, phone=? where shipperId = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setString(1, shipper.getCompanyName());
			 stmt.setString(2, shipper.getPhone()); 
			 stmt.setInt(3, shipper.getShipperId());
			  stmt.executeUpdate();
			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 
	 }
}
