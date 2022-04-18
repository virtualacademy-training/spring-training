package com.virtualacademy.training.dao;

import com.virtualacademy.training.commons.DBUtil;
import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
	 private Connection con = null;
	public void saveCategory(Category category) throws NorthwindException {
		try{
		 con = DBUtil.openConnection();
		 String sql = "insert into categories(categoryId,categoryName,description) values(?,?,?)";
		 PreparedStatement stmt = con.prepareStatement(sql);
		 stmt.setInt(1, category.getCategoryId());
		 stmt.setString(2, category.getCategoryName());
		 stmt.setString(3, category.getDescription());
		 stmt.executeUpdate();
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 }
	
	public Category findCategory(int categoryId) throws NorthwindException{
		 Category category = null;
		 try{
			 con = DBUtil.openConnection();
			 String sql = "select * from categories where categoryId = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, categoryId);
			  
			 ResultSet rs =  stmt.executeQuery();
			 if(rs.next()){
				 category = new Category();
				 category.setCategoryId(rs.getInt("categoryId"));
				 category.setCategoryName(rs.getString("categoryName"));
				 category.setDescription(rs.getString("description"));
			 }else{
				 throw new NorthwindException("Record not found");
			 }
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 return category;
	 }
	 
	 public List<Category> getAllCategories() throws NorthwindException{
	 List<Category> categories = new ArrayList<Category>();
	 Category category = null;
	 try{
		 con = DBUtil.openConnection();
		 String sql = "select * from categories ";
		 PreparedStatement stmt = con.prepareStatement(sql);
	 
		 ResultSet rs =  stmt.executeQuery();
		 while(rs.next()){
			 category = new Category();
			 category.setCategoryId(rs.getInt("categoryId"));
			 category.setCategoryName(rs.getString("categoryName"));
			 category.setDescription(rs.getString("description"));
			 categories.add(category);
		 } 
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 
	 return categories;
	 }
	 
	 public void deleteCategory(int categoryId) throws NorthwindException{
		 try{
			 con = DBUtil.openConnection();
			 String sql = "delete from categories where categoryId = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, categoryId);
			  
			 stmt.executeUpdate();
			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
	 }
	 
	 public void updateCategory(Category category) throws NorthwindException{
		 
		 try{
			 con = DBUtil.openConnection();
			 String sql = "update categories set categoryName = ?, description = ? where categoryId = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setString(1, category.getCategoryName());
			 stmt.setString(2, category.getDescription());
			 stmt.setInt(3, category.getCategoryId());
			  stmt.executeUpdate();
			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 
	 }
}
