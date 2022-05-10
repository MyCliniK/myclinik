package com.myclinik.myclinik;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.myclinik.model.Client;
import com.myclinik.model.Appointment;
import com.myclinik.service.IClientService;
import com.myclinik.service.IAppointmentService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.NoSuchElementException;

@SpringBootTest
public class ClientTests {

	@Autowired
	private IClientService clientService;
	@Autowired
	private IClientService appointmentService;

	private Client client;

	@Test
	@Transactional
	final void testClient() {
		client = new Client();
		client.setFirstName("John");
		client.setLastName("Doe");
		client.setDni("12345678");
		client.setBirthdate(new Date(20000101));
		client.setSex("M");
		client.setPhone("123456789");
		client.setEmail("john@mail.com");
		client.setPromos(false);
		client.setMedicalObservations("this is a medical observation");
		client.setObservations("this is an observation");
		List<Appointment> appointments = new ArrayList<Appointment>();
		client.setAppointments(appointments);

		clientService.saveClient(client);
		Client client2 = clientService.findOne(client.getId());
		assertThat(client2.equals(client)).isTrue();

		client.setFirstName("John2");
		clientService.saveClient(client);
		client2 = clientService.findOne(client.getId());
		assertThat(client2.getFirstName().equals("John")).isFalse();

		clientService.deleteClient(client.getId());
		try {
			client2 = clientService.findOne(client.getId());
		} catch (NoSuchElementException e) {
			client2 = null;
		}
		assertThat(client2 == null).isTrue();
	}

}
