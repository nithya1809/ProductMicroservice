package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/displayAll")
	public List<Product> getAll(){
		return productService.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public Product findById(@PathVariable("id") Long id) {
		return productService.findById(id);
	}
	
	@GetMapping("/findByCategory/{category}")
	public List<Product> findByCategory(@PathVariable("category") String category){
		return productService.findByCategory(category);
	}
	
	@GetMapping("/findByName/{name}")
	public Product findByName(@PathVariable("name") String name){
		return productService.findByName(name);
	}
	
	@GetMapping("/filterByPrice")
	public ResponseEntity<List<Product>> findByRangeBewteen(@RequestParam("minPrice")double minPrice,
			@RequestParam("maxPrice") double maxPrice, @RequestParam("category") String category){
		List<Product> products=productService.findByPriceRange(minPrice, maxPrice, category);
		return ResponseEntity.ok(products);
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@RequestBody Product prod) {
		productService.addProduct(prod);
		return "Product added successfully";
	}
	
	@DeleteMapping("/removeProduct/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
	    productService.deleteProduct(id);
	    return new ResponseEntity<>("Product deleted successfully!", HttpStatus.OK);
	}

	
	@PutMapping("/updateQuantity")
	public void updateQuantity(@RequestParam("name") String name, @RequestParam("quantity") int quantity) {
		productService.updateQuantity(name, quantity);
	}
	
	@PutMapping("findByName/{name}/reduceQuantity")
	public ResponseEntity<String> reduceQuantity(@PathVariable("name") String name, @RequestParam("quantity") int quantity){
		return ResponseEntity.ok(productService.reduceQuantity(name, quantity));
	}
	
	@PutMapping("{name}/restore")
	public ResponseEntity<String> restoreQuantity(@PathVariable("name") String name, @RequestParam("quantity") int quantity) {
	    return ResponseEntity.ok(productService.increaseQuantity(name, quantity));
	}

	
}