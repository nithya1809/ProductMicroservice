package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

	List<Product> findAll();
	
	@Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	Optional<Product> findByName(@Param("name") String name);
	
	@Query("SELECT p FROM Product p WHERE LOWER(p.category) LIKE LOWER(:category)")
	List<Product> findByCategory(@Param("category") String category);
	
	@Query("SELECT p FROM Product p WHERE p.price> :minPrice AND p.price< :maxPrice AND p.category=:category")
	List<Product> findByPriceBetween(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice, @Param("category") String category);
	
	@Query("SELECT COUNT(p)>0 FROM Product p WHERE p.name=:name")
	boolean productExists(@Param("name") String name);
	
	@Query("SELECT p FROM Product p WHERE p.id=:id")
	Optional<Product> findById(@Param("id")Long id);
	
	
}