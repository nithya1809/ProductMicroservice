package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	public Product findById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(()->new ProductNotFoundException(id));
	}
	
	public Product findByName(String name) {
	    return productRepository.findByName(name)
	        .orElseThrow(() -> new ProductNotFoundException(name));
	}
	
	public List<Product> findByCategory(String category) {
	    List<Product> products = productRepository.findByCategory(category);
	    if (products.isEmpty()) {
	        throw new CategoryNotFoundException(category);
	    }
	    return products;
	}

	public List<Product> findByPriceRange(double minPrice, double maxPrice, String category) {
	    List<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice, category);
	    if (products.isEmpty()) {
	        throw new ProductNotFoundException("No products found in category '" + category + "' within price range " + minPrice + " to " + maxPrice);
	    }
	    return products;
	}

	
	public void addProduct(Product prod){
		Product savedProd=productRepository.save(prod);
		//return ResponseEntity.status(HttpStatus.CREATED).body(savedProd);
	}
	
	public void deleteProduct(Long id) {
	    Product product = productRepository.findById(id)
	        .orElseThrow(() -> new ProductNotFoundException(id));
	    productRepository.delete(product);
	}
	
	public String reduceQuantity(String name, int quantity) {
		Optional<Product> prod=productRepository.findByName(name);
		if(prod!=null) {
			Product product=prod.get();
			if(product.getQuantity()>=quantity) {
				product.setQuantity(product.getQuantity()-quantity);
				productRepository.save(product);
				return "Purchase successful!";
			}else {
				return "Not enough quantity";
			}
		}else
			return "No such products found";
	}
	
	//To update quantity of already existing product
	public void updateQuantity(String name, int newQuantity) {
	    Product product = productRepository.findByName(name)
	        .orElseThrow(() -> new ProductNotFoundException("Product not found with name " + name));
	    
	    product.setQuantity(newQuantity);
	    productRepository.save(product);
	}
	
	//To restore quantity after a particular order gets deleted
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