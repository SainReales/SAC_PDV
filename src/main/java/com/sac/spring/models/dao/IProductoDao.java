package com.sac.spring.models.dao;

import java.util.List;

import com.sac.spring.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("productRepository")
public interface IProductoDao extends JpaRepository<Product, Long> {

	@Query("select p from Product p where p.name like %?1% and p.stock > 0 and p.enable = 1")
	public List<Product> findByName(String term);

	public List<Product> findByNameLikeIgnoreCase(String term);

}
