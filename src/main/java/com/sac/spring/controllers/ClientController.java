package com.sac.spring.controllers;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;


import com.sac.spring.helper.ResponseMessage;
import com.sac.spring.models.dao.IClientDao;
import com.sac.spring.models.entity.Client;
import com.sac.spring.models.service.*;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.JRException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("client")
public class ClientController {


	@Autowired
	IClientDao repo;

	@Autowired
	private EnvioEmail envioEmail;

	@Autowired
	private ClientServiceImpl clientService;

	private Logger logger = LoggerFactory.getLogger(ClientController.class);

	@RequestMapping(value = {"/clients"}, method = RequestMethod.GET)
	public String getClients(Model model) {
		model.addAttribute("client", new Client());
		return "client/client";
	}

	@RequestMapping(value = {"/addClient"}, method = RequestMethod.POST)
	public String addClient(@Valid Client client, BindingResult result, SessionStatus status,
							RedirectAttributes flash, @RequestParam("file") MultipartFile foto
	) {
		if (result.hasErrors()) {
//			flash.addFlashAttribute("error", "Error. El client: ".concat(client.getName().concat(" no es valido")));
			flash.addFlashAttribute("error", result.getFieldError());
			return "redirect:/clients";
		}
		try {
			status.setComplete();
			String message = (client.getId() == null) ? "Cliente agregado" : "Cliente editado con exito";
			clientService.addClient(client);
			flash.addFlashAttribute("success", message);
		} catch (Exception e) {
			logger.info("Error al crear / editar el cliente: ".concat(e.getMessage()));
		}
		if (!foto.isEmpty()) {
			Path directorioImagenes = Paths.get("src//main//resources//static/images");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			try {
				byte[] bytesImg = foto.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + foto.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				client.setFoto(foto.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}

			return "redirect:/clients";
		}
		return "redirect:/clients";
	}


		@GetMapping("/deleteClient/{id}")
		public String deleteClient (@PathVariable("id") Long id, RedirectAttributes flash) {
			if (id > 0) {
				flash.addFlashAttribute("succes", "cliente eliminado con exito");
				clientService.deleteClient(id);
			}
			return "redirect:/clients";
		}













	@PostMapping("/contact/mail")
	public String submitContactC(HttpServletRequest request, RedirectAttributes redirectAttrs,
								 @RequestParam("attachment") MultipartFile multipartFile)
			throws MessagingException, UnsupportedEncodingException {

		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		List<Client> listClients = repo.findAll();

		String mailContent = "<p><b>Remitente: </b>" + fullname + "</p>";
		mailContent += "<p><b>Contacto: </b>" + email + "</p>";
		mailContent += "<p><b>Asunto: </b>" + subject + "</p>";
		mailContent += "<p><b>Mensaje: </b>" + content + "</p>";
		mailContent += "<hr><img src='cid:logoImage' />";

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		try {

			for (Client c : listClients) {

				try {
					envioEmail.sendEmail(subject, mailContent, c.getEmail(), fileName, multipartFile);

				} catch (Exception e) {
					redirectAttrs.addFlashAttribute("mensaje", "No se pudo enviar los correos")
							.addFlashAttribute("clase", "danger");
					return "redirect:/clients";
				}

			}

			redirectAttrs.addFlashAttribute("mensaje", "Los correos se enviaron exitosamente")
					.addFlashAttribute("clase", "warning");
			return "redirect:/clients";

		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("mensaje", "No se pudo enviar todos los mensajes")
					.addFlashAttribute("clase", "danger");
			return "redirect:/clients";
		}


	}
}


