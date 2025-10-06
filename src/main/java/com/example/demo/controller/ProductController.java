package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

/**
 * REST controller for managing Product-related operations.
 * Provides endpoints to create, retrieve, update, and delete products.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Retrieves all products.
     *
     * @return a list of all products
     */
    @GetMapping("/displayAll")
    public List<Product> getAll() {
        return productService.findAll();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product
     * @return the product with the given ID
     */
    @GetMapping("/findById/{id}")
    public Product findById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    /**
     * Retrieves a list of products by category.
     *
     * @param category the category to filter by
     * @return a list of products in the given category
     */
    @GetMapping("/findByCategory/{category}")
    public List<Product> findByCategory(@PathVariable("category") String category) {
        return productService.findByCategory(category);
    }

    /**
     * Retrieves a product by its name.
     *
     * @param name the name of the product
     * @return the product with the given name
     */
    @GetMapping("/findByName/{name}")
    public Product findByName(@PathVariable("name") String name) {
        return productService.findByName(name);
    }

    /**
     * Retrieves a list of products within a specified price range and category.
     *
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @param category the category to filter by
     * @return a list of products matching the criteria
     */
    @GetMapping("/filterByPrice")
    public ResponseEntity<List<Product>> findByRangeBetween(
            @RequestParam("minPrice") double minPrice,
            @RequestParam("maxPrice") double maxPrice,
            @RequestParam("category") String category) {

        List<Product> products = productService.findByPriceRange(minPrice, maxPrice, category);
        return ResponseEntity.ok(products);
    }

    /**
     * Adds a new product.
     *
     * @param prod the product to add
     * @return a confirmation message
     */
    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product prod) {
        productService.addProduct(prod);
        return "Product added successfully";
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     * @return a confirmation message
     */
    @DeleteMapping("/removeProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted successfully!", HttpStatus.OK);
    }

    /**
     * Updates the quantity of a product by name.
     *
     * @param name     the name of the product
     * @param quantity the new quantity to set
     */
    @PutMapping("/updateQuantity")
    public void updateQuantity(@RequestParam("name") String name, @RequestParam("quantity") int quantity) {
        productService.updateQuantity(name, quantity);
    }

    /**
     * Reduces the quantity of a product by a given amount.
     *
     * @param name     the name of the product
     * @param quantity the amount to reduce
     * @return a confirmation message
     */
    @PutMapping("findByName/{name}/reduceQuantity")
    public ResponseEntity<String> reduceQuantity(@PathVariable("name") String name, @RequestParam("quantity") int quantity) {
        return ResponseEntity.ok(productService.reduceQuantity(name, quantity));
    }

    /**
     * Restores or increases the quantity of a product by a given amount.
     *
     * @param name     the name of the product
     * @param quantity the amount to increase
     * @return a confirmation message
     */
    @PutMapping("{name}/restore")
    public ResponseEntity<String> restoreQuantity(@PathVariable("name") String name, @RequestParam("quantity") int quantity) {
        return ResponseEntity.ok(productService.increaseQuantity(name, quantity));
    }
}
