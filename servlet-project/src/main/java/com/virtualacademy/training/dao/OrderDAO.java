package com.virtualacademy.training.dao;


import com.virtualacademy.training.commons.DBUtil;
import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.model.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
	 private Connection con = null;
	public void saveOrder(CustomerOrder order) throws NorthwindException{
		try{
		 con = DBUtil.openConnection();
		 String sql = "insert into orders(orderID,customerID,employeeID,orderDate,requiredDate,ShippedDate,ShipVia,freight,shipName,ShipAddress,ShipCity,ShipRegion,ShipPostalCode,ShipCountry) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		 PreparedStatement stmt = con.prepareStatement(sql);
		  stmt.setInt(1, order.getOrderId());
		  stmt.setString(2, order.getCustomer().getCustomerId());
		  stmt.setInt(3, order.getEmployee().getEmployeeId());
		  stmt.setDate(4,new java.sql.Date(order.getOrderDate().getTime()));
		  stmt.setDate(5,new java.sql.Date(order.getRequiredDate().getTime()));
		  stmt.setDate(6,new java.sql.Date(order.getShippedDate().getTime()));
		  if(order.getShipper() != null){
			  stmt.setInt(7, order.getShipper().getShipperId());
		  }
		  stmt.setFloat(8, order.getFreight());
		  stmt.setString(9, order.getShipName());
		  stmt.setString(10, order.getShipAddress());
		  stmt.setString(11, order.getShipCity());
		  stmt.setString(12,order.getShipRegion());
		  stmt.setString(13, order.getShipPostalCode());
		  stmt.setString(14,order.getShipCountry());
		  stmt.executeUpdate();
		  if(order.getDetail() !=null && order.getDetail().size()>0){
			  sql ="insert into order_details (orderID,productID,UnitPrice,Quantity,Discount) values(?,?,?,?,?)";
			  for(OrderDetail detail : order.getDetail()){
				  stmt = con.prepareStatement(sql);
				  stmt.setInt(1, order.getOrderId());
				  stmt.setInt(2, detail.getProduct().getProductId());
				  stmt.setDouble(3, detail.getUnitPrice());
				  stmt.setInt(4, detail.getQuantity());
				  stmt.setDouble(5, detail.getDiscount());
				  stmt.executeUpdate();
			  }
		  }
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 }
	
	public CustomerOrder findOrder(int orderId) throws NorthwindException{
		 CustomerOrder order = null;
		 try{
			 con = DBUtil.openConnection();
			 String sql = "select * from orders  where orderID = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, orderId);
			  
			 ResultSet rs =  stmt.executeQuery();
			 if(rs.next()){
				 order = new CustomerOrder();
				 order.setOrderId(rs.getInt("orderId"));
				 Customer customer =  new Customer();
				 customer.setCustomerId(rs.getString("customerId"));
				 order.setCustomer(customer);
				 Employee emp = new Employee();
				 emp.setEmployeeId(rs.getInt("employeeId"));
				  order.setEmployee(emp);
				  order.setOrderDate(rs.getDate("orderDate"));
				  order.setRequiredDate(rs.getDate("requiredDate"));
				  order.setShippedDate(rs.getDate("shippedDate"));
				  Shipper shipper = new Shipper();
				  shipper.setShipperId(rs.getInt("shipperId"));
				  order.setShipper(shipper);
				  
				  order.setFreight(rs.getFloat("freight"));
				  order.setShipName(rs.getString("shipName"));
				  order.setShipAddress(rs.getString("shipAddress"));
				  order.setShipCity(rs.getString("shipCity"));
				  order.setShipRegion(rs.getString("shipRegion"));
				  order.setShipPostalCode(rs.getString("postalCode"));
				  order.setShipCountry(rs.getString("shipCountry")); 
				  sql ="select * from order_details where orderID=?";
				  stmt = con.prepareStatement(sql);
				  stmt.setInt(1, orderId);
				  rs = stmt.executeQuery();
				  List<OrderDetail> details = new ArrayList<>();
				  OrderDetail od = null;
				  while(rs.next()){
					  od = new OrderDetail();
					  Product p = new Product();
					  p.setProductId(rs.getInt("productId"));
					  od.setProduct(p);
					  od.setDiscount(rs.getDouble("discount"));
					  od.setQuantity(rs.getInt("quantity"));
					  od.setUnitPrice(rs.getDouble("unitprice"));
					  details.add(od);
				  }
				  order.setDetail(details);
			 }else{
				 throw new NorthwindException("Record not found");
			 }
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 return order;
	 }
	 
	 public List<CustomerOrder> getAllOrder() throws NorthwindException{
	 List<CustomerOrder> orders = new ArrayList<CustomerOrder>();
	 CustomerOrder order = null;
	 try{
		 con = DBUtil.openConnection();
		 String sql = "select * from orders";
		 PreparedStatement stmt = con.prepareStatement(sql);
	 
		 ResultSet rs =  stmt.executeQuery();
		 while(rs.next()){
			 order = new CustomerOrder();
			 order.setOrderId(rs.getInt("orderId"));
			 Customer customer =  new Customer();
			 customer.setCustomerId(rs.getString("customerId"));
			 order.setCustomer(customer);
			 Employee emp = new Employee();
			 emp.setEmployeeId(rs.getInt("employeeId"));
			  order.setEmployee(emp);
			  order.setOrderDate(rs.getDate("orderDate"));
			  order.setRequiredDate(rs.getDate("requiredDate"));
			  order.setShippedDate(rs.getDate("shippedDate"));
			  Shipper shipper = new Shipper();
			  shipper.setShipperId(rs.getInt("shipVia"));
			  order.setShipper(shipper);
			  
			  order.setFreight(rs.getFloat("freight"));
			  order.setShipName(rs.getString("shipName"));
			  order.setShipAddress(rs.getString("shipAddress"));
			  order.setShipCity(rs.getString("shipCity"));
			  order.setShipRegion(rs.getString("shipRegion"));
			  order.setShipPostalCode(rs.getString("shippostalCode"));
			  order.setShipCountry(rs.getString("shipCountry"));
			 orders.add(order);
		 } 
		}catch(Exception ex){
			throw new NorthwindException(ex.getMessage());
		}finally{
			DBUtil.closeConnection(con);
		}
	 
	 return orders;
	 }
	 
	 public void deleteOrder(int orderId) throws NorthwindException{
		 try{
			 con = DBUtil.openConnection();
			 String sql = "delete from order_details where orderId = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setInt(1, orderId);
			  
			 stmt.executeUpdate();
			 sql = "delete from orders where orderId = ?";
			 stmt = con.prepareStatement(sql);
			 stmt.setInt(1, orderId);
			  
			 stmt.executeUpdate(); 
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
	 }
	 
	 public void updateOrder(CustomerOrder order) throws NorthwindException{
		 
		 try{
			 con = DBUtil.openConnection();
			 String sql = "UPDATE orders SET customerID=?,employeeID=?,orderDate=?,requiredDate=?,ShippedDate=?,ShipVia=?,freight=?,shipName=?,ShipAddress=?,ShipCity=?,ShipRegion=?,ShipPostalCode=?,ShipCountr=? WHERE orderID=?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 
			  stmt.setString(1, order.getCustomer().getCustomerId());
			  stmt.setInt(2, order.getEmployee().getEmployeeId());
			  stmt.setDate(3,new java.sql.Date(order.getOrderDate().getTime()));
			  stmt.setDate(4,new java.sql.Date(order.getRequiredDate().getTime()));
			  stmt.setDate(5,new java.sql.Date(order.getShippedDate().getTime()));
			  if(order.getShipper() != null){
				  stmt.setInt(6, order.getShipper().getShipperId());
			  }
			  stmt.setFloat(7, order.getFreight());
			  stmt.setString(8, order.getShipName());
			  stmt.setString(9, order.getShipAddress());
			  stmt.setString(10, order.getShipCity());
			  stmt.setString(11,order.getShipRegion());
			  stmt.setString(12, order.getShipPostalCode());
			  stmt.setString(13,order.getShipCountry());
			  stmt.setInt(14, order.getOrderId());

			 stmt.executeUpdate();
			 if(order.getDetail() !=null && order.getDetail().size()>0){
				  sql ="update order_details set productID=?,UnitPrice=?,Quantity=?,Discount=? WHERE orderID=?";
				  for(OrderDetail detail : order.getDetail()){
					  stmt = con.prepareStatement(sql);
					  
					  stmt.setInt(1, detail.getProduct().getProductId());
					  stmt.setDouble(2, detail.getUnitPrice());
					  stmt.setInt(3, detail.getQuantity());
					  stmt.setDouble(4, detail.getDiscount());
					  stmt.setInt(5, order.getOrderId());
					  stmt.executeUpdate();
				  }
			  }			  
			}catch(Exception ex){
				throw new NorthwindException(ex.getMessage());
			}finally{
				DBUtil.closeConnection(con);
			}
		 
	 }
}
