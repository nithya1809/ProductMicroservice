package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

/**
 * Service class that handles business logic related to Product operations.
 * Acts as an intermediary between the controller and repository layers.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Retrieves all products from the database.
     *
     * @return a list of all products
     */
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Finds a product by its ID.
     *
     * @param id the product ID
     * @return the product with the specified ID
     * @throws ProductNotFoundException if no product is found with the given ID
     */
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    /**
     * Finds a product by its name.
     *
     * @param name the name of the product
     * @return the product with the specified name
     * @throws ProductNotFoundException if no product is found with the given name
     */
    public Product findByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new ProductNotFoundException(name));
    }

    /**
     * Retrieves all products belonging to a specific category.
     *
     * @param category the category name
     * @return a list of products in the specified category
     * @throws CategoryNotFoundException if no products are found in the category
     */
    public List<Product> findByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        if (products.isEmpty()) {
            throw new CategoryNotFoundException(category);
        }
        return products;
    }

    /**
     * Finds products within a specified price range and category.
     *
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @param category the product category
     * @return a list of products matching the criteria
     * @throws ProductNotFoundException if no matching products are found
     */
    public List<Product> findByPriceRange(double minPrice, double maxPrice, String category) {
        List<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice, category);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found in category '" + category + "' within price range " + minPrice + " to " + maxPrice);
        }
        return products;
    }

    /**
     * Adds a new product to the database.
     *
     * @param prod the product to add
     */
    public void addProduct(Product prod) {
        productRepository.save(prod);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the product ID
     * @throws ProductNotFoundException if no product is found with the given ID
     */
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(product);
    }

    /**
     * Reduces the quantity of a product by a given amount.
     *
     * @param name     the name of the product
     * @param quantity the amount to reduce
     * @return a message indicating the result of the operation
     */
    public String reduceQuantity(String name, int quantity) {
        Optional<Product> prod = productRepository.findByName(name);
        if (prod.isPresent()) {
            Product product = prod.get();
            if (product.getQuantity() >= quantity) {
                product.setQuantity(product.getQuantity() - quantity);
                productRepository.save(product);
                return "Purchase successful!";
            } else {
                return "Not enough quantity";
            }
        } else {
            return "No such products found";
        }
    }

    /**
     * Updates the quantity of an existing product.
     *
     * @param name        the name of the product
     * @param newQuantity the new quantity to set
     * @throws ProductNotFoundException if no product is found with the given name
     */
    public void updateQuantity(String name, int newQuantity) {
        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with name " + name));

        product.setQuantity(newQuantity);
        productRepository.save(product);
    }

    /**
     * Increases the quantity of a product by a given amount (e.g., after order cancellation).
     *
     * @param name     the name of the product
     * @param quantity the quantity to restore
     * @return a message indicating the result of the operation
     */
    public String increaseQuantity(String name, int quantity) {
        Optional<Product> prod = productRepository.findByName(name);
        if (prod.isPresent()) {
            Product product = prod.get();
            product.setQuantity(product.getQuantity() + quantity);
            productRepository.save(product);
            return "Quantity restored after order deletion!";
        } else {
            return "Product not found";
        }
    }
}
