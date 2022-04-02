package com.myclinik.service;

import com.myclinik.model.Client;
import com.myclinik.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements IClientService {
	@Autowired
	private ClientRepository repository;

	@Override
	public List<Client> findAll() {
		var clients = (List<Client>) repository.findAll();
		return clients;
	}
	public Client findOne(Long id) {
		var client = (Client) repository.findById(id).get();
		return client;
	}
	/*oid Client createClient(){
		Client client = null;
		repository.save(client);
	}*/
}
