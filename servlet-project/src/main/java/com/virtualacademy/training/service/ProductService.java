package com.virtualacademy.training.service;


import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.dao.ProductDAO;
import com.virtualacademy.training.model.Product;

import java.util.ArrayList;
import java.util.List;
 
public class ProductService {
	 private ProductDAO productDAO = new ProductDAO();
	public void saveProduct(Product product) throws NorthwindException{
		 productDAO.saveProduct(product);
	 }
	
	public Product findProduct(int productId) throws NorthwindException{
		 Product product = null;
		 product = productDAO.findProduct(productId);  
		 return product;
	 }
	 
	 public List<Product> getAllProduct() throws NorthwindException{
		 List<Product> products = new ArrayList<Product>();
		 products = productDAO.getAllProduct();
		 return products;
	 }
	 
	 public void deleteProduct(int productId) throws NorthwindException{
		  productDAO.deleteProduct(productId);
	 }
	 
	 public void updateProduct(Product product) throws NorthwindException{
		  productDAO.updateProduct(product);		 
	 }
}
