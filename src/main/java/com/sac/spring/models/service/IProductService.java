package com.sac.spring.models.service;

import java.util.List;

import com.sac.spring.models.entity.Product;

public interface IProductService {

	public List<Product> getAllProducts();

	public Product getProductById(long id);

	public List<Product> findProductByTerm(String term);

	public void addProduct(Product product);

	public void deleteProduct(Long id);
}
