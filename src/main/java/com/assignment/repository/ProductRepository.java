package com.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assignment.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	 @Query("SELECT p FROM Product p WHERE LOWER(p.category) = LOWER(:category)")
	    List<Product> dataByCategory( String category);
	 
	 
	 @Query("SELECT p from Product p WHERE p.price BETWEEN :minprice AND :maxprice")
	 List<Product> dataByPrice(@Param("minprice") int minprice,@Param("maxprice")  int maxprice);

	 @Query("SELECT p from Product p WHERE lower(p.name) like lower(concat('%',:name,'%'))")
	 List<Product> dataBySearch(@Param("name") String name);

	 @Query("SELECT p from Product p WHERE p.inStock=true")
	 List<Product> dataByStock();

	 @Query("SELECT p from Product p ORDER BY p.price asc,p.name asc ")
	 List<Product> dataBySorting();

	 @Query("SELECT count(p) from Product p where p.name = :cname")
	 Long CountByName(@Param("cname") String cname);
	

}
