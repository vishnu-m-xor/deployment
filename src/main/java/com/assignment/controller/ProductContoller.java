package com.assignment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.model.Product;
import com.assignment.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductContoller {
	@Autowired
	ProductService productservice;
	
	
	
	@PostMapping("/insert")
	public List<Product>  insertData(@RequestBody List<Product> product) 
	{
		return productservice.insertProduct(product);		
	}
	@PostMapping("/insertone")
	public Product setDataById(@RequestBody Product product){
		
		return productservice.setDataById(product);
	}
	@GetMapping("/getdata")
	public List<Product> getAllData(){
		return productservice.getAllData();
	}
	@GetMapping("/{id}")
	public Product getDataById(@PathVariable Long id){
		
		return productservice.getDataById(id);
	}
	
	@GetMapping("/category/{category}")
	public List<Product> dataByCategory(@PathVariable String category){
		return productservice.dataByCategory(category);		
	}
	
	@GetMapping("/price-range")
	public List<Product> dataByPrice(@RequestParam int minprice,@RequestParam int maxprice){
		return productservice.dataByPrice(minprice,maxprice);
	}
	
	@GetMapping("/search")
	public List<Product> dataBySearch(@RequestParam String name){
		return productservice.dataBySearch(name);
		
	}
	@GetMapping("/in-stock")
	public List<Product> dataByStock(){
		return productservice.dataByStock();
	}
	@GetMapping("/sorting")
	public List<Product> dataBySorting(){
		return productservice.dataBySorting();
	}
	@GetMapping("/{cname}/count")
	public Long CountByName(@PathVariable String cname){
		return productservice.CountByName(cname);	
	}
	@DeleteMapping("/{id}")
	public void DeleteById(Long id) {
		 productservice.DeleteById(id);
	}
	

}
