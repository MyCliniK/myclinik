package com.myclinik.service;

import com.myclinik.model.Client;
import com.myclinik.model.Appointment;
import com.myclinik.repository.AppointmentRepository;
import com.myclinik.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements IClientService {
	@Autowired
	private ClientRepository repository;
	private AppointmentRepository aRepository;

	@Override
	public List<Client> findAll() {
		var clients = (List<Client>) repository.findAll();
		return clients;
	}
	public Client findOne(Long id) {
		var client = (Client) repository.findById(id).get();
		return client;
	}
	public Client createClient(){
		Client client = new Client();
		return client;
	}
	public void saveClient(Client c){
		repository.save(c);
	}
	public void deleteClient(Long id){
		repository.deleteById(id);
	}
	public void updateClient(Long id, Client newClient){
		newClient.setId(id);
		repository.save(newClient);
	}
	// public List<Appointment> findAppointments(Long id){
	// 	List<Appointment> appointments = new ArrayList();
	// 	var app = (List<Appointment>) aRepository.findAll();
	// 	for (Appointment a: app) {
	// 		if (a.getClient() == (Client) repository.findById(id).get()){
	// 			appointments.add(a);
	// 		}
	// 	}
	// 	return appointments;
	// }

}
