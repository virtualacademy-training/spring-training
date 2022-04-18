package com.virtualacademy.training.service;

import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.dao.TerritoryDAO;
import com.virtualacademy.training.model.Territory;

import java.util.ArrayList;
import java.util.List;

public class TerritoryService {
	 private TerritoryDAO territoryDAO = new TerritoryDAO();
	public void saveTerritory(Territory territory) throws NorthwindException{
		territoryDAO.saveTerritory(territory);
	 }
	
	public Territory findTerritory(int territoryId) throws NorthwindException{
		 Territory territory = null;
		 territory = territoryDAO.findTerritory(territoryId); 
		 return territory;
	 }
	 
	 public List<Territory> getAllTerritory() throws NorthwindException{
		 List<Territory> territories = new ArrayList<Territory>();
		 territories = territoryDAO.getAllTerritory();
		 return territories;
	 }
	 
	 public void deleteTerritory(int territoryId) throws NorthwindException{
		 territoryDAO.deleteTerritory(territoryId);
	 }
	 
	 public void updateTerritory(Territory territory) throws NorthwindException{
		 territoryDAO.updateTerritory(territory);
		 
	 }
}
