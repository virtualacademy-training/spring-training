package com.virtualacademy.training.dao;


import com.virtualacademy.training.commons.DBUtil;
import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.model.Category;
import com.virtualacademy.training.model.Product;
import com.virtualacademy.training.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	 private Connection con = null;
	public void saveProduct(Product product) throws NorthwindException {
		try{
		 con = DBUtil.openConnection();
		 String sql = "insert into products(productID,productName,supplierID,CategoryID,QuantityPerUnit,UnitPrice,UnitsInStock,UnitsOnOrder,ReorderLevel,Discontinued) values (?,?,?,?,?,?,?,?,?,?)";
		 PreparedStatement stmt = con.prepareStatement(sql);
		  stmt.setInt(1, product.getProductId());
		  stmt.setString(2, product.getProductName());
		  if(product.getSupplier() != null){
			  stmt.setInt(3,product.getSupplier().getSupplierId());
		  }
		  if(product.getCategory() != null){
			  stmt.setInt(4, product.getCategory().getCategoryId());
		  }
		  stmt.setString(5, product.getQuantityPerUnit());
		  stmt.setDouble(6, product.getUnitPrice());
		  stmt.setInt(7, product.getUnitsInStock());
		  stmt.setInt(8, product.getUnitsOnOrder());
		  stmt.setInt(9, product.getReorderLevel());
		  stmt.setBoolean(10, product.isDiscontinued());
		  stmt.executeUpdate();
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 }
	
	public Product findProduct(int productId) throws NorthwindException{
		 Product product = null;
		 try{
			 con = DBUtil.openConnection();
			 String sql = "select * from products where productID = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, productId);
			  
			 ResultSet rs =  stmt.executeQuery();
			 if(rs.next()){
				 product = new Product();
				 product.setProductId(rs.getInt("productId"));
				 product.setProductName(rs.getString("productName"));
				 Supplier supplier = new Supplier();
				 supplier.setSupplierId(rs.getInt("SupplierId"));
				 Category category = new Category();
				 category.setCategoryId(rs.getInt("categoryId"));
				 product.setCategory(category);
				 
				 product.setQuantityPerUnit(rs.getString(rs.getString("quantityPerUnit")));
				  product.setUnitPrice(rs.getDouble("unitPrice"));
				   product.setUnitsInStock(rs.getInt("unitsinstock"));
				  product.setUnitsOnOrder(rs.getInt("unitsonorder"));
				  product.setReorderLevel(rs.getInt("reorderlevel"));
				   
				  product.setDiscontinued(rs.getBoolean("discontinued"));			 
				  
			 }else{
				 throw new NorthwindException("Record not found");
			 }
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 return product;
	 }
	 
	 public List<Product> getAllProduct() throws NorthwindException{
	 List<Product> products = new ArrayList<Product>();
	 Product product = null;
	 try{
		 con = DBUtil.openConnection();
		 String sql = "select * from products";
		 PreparedStatement stmt = con.prepareStatement(sql);
	 
		 ResultSet rs =  stmt.executeQuery();
		 while(rs.next()){
			 product = new Product();
			 product = new Product();
			 product.setProductId(rs.getInt("productId"));
			 product.setProductName(rs.getString("productName"));
			 Supplier supplier = new Supplier();
			 supplier.setSupplierId(rs.getInt("SupplierId"));
			 Category category = new Category();
			 category.setCategoryId(rs.getInt("categoryId"));
			 product.setCategory(category);
			 
			 product.setQuantityPerUnit(rs.getString("quantityPerUnit"));
			  product.setUnitPrice(rs.getDouble("unitPrice"));
			   product.setUnitsInStock(rs.getInt("unitsinstock"));
			  product.setUnitsOnOrder(rs.getInt("unitsonorder"));
			  product.setReorderLevel(rs.getInt("reorderlevel"));
			   
			  product.setDiscontinued(rs.getBoolean("discontinued"));
			 products.add(product);
		 } 
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 
	 return products;
	 }
	 
	 public void deleteProduct(int productId) throws NorthwindException{
		 try{
			 con = DBUtil.openConnection();
			 String sql = "delete from products where productId = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, productId);
			  
			 stmt.executeUpdate();
			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
	 }
	 
	 public void updateProduct(Product product) throws NorthwindException{
		 
		 try{
			 con = DBUtil.openConnection();
			 String sql = "update products set productName=?,supplierID=?,CategoryID=?,QuantityPerUnit=?,UnitPrice=?,UnitsInStock=?,UnitsOnOrder=?,ReorderLevel=?,Discontinued=? where productID=? ";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 
			  
			  stmt.setString(1, product.getProductName());
			  if(product.getSupplier() != null){
				  stmt.setInt(2,product.getSupplier().getSupplierId());
			  }
			  if(product.getCategory() != null){
				  stmt.setInt(3, product.getCategory().getCategoryId());
			  }
			  stmt.setString(4, product.getQuantityPerUnit());
			  stmt.setDouble(5, product.getUnitPrice());
			  stmt.setInt(6, product.getUnitsInStock());
			  stmt.setInt(7, product.getUnitsOnOrder());
			  stmt.setInt(8, product.getReorderLevel());
			  stmt.setBoolean(9, product.isDiscontinued());
			  stmt.setInt(10, product.getProductId());
			  stmt.executeUpdate();
			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 
	 }
}
