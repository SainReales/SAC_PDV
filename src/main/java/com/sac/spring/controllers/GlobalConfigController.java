package com.sac.spring.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sac.spring.models.entity.GlobalConfig;
import com.sac.spring.models.service.GlobalConfigServiceImpl;

@Secured({ "ROLE_ADMIN" })
@Controller
@SessionAttributes("globalConfig")
public class GlobalConfigController {

	@Autowired
	private GlobalConfigServiceImpl globalConfigService;

	@RequestMapping("/properties")
	public String properties(Model model) {
		GlobalConfig globalConfig = globalConfigService.properties();
		model.addAttribute("globalConfig", globalConfig);

		return "globalConfig/globalConfig";
	}

	@PostMapping("/saveGlobalConfig")
	public String save(@Valid GlobalConfig config, BindingResult result, RedirectAttributes flash) {

		if (result.hasErrors()) {
			flash.addFlashAttribute("error", "All the fields are required");
			return "redirect:/properties";
		}

		flash.addFlashAttribute("success", "Config Saved");
		globalConfigService.saveConfig(config);
		return "redirect:/properties";
	}
}
