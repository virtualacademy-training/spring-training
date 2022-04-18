package com.virtualacademy.training.service;


import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.dao.CustomerDAO;
import com.virtualacademy.training.model.Customer;

import java.util.ArrayList;
import java.util.List;
 
public class CustomerService {
	 private CustomerDAO customerDAO = new CustomerDAO();
	public void saveCustomer(Customer customer) throws NorthwindException{
		 customerDAO.saveCustomer(customer);
	 }
	
	public Customer findCustomer(int customerId) throws NorthwindException{
		 Customer customer = null;
		 customer = customerDAO.findCustomer(customerId);
		 return customer;
	 }
	 
	 public List<Customer> getAllCustomer() throws NorthwindException{
		 List<Customer> customers = new ArrayList<Customer>();
		 customers = customerDAO.getAllCustomer();
		 return customers;
	 }
	 
	 public void deleteCustomer(int customerId) throws NorthwindException{
		 customerDAO.deleteCustomer(customerId);
	 }
	 
	 public void updateCustomer(Customer customer) throws NorthwindException{
		  customerDAO.updateCustomer(customer);		 
	 }
}
