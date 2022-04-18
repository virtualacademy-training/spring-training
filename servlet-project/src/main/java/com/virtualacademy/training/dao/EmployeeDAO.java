package com.virtualacademy.training.dao;


import com.virtualacademy.training.commons.DBUtil;
import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.model.Employee;
import com.virtualacademy.training.model.Territory;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
public class EmployeeDAO {
	 private Connection con = null;
	public void saveEmployee(Employee employee) throws NorthwindException {
		try{
		 con = DBUtil.openConnection();
		 String sql = "insert into (employeeID,lastName,firstname,title,TitleOfCourtesy,birthdate,hiredate,address,city,region,postalcode,country,homephone,extension,photo,notes,ReportsTo,photopath) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		 PreparedStatement stmt = con.prepareStatement(sql);
		 stmt.setInt(1, employee.getEmployeeId());
		 stmt.setString(2, employee.getLastName());
		 stmt.setString(3, employee.getFirstName());
		 stmt.setString(4, employee.getTitle());
		 stmt.setString(5, employee.getTitleOfCourtesy());
		  
		 stmt.setDate(6, new java.sql.Date(employee.getBirthDate().getTime()));
		 stmt.setDate(7, new java.sql.Date(employee.getHireDate().getTime()));
		 stmt.setString(8, employee.getAddress());
		 stmt.setString(9,employee.getCity());
		 stmt.setString(10,employee.getRegion());
		 stmt.setString(11, employee.getPostalCode());
		 stmt.setString(12, employee.getCountry());
		 stmt.setString(13, employee.getHomePhone());
		 stmt.setString(14, employee.getExtension());
		 if(employee.getPhoto() != null){
		 InputStream is = new ByteArrayInputStream(employee.getPhoto());
			 stmt.setBinaryStream(15, is);
		 }
		 stmt.setString(16, employee.getNotes());
		 if(employee.getReportesTo() != null){
			 stmt.setInt(17, employee.getReportesTo().getEmployeeId());
		 }
		 stmt.setString(18, employee.getPhotoPath());
		 stmt.executeUpdate();
		 List<Territory> territories = employee.getTerritories();
		 if(territories != null && territories.size()>0){
			 sql = "insert into employeeterritories(employeeID,territoryID) values(?,?)";
			 for(Territory territory : territories){
				 stmt = con.prepareStatement(sql);
				 stmt.setInt(1, territory.getTerritoryId());
				 stmt.setInt(2, employee.getEmployeeId());
	 			 stmt.executeUpdate();
			 }
		 }
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 }
	
	public Employee findEmployee(int employeeId) throws NorthwindException{
		 Employee employee = null;
		 try{
			 con = DBUtil.openConnection();
			 String sql = "select * from employees where employeeID = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, employeeId);
			  
			 ResultSet rs =  stmt.executeQuery();
			 if(rs.next()){
				 employee = new Employee();
				 employee.setEmployeeId(rs.getInt("employeeID"));
				 employee.setLastName(rs.getString("lastName"));
				 employee.setFirstName(rs.getString("firstName"));
				 employee.setTitle(rs.getString("title"));
				 employee.setTitleOfCourtesy(rs.getString("titleOfCourtesy"));
				 employee.setBirthDate(rs.getDate("birthDate"));
				 employee.setHireDate(rs.getDate("hireDate"));
 				 employee.setAddress(rs.getString("address"));
				 employee.setCity(rs.getString("city"));
				 employee.setRegion("region");
				 employee.setPostalCode(rs.getString("postalCode"));
				 employee.setCountry(rs.getString("country"));
				 employee.setHomePhone(rs.getString("homephone"));
				 employee.setExtension(rs.getString("extension"));
				  
				  byte[] pic = rs.getBytes("photo");
				  if(pic != null){
					  FileOutputStream fout = new FileOutputStream(rs.getString("photopath"));
						fout.write(pic);
					}
 				 employee.setPhotoPath(rs.getString("photopath"));
				 employee.setNotes(rs.getString("notes"));
				  int manager = rs.getInt(rs.getInt("reportsTo"));
				  if(manager >0){
					  Employee emp = new Employee();
					  emp.setEmployeeId(manager);
					  employee.setReportesTo(emp);
				  }
				 
				  
			 }else{
				 throw new NorthwindException("Record not found");
			 }
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 return employee;
	 }
	 
	 public List<Employee> getAllEmployee() throws NorthwindException{
	 List<Employee> employees = new ArrayList<Employee>();
	 Employee employee = null;
	 try{
		 con = DBUtil.openConnection();
		 String sql = "select * from employees";
		 PreparedStatement stmt = con.prepareStatement(sql);
	 
		 ResultSet rs =  stmt.executeQuery();
		 while(rs.next()){
			 employee = new Employee();
			 employee.setEmployeeId(rs.getInt("employeeID"));
			 employee.setLastName(rs.getString("lastName"));
			 employee.setFirstName(rs.getString("firstName"));
			 employee.setTitle(rs.getString("title"));
			 employee.setTitleOfCourtesy(rs.getString("titleOfCourtesy"));
			 employee.setBirthDate(rs.getDate("birthDate"));
			 employee.setHireDate(rs.getDate("hireDate"));
				 employee.setAddress(rs.getString("address"));
			 employee.setCity(rs.getString("city"));
			 employee.setRegion("region");
			 employee.setPostalCode(rs.getString("postalCode"));
			 employee.setCountry(rs.getString("country"));
			 employee.setHomePhone(rs.getString("homephone"));
			 employee.setExtension(rs.getString("extension"));
			  
			  byte[] pic = rs.getBytes("photo");
			  if(pic != null){
				  FileOutputStream fout = new FileOutputStream(rs.getString("photopath"));
					fout.write(pic);
				}
				 employee.setPhotoPath(rs.getString("photopath"));
			 employee.setNotes(rs.getString("notes"));
			  int manager = rs.getInt("reportsTo");
			  if(manager >0){
				  Employee emp = new Employee();
				  emp.setEmployeeId(manager);
				  employee.setReportesTo(emp);
			  }
			 employees.add(employee);
		 } 
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 
	 return employees;
	 }
	 
	 public void deleteEmployee(int employeeId) throws NorthwindException{
		 try{
			 con = DBUtil.openConnection();
			 String sql = "delete from employees where employeeId = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, employeeId);
			  
			 stmt.executeUpdate();
			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
	 }
	 
	 public void updateEmployee(Employee employee) throws NorthwindException{
		 
		 try{
			 con = DBUtil.openConnection();
			 String sql = "update employees set lastName=?,firstname=?,title=?,TitleOfCourtesy=?,birthdate=?,hiredate=?,address=?,city=?,region=?,postalcode=?,country=?,homephone=?,extension=?,photo=?,notes=?,ReportsTo=?,photopath=? where employeeID=?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 
			 stmt.setString(1, employee.getLastName());
			 stmt.setString(2, employee.getFirstName());
			 stmt.setString(3, employee.getTitle());
			 stmt.setString(4, employee.getTitleOfCourtesy());
			  
			 stmt.setDate(5, new java.sql.Date(employee.getBirthDate().getTime()));
			 stmt.setDate(6, new java.sql.Date(employee.getHireDate().getTime()));
			 stmt.setString(7, employee.getAddress());
			 stmt.setString(8,employee.getCity());
			 stmt.setString(9,employee.getRegion());
			 stmt.setString(10, employee.getPostalCode());
			 stmt.setString(11, employee.getCountry());
			 stmt.setString(12, employee.getHomePhone());
			 stmt.setString(13, employee.getExtension());
			 if(employee.getPhoto() != null){
			 InputStream is = new ByteArrayInputStream(employee.getPhoto());
				 stmt.setBinaryStream(14, is);
			 }
			 stmt.setString(15, employee.getNotes());
			 if(employee.getReportesTo() != null){
				 stmt.setInt(16, employee.getReportesTo().getEmployeeId());
			 }
			 stmt.setString(17, employee.getPhotoPath());
			 stmt.setInt(18, employee.getEmployeeId());

			  stmt.executeUpdate();
			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 
	 }
}
