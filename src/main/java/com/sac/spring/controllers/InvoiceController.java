package com.sac.spring.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sac.spring.helper.InvoiceOperations;
import com.sac.spring.models.dao.IUserDao;
import com.sac.spring.models.service.IClientService;
import com.sac.spring.models.service.IInvoiceService;
import com.sac.spring.models.service.IProductService;

import com.sac.spring.models.service.RepVentas;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sac.spring.models.entity.Client;
import com.sac.spring.models.entity.Invoice;
import com.sac.spring.models.entity.InvoiceItem;
import com.sac.spring.models.entity.Product;
import com.sac.spring.models.entity.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

@Controller
@SessionAttributes("invoiceController")
public class InvoiceController {

	@Autowired
	private IClientService clientService;

	@Autowired
	private IProductService productService;

	@Autowired
	private IInvoiceService invoiceService;

	@Autowired
	private IUserDao userDao;

	@Autowired
	private InvoiceOperations invoiceOperations;

//	private Logger logger = LoggerFactory.getLogger(InvoiceController.class);

	@GetMapping("/invoices")
	public String list(Model model) {
		model.addAttribute("invoice", new Invoice());
		return "invoice/listInvoices";
	}

	@GetMapping("/createInvoice")
	public String create(Model model) {
		model.addAttribute("invoice", new Invoice());
		return "invoice/createInvoice";
	}

	@PostMapping("/saveInvoice")
	public String save(@Valid Invoice invoice, BindingResult result, SessionStatus status, RedirectAttributes flash,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] quantity,
			@RequestParam(name = "clientId", required = false) Long id) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		User user = userDao.findByUsername(username);

		if (result.hasErrors()) {
			flash.addFlashAttribute("error", result.getFieldError());
			return "redirect:/createInvoice";

		}

		if (itemId == null || itemId.length == 0) {
			flash.addFlashAttribute("error", "La factura deber tener al menos un item");
			return "redirect:/createInvoice";
		}

		if (id == null || id < 1) {
			flash.addFlashAttribute("error", "El id " + id + " es incorrecto");
			return "redirect:/createInvoice";
		}

		Client client = clientService.getClientById(id);

		if (client == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/createInvoice";
		}

		invoice.setClient(client);
		invoice.setUser(user);

		for (int i = 0; i < itemId.length; i++) {
			Product product = productService.getProductById(itemId[i]);
			int stock = product.getStock();

			if (stock > 0) {
				product.setStock(stock - quantity[i]);
			}

			InvoiceItem item = new InvoiceItem();
			item.setQuatity(quantity[i]);
			item.setProduct(product);
			item.setItbis((product.getIva()));
			item.setPrice(product.getPrice());
			invoice.addInvoiceItem(item);
		}

		invoiceService.addInvoice(invoice);
		flash.addFlashAttribute("success", "Factura creada !");
		status.setComplete();
		return "redirect:/invoices";
	}

	@GetMapping("/invoice/details/{id}")
	public String details(Model model, @PathVariable("id") Long id, RedirectAttributes flash) {

		if (id == null || id < 1) {
			flash.addFlashAttribute("error", "El id: " + id + " no es valido");
			return "redirect:/invoices";
		}

		Invoice invoice = invoiceService.getInvoiceById(id);
		if (invoice == null) {
			flash.addFlashAttribute("error", "No existe ninguna factura con el id: " + id);
			return "redirect:/invoices";
		}

		model.addAttribute("invoice", invoice);
		model.addAttribute("title", "Factura: ".concat(invoice.getDescription()));

		return "invoice/details";
	}

	@Autowired
	private RepVentas service;

	@GetMapping("/invoices/report/{format}")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException, SQLException {
		return service.exportReport(format);
	}



}
