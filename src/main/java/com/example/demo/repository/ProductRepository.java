package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Product;

/**
 * Repository interface for Product entity.
 * Extends JpaRepository to provide CRUD operations and defines custom queries.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Retrieves all products from the database.
     *
     * @return a list of all products
     */
    List<Product> findAll();

    /**
     * Finds a product by its name (case-insensitive, partial match).
     *
     * @param name the product name to search for
     * @return an Optional containing the product if found, or empty otherwise
     */
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Optional<Product> findByName(@Param("name") String name);

    /**
     * Retrieves all products that belong to a specific category (case-insensitive match).
     *
     * @param category the category name to filter by
     * @return a list of products in the given category
     */
    @Query("SELECT p FROM Product p WHERE LOWER(p.category) LIKE LOWER(:category)")
    List<Product> findByCategory(@Param("category") String category);

    /**
     * Retrieves products in a specific category and within a given price range.
     *
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @param category the category to filter by
     * @return a list of products matching the criteria
     */
    @Query("SELECT p FROM Product p WHERE p.price > :minPrice AND p.price < :maxPrice AND p.category = :category")
    List<Product> findByPriceBetween(
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice,
            @Param("category") String category
    );

    /**
     * Checks if a product with the given name exists.
     *
     * @param name the product name to check
     * @return true if a product with the name exists, false otherwise
     */
    @Query("SELECT COUNT(p) > 0 FROM Product p WHERE p.name = :name")
    boolean productExists(@Param("name") String name);

    /**
     * Finds a product by its ID.
     *
     * @param id the ID of the product
     * @return an Optional containing the product if found, or empty otherwise
     */
    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Optional<Product> findById(@Param("id") Long id);

}
