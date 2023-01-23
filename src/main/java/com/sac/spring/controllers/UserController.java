package com.sac.spring.controllers;

import javax.validation.Valid;

import com.sac.spring.models.dao.IProductoDao;
import com.sac.spring.models.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import com.sac.spring.models.entity.Role;
import com.sac.spring.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sac.spring.models.service.IUserService;

import java.io.FileNotFoundException;

@Secured({ "ROLE_ADMIN" })
@Controller
@SessionAttributes("user")
public class UserController {

	@Autowired
	private IUserService userService;



	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/users")
	public String users(Model model) {
		model.addAttribute("user", new User());
		return "user/user";
	}

	@PostMapping("/addUser")
	public String addUser(@Valid User user, BindingResult result, SessionStatus status, RedirectAttributes flash,
			@RequestParam(name = "role_user", required = false) String roleUser,
			@RequestParam(name = "role_admin", required = false) String roleAdmin,
			@RequestParam(name = "status", required = false) String userStatus,
			@RequestParam(name = "editPassword", required = false) String editPassword) {

		if (user.getPassword() != null && user.getPassword().length() > 2) {
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
		}

		if (result.hasErrors()) {
			flash.addFlashAttribute("error", "");
			return "redirect:user/user";
		}

		Role role = null;
		if (roleUser != null) {
			role = new Role();
			role.setAuthority("ROLE_USER");
			user.addRole(role);
		}

		if (roleAdmin != null) {
			role = new Role();
			role.setAuthority("ROLE_ADMIN");
			user.addRole(role);
		}

		if (user.getId() != null && userStatus != null) {
			user.setEnable(true);
		} else if (user.getId() != null && userStatus == null) {
			user.setEnable(false);
		}

		if (user.getId() == null) {
			user.setEnable(true);
		}

		if (editPassword != null && editPassword.length() > 2) {
			String encodedPassword = passwordEncoder.encode(editPassword);
			user.setPassword(encodedPassword);
		}
		String flashMessage = (user.getId() != null) ? "User editado con exito" : "User agregado con exito ";

		userService.addUser(user);
		status.setComplete();
		flash.addFlashAttribute("success", flashMessage);
		return "redirect:/users";
	}


	@GetMapping("/deleteUser/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes flash) {

		if (id == null || id < 1) {
			flash.addFlashAttribute("error", "the user id is not valid");
			return "redirect:/users";
		}

		userService.deleteUser(id);
		flash.addFlashAttribute("success", "User deleted");
		return "redirect:/users";
	}


}
