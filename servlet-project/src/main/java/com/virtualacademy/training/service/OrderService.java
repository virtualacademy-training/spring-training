package com.virtualacademy.training.service;


import com.virtualacademy.training.dao.OrderDAO;
import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.model.CustomerOrder;

import java.util.ArrayList;
import java.util.List;
 
public class OrderService {
	 private OrderDAO orderDAO = new OrderDAO();
	public void saveOrder(CustomerOrder order) throws NorthwindException{
		   orderDAO.saveOrder(order);
	 }
	
	public CustomerOrder findOrder(int orderId) throws NorthwindException{
		 CustomerOrder order = null;
		 order = orderDAO.findOrder(orderId);   
		 return order;
	 }
	 
	 public List<CustomerOrder> getAllOrder() throws NorthwindException{
		 List<CustomerOrder> orders = new ArrayList<CustomerOrder>();
		 orders = orderDAO.getAllOrder();
		 return orders;
	 }
	 
	 public void deleteOrder(int orderId) throws NorthwindException{
		 orderDAO.deleteOrder(orderId);
	 }
	 
	 public void updateOrder(CustomerOrder order) throws NorthwindException{
		  orderDAO.updateOrder(order);		 
	 }
}
