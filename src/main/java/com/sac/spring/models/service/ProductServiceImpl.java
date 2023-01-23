package com.sac.spring.models.service;

import java.util.List;

import com.sac.spring.models.dao.IProductoDao;
import com.sac.spring.models.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductoDao productDao;

	@Override
	public List<Product> getAllProducts() {
		return productDao.findAll();
	}

	@Override
	public Product getProductById(long id) {
		return productDao.findById(id).orElse(null);
	}

	@Override
	public void addProduct(Product product) {
		productDao.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		productDao.deleteById(id);
	}

	@Override
	public List<Product> findProductByTerm(String term) {
		return productDao.findByName(term);
	}

}
