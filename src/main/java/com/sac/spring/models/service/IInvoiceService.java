package com.sac.spring.models.service;

import java.util.List;

import com.sac.spring.models.entity.Invoice;

public interface IInvoiceService {

	public List<Invoice> getInvoices();

	public Invoice getInvoiceById(Long id);

	public void addInvoice(Invoice client);

	public void deleteInvoice(Long id);

	public List<Invoice> fetchdWithClientWhithItemInvoiceWithProduct();

}
