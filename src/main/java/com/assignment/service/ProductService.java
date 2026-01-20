package com.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.Dao.ProductDao;
import com.assignment.model.Product;

@Service
public class ProductService {
	
	@Autowired
	ProductDao productdao;
	
	public List<Product> insertProduct(List<Product> product) {
		return productdao.insertProduct(product);
	}
	public Product setDataById(Product product){
		
		return productdao.setDataById(product);
	}
	
	
	public List<Product> getAllData(){
		return productdao.getAllData();
	}
	
	public Product getDataById(Long id){
		
		return productdao.getDataById(id);
	}
	public List<Product> dataByCategory(String category){
		return productdao.dataByCategory(category);
		
	}
	public List<Product> dataByPrice(int minprice, int maxprice) {
		// TODO Auto-generated method stub
		return productdao.dataByPrice(minprice,maxprice);
	}
	public List<Product> dataBySearch(String name) {
		// TODO Auto-generated method stub
		return productdao.dataBySearch(name);
	}
	public List<Product> dataByStock() {
		// TODO Auto-generated method stub
		return productdao.dataByStock();
	}
	public List<Product> dataBySorting() {
		// TODO Auto-generated method stub
		return productdao.dataBySorting();
	}
	public Long CountByName(String cname) {
		// TODO Auto-generated method stub
		return productdao.CountByName(cname);
	}
	public void DeleteById(Long id) {
		// TODO Auto-generated method stub
		productdao.DeleteById(id);
	}
	


}
