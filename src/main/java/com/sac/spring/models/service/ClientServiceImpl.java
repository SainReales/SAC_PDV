package com.sac.spring.models.service;

import java.util.List;

import com.sac.spring.models.dao.IClientDao;
import com.sac.spring.models.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientDao clientDao;

	@Override
	@Transactional(readOnly = true)
	public List<Client> getAllClients() {
		return clientDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Client getClientById(long id) {
		return clientDao.findById(id).orElse(null);
	}

	@Override
	public void addClient(Client client) {
		clientDao.save(client);
	}

	@Override
	public void deleteClient(Long id) {
		clientDao.deleteById(id);
	}

	@Override
	public List<Client> findByTerm(String term) {
		return clientDao.findByTerm(term);
	}

	@Override
	public void saveAll(List<Client> listclient) {

		this.clientDao.saveAll(listclient);
	}

}
