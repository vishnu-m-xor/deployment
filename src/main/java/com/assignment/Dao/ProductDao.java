package com.assignment.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assignment.model.Product;
import com.assignment.repository.ProductRepository;

@Repository
public class ProductDao {
	@Autowired
	ProductRepository productrepository;
	
	public List<Product> insertProduct(List<Product> product) {
		return productrepository.saveAll(product);
	}
	public Product setDataById(Product product) {
		// TODO Auto-generated method stub
		return productrepository.save(product);
	}
	public List<Product> getAllData(){
		return  productrepository.findAll();
	}
	public Product getDataById(Long id) {
		// TODO Auto-generated method stub
		return productrepository.findById(id).orElseThrow(() -> new RuntimeException("404 not found"));
	}
	public List<Product> dataByCategory(String category){
		return productrepository.dataByCategory(category);
		
	}
	public List<Product> dataByPrice(int minprice, int maxprice) {
		// TODO Auto-generated method stub
		return productrepository.dataByPrice(minprice,maxprice);
	}
	public List<Product> dataBySearch(String name) {
		// TODO Auto-generated method stub
		return productrepository.dataBySearch(name);
	}
	public List<Product> dataByStock() {
		// TODO Auto-generated method stub
		return productrepository.dataByStock();
	}
	public List<Product> dataBySorting() {
		// TODO Auto-generated method stub
		return productrepository.dataBySorting();
	}
	public Long CountByName(String cname) {
		// TODO Auto-generated method stub
		return productrepository.CountByName(cname);
	}
	public void DeleteById(Long id) {
		// TODO Auto-generated method stub
		productrepository.deleteById(id);
		
	}

		
		
	}


