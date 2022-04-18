package com.virtualacademy.training.service;


import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.dao.ShipperDAO;
import com.virtualacademy.training.model.Shipper;

import java.util.ArrayList;
import java.util.List;
public class ShipperService {
	 private ShipperDAO shipperDAO = new ShipperDAO();
	public void saveShipper(Shipper shipper) throws NorthwindException{
		shipperDAO.saveShipper(shipper);
	 }
	
	public Shipper findShipper(int shipperId) throws NorthwindException{
		 Shipper shipper = null;
		 shipper = shipperDAO.findShipper(shipperId);
		 return shipper;
	 }
	 
	 public List<Shipper> getAllShipper() throws NorthwindException{
		 List<Shipper> shippers = new ArrayList<Shipper>();
	  	 shippers = shipperDAO.getAllShipper();
		 return shippers;
	 }
	 
	 public void deleteShipper(int shipperId) throws NorthwindException{
		 shipperDAO.deleteShipper(shipperId);
	 }
	 
	 public void updateShipper(Shipper shipper) throws NorthwindException{
		 shipperDAO.updateShipper(shipper);
		 
	 }
}
