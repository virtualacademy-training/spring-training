package com.virtualacademy.training.model;

import java.util.List;

public class Territory {
	private Integer territoryId;
	private String description;
	private Region region;
	
	private List<Employee> employees;
	public Integer getTerritoryId() {
		return territoryId;
	}
	public void setTerritoryId(Integer territoryId) {
		this.territoryId = territoryId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
}
