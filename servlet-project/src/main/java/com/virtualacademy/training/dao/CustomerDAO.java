package com.virtualacademy.training.dao;


import com.virtualacademy.training.commons.DBUtil;
import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
public class CustomerDAO {
	 private Connection con = null;
	public void saveCustomer(Customer customer) throws NorthwindException {
		try{
		 con = DBUtil.openConnection();
		 String sql = "insert into customers(customerID,companyName,contactName,contactTitle,address,city,region,postalcode,country,phone,fax) values (?,?,?,?,?,?,?,?,?,?,?)";
		 PreparedStatement stmt = con.prepareStatement(sql);
		 stmt.setString(1, customer.getCustomerId());
		 stmt.setString(2, customer.getCompanyName());
		 stmt.setString(3, customer.getContactName());
		 stmt.setString(4, customer.getContactTitle());
		 stmt.setString(5, customer.getAddress());
		 stmt.setString(6, customer.getCity());
		 stmt.setString(7, customer.getRegion());
		 stmt.setString(8, customer.getPostalCode());
		 stmt.setString(9, customer.getCountry());
		 stmt.setString(10, customer.getPhone());
		 stmt.setString(11, customer.getFax());
		  
		  
		 stmt.executeUpdate();
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 }
	
	public Customer findCustomer(int customerId) throws NorthwindException{
		 Customer customer = null;
		 try{
			 con = DBUtil.openConnection();
			 String sql = "select * from customers where customerID = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, customerId);
			  
			 ResultSet rs =  stmt.executeQuery();
			 if(rs.next()){
				 customer = new Customer();
				 customer.setCustomerId(rs.getString("supplierID"));
				 customer.setCompanyName(rs.getString("companyName"));
				 customer.setContactName(rs.getString("contactName"));
				 customer.setContactTitle(rs.getString("contactTitle"));
				 customer.setAddress(rs.getString("address"));
				 customer.setCity(rs.getString("city"));
				 customer.setRegion(rs.getString("region"));
				 customer.setPostalCode(rs.getString("postalCode"));
				 customer.setCountry(rs.getString("country"));
				 customer.setPhone(rs.getString("phone"));
				 customer.setFax(rs.getString("fax"));
				   
				  
				  
			 }else{
				 throw new NorthwindException("Record not found");
			 }
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 return customer;
	 }
	 
	 public List<Customer> getAllCustomer() throws NorthwindException{
	 List<Customer> customers = new ArrayList<Customer>();
	 Customer customer = null;
	 try{
		 con = DBUtil.openConnection();
		 String sql = "select * from customers";
		 PreparedStatement stmt = con.prepareStatement(sql);
	 
		 ResultSet rs =  stmt.executeQuery();
		 while(rs.next()){
			 customer = new Customer();
			 customer.setCustomerId(rs.getString("customerID"));
			 customer.setCompanyName(rs.getString("companyName"));
			 customer.setContactName(rs.getString("contactName"));
			 customer.setContactTitle(rs.getString("contactTitle"));
			 customer.setAddress(rs.getString("address"));
			 customer.setCity(rs.getString("city"));
			 customer.setRegion(rs.getString("region"));
			 customer.setPostalCode(rs.getString("postalCode"));
			 customer.setCountry(rs.getString("country"));
			 customer.setPhone(rs.getString("phone"));
			 customer.setFax(rs.getString("fax"));
			  
			 customers.add(customer);
		 } 
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 
	 return customers;
	 }
	 
	 public void deleteCustomer(int customerId) throws NorthwindException{
		 try{
			 con = DBUtil.openConnection();
			 String sql = "delete from customers where customerId = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, customerId);
			  
			 stmt.executeUpdate();
			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
	 }
	 
	 public void updateCustomer(Customer customer) throws NorthwindException{
		 
		 try{
			 con = DBUtil.openConnection();
			 String sql = "update customers set companyName=?,contactName=?,contactTitle=?,address=?,city=?,region=?,postalcode=?,country=?,phone=?,fax=?,homepage=? where companyID=?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 
			 stmt.setString(1, customer.getCompanyName());
			 stmt.setString(2, customer.getContactName());
			 stmt.setString(3, customer.getContactTitle());
			 stmt.setString(4, customer.getAddress());
			 stmt.setString(5, customer.getCity());
			 stmt.setString(6, customer.getRegion());
			 stmt.setString(7, customer.getPostalCode());
			 stmt.setString(8, customer.getCountry());
			 stmt.setString(9, customer.getPhone());
			 stmt.setString(10, customer.getFax());
 			 stmt.setString(11, customer.getCustomerId());
			  stmt.executeUpdate();
			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 
	 }
}
