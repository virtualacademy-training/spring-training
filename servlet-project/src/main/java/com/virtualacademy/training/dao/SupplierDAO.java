package com.virtualacademy.training.dao;


import com.virtualacademy.training.commons.DBUtil;
import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
public class SupplierDAO {
	 private Connection con = null;
	public void saveSupplier(Supplier supplier) throws NorthwindException {
		try{
		 con = DBUtil.openConnection();
		 String sql = "insert into suppliers(supplierID,companyName,contactName,contactTitle,address,city,region,postalcode,country,phone,fax,homepage) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		 PreparedStatement stmt = con.prepareStatement(sql);
		 stmt.setInt(1, supplier.getSupplierId());
		 stmt.setString(2, supplier.getCompanyName());
		 stmt.setString(3, supplier.getContactName());
		 stmt.setString(4, supplier.getContactTitle());
		 stmt.setString(5, supplier.getAddress());
		 stmt.setString(6, supplier.getCity());
		 stmt.setString(7, supplier.getRegion());
		 stmt.setString(8, supplier.getPostalCode());
		 stmt.setString(9, supplier.getCountry());
		 stmt.setString(10, supplier.getPhone());
		 stmt.setString(11, supplier.getFax());
		 stmt.setString(12, supplier.getHomePage());
		  
		 stmt.executeUpdate();
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 }
	
	public Supplier findSupplier(int supplierId) throws NorthwindException{
		 Supplier supplier = null;
		 try{
			 con = DBUtil.openConnection();
			 String sql = "select * from suppliers where supplierID = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, supplierId);
			  
			 ResultSet rs =  stmt.executeQuery();
			 if(rs.next()){
				 supplier = new Supplier();
				 supplier.setSupplierId(rs.getInt("supplierID"));
				 supplier.setCompanyName(rs.getString("companyName"));
				 supplier.setContactName(rs.getString("contactName"));
				 supplier.setContactTitle(rs.getString("contactTitle"));
				 supplier.setAddress(rs.getString("address"));
				 supplier.setCity(rs.getString("city"));
				 supplier.setRegion(rs.getString("region"));
				 supplier.setPostalCode(rs.getString("postalCode"));
				 supplier.setCountry(rs.getString("country"));
				 supplier.setPhone(rs.getString("phone"));
				 supplier.setFax(rs.getString("fax"));
				 supplier.setHomePage(rs.getString("homepage")); 
				  
				  
			 }else{
				 throw new NorthwindException("Record not found");
			 }
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 return supplier;
	 }
	 
	 public List<Supplier> getAllSupplier() throws NorthwindException{
	 List<Supplier> suppliers = new ArrayList<Supplier>();
	 Supplier supplier = null;
	 try{
		 con = DBUtil.openConnection();
		 String sql = "select * from suppliers";
		 PreparedStatement stmt = con.prepareStatement(sql);
	 
		 ResultSet rs =  stmt.executeQuery();
		 while(rs.next()){
			 supplier = new Supplier();
			 supplier.setSupplierId(rs.getInt("supplierID"));
			 supplier.setCompanyName(rs.getString("companyName"));
			 supplier.setContactName(rs.getString("contactName"));
			 supplier.setContactTitle(rs.getString("contactTitle"));
			 supplier.setAddress(rs.getString("address"));
			 supplier.setCity(rs.getString("city"));
			 supplier.setRegion(rs.getString("region"));
			 supplier.setPostalCode(rs.getString("postalCode"));
			 supplier.setCountry(rs.getString("country"));
			 supplier.setPhone(rs.getString("phone"));
			 supplier.setFax(rs.getString("fax"));
			 supplier.setHomePage(rs.getString("homepage"));
			 suppliers.add(supplier);
		 } 
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 
	 return suppliers;
	 }
	 
	 public void deleteSupplier(int supplierId) throws NorthwindException{
		 try{
			 con = DBUtil.openConnection();
			 String sql = "delete from suppliers where supplierId = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, supplierId);
			  
			 stmt.executeUpdate();
			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
	 }
	 
	 public void updateSupplier(Supplier supplier) throws NorthwindException{
		 
		 try{
			 con = DBUtil.openConnection();
			 String sql = "update suppliers set companyName=?,contactName=?,contactTitle=?,address=?,city=?,region=?,postalcode=?,country=?,phone=?,fax=?,homepage=? where supplierID=?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 
			 stmt.setString(1, supplier.getCompanyName());
			 stmt.setString(2, supplier.getContactName());
			 stmt.setString(3, supplier.getContactTitle());
			 stmt.setString(4, supplier.getAddress());
			 stmt.setString(5, supplier.getCity());
			 stmt.setString(6, supplier.getRegion());
			 stmt.setString(7, supplier.getPostalCode());
			 stmt.setString(8, supplier.getCountry());
			 stmt.setString(9, supplier.getPhone());
			 stmt.setString(10, supplier.getFax());
			 stmt.setString(11, supplier.getHomePage());
			 stmt.setInt(12, supplier.getSupplierId());
			  stmt.executeUpdate();
			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 
	 }
}
