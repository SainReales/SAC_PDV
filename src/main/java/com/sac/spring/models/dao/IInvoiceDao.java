package com.sac.spring.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sac.spring.models.entity.Invoice;

@Repository("invoiceRepository")
public interface IInvoiceDao extends JpaRepository<Invoice, Long> {

	@Query("select i from Invoice i join fetch i.client c join fetch i.items")
	public List<Invoice> fetchdWithClientWhithItemInvoiceWithProduct();
}
