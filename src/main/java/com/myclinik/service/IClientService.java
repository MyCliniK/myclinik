package com.myclinik.service;

import com.myclinik.model.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {
	List<Client> findAll();
	Client findOne(Long id);
	Client createClient();
	void saveClient(Client c);
	void deleteClient(Long id);
	void updateClient(Long id, Client newClient);
}
