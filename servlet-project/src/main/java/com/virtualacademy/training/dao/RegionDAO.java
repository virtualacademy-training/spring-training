package com.virtualacademy.training.dao;


import com.virtualacademy.training.commons.DBUtil;
import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.model.Region;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
public class RegionDAO {
	 private Connection con = null;
	public void saveRegion(Region region) throws NorthwindException {
		try{
		 con = DBUtil.openConnection();
		 String sql = "insert into region(regionID,regionDescription) values(?,?)";
		 PreparedStatement stmt = con.prepareStatement(sql);
		 stmt.setInt(1, region.getRegionId());
		 stmt.setString(2, region.getDescription());
		  
		  
		 stmt.executeUpdate();
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 }
	
	public Region findRegion(int regionId) throws NorthwindException{
		 Region region = null;
		 try{
			 con = DBUtil.openConnection();
			 String sql = "select * from region where regionID = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, regionId);
			  
			 ResultSet rs =  stmt.executeQuery();
			 if(rs.next()){
				 region = new Region();
				 region.setRegionId(rs.getInt("regionID"));
				 region.setDescription(rs.getString("regionDescription"));
				  
				  
			 }else{
				 throw new NorthwindException("Record not found");
			 }
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 return region;
	 }
	 
	 public List<Region> getAllRegion() throws NorthwindException{
	 List<Region> regions = new ArrayList<Region>();
	 Region region = null;
	 try{
		 con = DBUtil.openConnection();
		 String sql = "select * from region";
		 PreparedStatement stmt = con.prepareStatement(sql);
	 
		 ResultSet rs =  stmt.executeQuery();
		 while(rs.next()){
			 region = new Region();
			 region.setRegionId(rs.getInt("regionID"));
			 region.setDescription(rs.getString("regionDescription"));
			  
			 regions.add(region);
		 } 
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 
	 return regions;
	 }
	 
	 public void deleteRegion(int regionId) throws NorthwindException{
		 try{
			 con = DBUtil.openConnection();
			 String sql = "delete from region where regionId = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, regionId);
			  
			 stmt.executeUpdate();
			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
	 }
	 
	 public void updateRegion(Region region) throws NorthwindException{
		 
		 try{
			 con = DBUtil.openConnection();
			 String sql = "update region set regionDescription= ? where regionId = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setString(1, region.getDescription());
 			 stmt.setInt(2, region.getRegionId());
			  stmt.executeUpdate();
			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 
	 }
}
