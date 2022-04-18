package com.virtualacademy.training.dao;

import com.virtualacademy.training.commons.DBUtil;
import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.model.Region;
import com.virtualacademy.training.model.Territory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TerritoryDAO {
	 private Connection con = null;
	public void saveTerritory(Territory territory) throws NorthwindException {
		try{
		 con = DBUtil.openConnection();
		 String sql = "insert into territories(territoryId,territoryDescription,regionid) values(?,?,?)";
		 PreparedStatement stmt = con.prepareStatement(sql);
		 stmt.setInt(1, territory.getTerritoryId());
		 stmt.setString(2, territory.getDescription());
		 stmt.setInt(3, territory.getRegion().getRegionId());
		 stmt.executeUpdate();
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 }
	
	public Territory findTerritory(int territoryId) throws NorthwindException{
		 Territory territory = null;
		 try{
			 con = DBUtil.openConnection();
			 String sql = "select * from territories where territoryId = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, territoryId);
			  
			 ResultSet rs =  stmt.executeQuery();
			 if(rs.next()){
				 territory = new Territory();
				 territory.setTerritoryId(rs.getInt("territoryId"));
				 territory.setDescription(rs.getString("territoryDescription"));
				 Region region = new Region();
				 region.setRegionId(rs.getInt("regionId"));
				 territory.setRegion(region);
			 }else{
				 throw new NorthwindException("Record not found");
			 }
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 return territory;
	 }
	 
	 public List<Territory> getAllTerritory() throws NorthwindException{
	 List<Territory> territories = new ArrayList<Territory>();
	 Territory territory = null;
	 try{
		 con = DBUtil.openConnection();
		 String sql = "select * from territories ";
		 PreparedStatement stmt = con.prepareStatement(sql);
	 
		 ResultSet rs =  stmt.executeQuery();
		 while(rs.next()){
			 territory = new Territory();
			 territory.setTerritoryId(rs.getInt("territoryId"));
			 Region region = new Region();
			 region.setRegionId(rs.getInt("regionId"));
			 territory.setRegion(region);
			 territory.setDescription(rs.getString("territorydescription"));
			 territories.add(territory);
		 } 
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 
	 return territories;
	 }
	 
	 public void deleteTerritory(int territoryId) throws NorthwindException{
		 try{
			 con = DBUtil.openConnection();
			 String sql = "delete from territories where territoryId = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, territoryId);
			  
			 stmt.executeUpdate();
			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
	 }
	 
	 public void updateTerritory(Territory territory) throws NorthwindException{
		 
		 try{
			 con = DBUtil.openConnection();
			 String sql = "update territories set territoryDescription = ?, regionID = ? where territoryId = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setString(1, territory.getDescription());
			 stmt.setInt(2, territory.getRegion().getRegionId());
			 stmt.setInt(3, territory.getTerritoryId());
			  stmt.executeUpdate();
			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 
	 }
}
