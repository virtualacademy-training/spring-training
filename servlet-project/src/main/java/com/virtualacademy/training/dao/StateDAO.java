package com.virtualacademy.training.dao;

import com.virtualacademy.training.commons.DBUtil;
import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.model.Region;
import com.virtualacademy.training.model.State;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StateDAO {
	 private Connection con = null;
	public void saveState(State state) throws NorthwindException {
		try{
		 con = DBUtil.openConnection();
		 String sql = "insert into usstates(stateId,stateName,stateAbbr,regionid) values(?,?,?,?)";
		 PreparedStatement stmt = con.prepareStatement(sql);
		 stmt.setInt(1, state.getStateId());
		 stmt.setString(2, state.getStateName());
		 stmt.setString(3, state.getShortName());
		 stmt.setInt(4, state.getRegion().getRegionId());
		 stmt.executeUpdate();
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 }
	
	public State findState(int stateId) throws NorthwindException{
		 State state = null;
		 try{
			 con = DBUtil.openConnection();
			 String sql = "select * from usstates where stateId = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, stateId);
			  
			 ResultSet rs =  stmt.executeQuery();
			 if(rs.next()){
				 state = new State();
				 state.setStateId(rs.getInt("stateId"));
				 state.setStateName(rs.getString("stateName"));
				 state.setShortName(rs.getString("StateAbbr"));
				 Region region = new Region();
				 region.setRegionId(rs.getInt("regionId"));
				 state.setRegion(region);
			 }else{
				 throw new NorthwindException("Record not found");
			 }
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 return state;
	 }
	 
	 public List<State> getAllState() throws NorthwindException{
	 List<State> states = new ArrayList<State>();
	 State state = null;
	 try{
		 con = DBUtil.openConnection();
		 String sql = "select * from usstates ";
		 PreparedStatement stmt = con.prepareStatement(sql);
	 
		 ResultSet rs =  stmt.executeQuery();
		 while(rs.next()){
			 state = new State();
			 state.setStateId(rs.getInt("stateId"));
			 state.setStateName(rs.getString("stateName"));
			 state.setShortName(rs.getString("StateAbbr"));
			 Region region = new Region();
			 region.setRegionId(rs.getInt("regionId"));
			 state.setRegion(region);

			 states.add(state);
		 } 
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 
	 return states;
	 }
	 
	 public void deleteState(int stateId) throws NorthwindException{
		 try{
			 con = DBUtil.openConnection();
			 String sql = "delete from usstates where stateId = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, stateId);
			  
			 stmt.executeUpdate();
			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
	 }
	 
	 public void updateState(State state) throws NorthwindException{
		 
		 try{
			 con = DBUtil.openConnection();
			 String sql = "update usstates set statename = ?, stateAbbr=?,regionID = ? where stateId = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setString(1, state.getStateName());
			 stmt.setString(2, state.getShortName());
			 stmt.setInt(3, state.getRegion().getRegionId());
			 stmt.setInt(4, state.getStateId());
			  stmt.executeUpdate();
			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 
	 }
}
