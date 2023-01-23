package com.sac.spring.restControllers;

import java.util.List;

import com.sac.spring.models.entity.Invoice;
import com.sac.spring.models.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceRestController {

	@Autowired
	private InvoiceServiceImpl invoiceService;

	@GetMapping(value = { "/rest/invoices" })
	public List<Invoice> invoces() {
		return invoiceService.fetchdWithClientWhithItemInvoiceWithProduct();
	}

	@GetMapping("/invoice/{id}")
	public Invoice invoice(@PathVariable("id") Long id) {
		return invoiceService.getInvoiceById(id);
	}

}
