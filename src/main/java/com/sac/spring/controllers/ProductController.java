package com.sac.spring.controllers;

import javax.validation.Valid;

import com.sac.spring.models.dao.IProductoDao;
import com.sac.spring.models.entity.Product;
import com.sac.spring.models.service.IProductService;
import com.sac.spring.models.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;



@Controller
@SessionAttributes("product")
public class ProductController {

	@Autowired
	private IProductService productService;
	@Autowired
	private IProductoDao repository;
	protected final Log logger = LogFactory.getLog(this.getClass());

	@RequestMapping(value = { "/products" }, method = RequestMethod.GET)
	public String getProducts(Model model) {
		model.addAttribute("product", new Product());
		return "product/product";
	}

	@PostMapping(value = "/addProduct")
	public String addProduct(@Valid Product product, BindingResult result, SessionStatus status,
			RedirectAttributes flash, @RequestParam(name = "productStatus", required = false) String productStatus) {
		if (result.hasErrors()) {
			flash.addFlashAttribute("error", "El roducto: '" + product.getName() + "' no pudo ser creado");
			return "redirect:/products";
		}
		try {
			status.setComplete();
			String flashMessage = (product.getId() != null) ? "Product editado con exito"
					: "Producto agregado con exito ";

			if (product.getId() != null && productStatus != null) {
				product.setEnable(true);
			} else if (product.getId() != null && productStatus == null) {
				product.setEnable(false);
			}

			if (product.getId() == null) {
				product.setEnable(true);
			}

			productService.addProduct(product);
			flash.addFlashAttribute("success", flashMessage);

		} catch (DataAccessException e) {
			logger.info(e.getMessage());
		}
		return "redirect:/products";
	}

	@Autowired
	private ReportService service;

	@GetMapping("/products/report/{format}")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
		return service.exportReport(format);
	}



//	@GetMapping(value = "/deleteProduct/{id}")
//	public String deleteProduct(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
//		if (id > 0) {
//			productService.deleteProduct(id);
//			flash.addFlashAttribute("success", "Producto eliminado exitosamente");
//		}
//		return "redirect:/products";
//	}
}
