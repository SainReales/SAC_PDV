package com.sac.spring.models.service;

import java.util.List;

import com.sac.spring.models.entity.Client;

public interface IClientService {

	public List<Client> getAllClients();

	public Client getClientById(long id);

	public void addClient(Client client);

	public void deleteClient(Long id);

	public List<Client> findByTerm(String term);

	void saveAll(List<Client> listclient);
}
