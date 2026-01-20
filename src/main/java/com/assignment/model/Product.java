package com.assignment.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
	
	private String category;
	
	private BigDecimal price;
	
	private Integer stockQuantity;
	
	private Boolean inStock;

	public Product(Long id, String name, String category, BigDecimal i, Integer stockQuantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = i;
        this.stockQuantity = stockQuantity;
        this.inStock = stockQuantity != null && stockQuantity > 0;
    }

	
	public Product(long id2, String name2, String category2, BigDecimal i, int stockQuantity2) {
		// TODO Auto-generated constructor stub
		this.id = id2;
        this.name = name2;
        this.category = category2;
        this.price = i;
        this.stockQuantity = stockQuantity2;
        this.inStock =  stockQuantity2 > 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
		this.inStock = (stockQuantity>0 && stockQuantity!=null);
	}

	public Boolean getInStock() {
		return inStock;
	}

	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
	}
	
	@Override
	public String toString() {
	    return "Product{" +
	            "id=" + id +
	            ", name='" + name + '\'' +
	            ", category='" + category + '\'' +
	            ", price=" + price +
	            ", stockQuantity=" + stockQuantity +
	            ", inStock=" + inStock +
	            '}';
	}

	
	

}
