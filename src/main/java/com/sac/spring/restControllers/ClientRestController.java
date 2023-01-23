package com.sac.spring.restControllers;

import java.util.List;

import com.sac.spring.models.entity.Client;
import com.sac.spring.models.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRestController {

	@Autowired
	private IClientService clientService;

	@GetMapping(value = { "/rest/clients" })
	public List<Client> clients() {
		return clientService.getAllClients();
	}

	@GetMapping("/rest/client/{id}")
	public Client client(@PathVariable("id") Long id) {
		return clientService.getClientById(id);
	}

	@GetMapping("/rest/clientsByTerm/{term}")
	public List<Client> clientsByTerm(@PathVariable("term") String term) {
		return clientService.findByTerm(term);
	}

}
