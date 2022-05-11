package com.myclinik.myclinik;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.myclinik.model.Client;
import com.myclinik.model.Treatment;
import com.myclinik.model.Appointment;
import com.myclinik.service.IAppointmentService;

import java.util.Date;
import java.time.LocalDateTime;

import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

@SpringBootTest
public class AppointmentTests {

	@Autowired
	private IAppointmentService appointmentService;

	@Test
	@Transactional
	final void testAppointment() {
		Appointment a1 = new Appointment();
		Client c1 = new Client();
		Treatment t1 = new Treatment();
		a1.setDone(false);
		a1.setPaid(false);
		a1.setAppointmentDate(LocalDateTime.parse("2022-01-01T00:00"));
		a1.setClient(c1);
		a1.setTreatment(t1);

		appointmentService.save(a1);
		Appointment a2 = appointmentService.get(a1.getId());
		assertThat(a2.equals(a1)).isTrue();

		a1.setDone(true);
		appointmentService.save(a1);
		a2 = appointmentService.get(a1.getId());
		assertThat(a2.getDone()).isTrue();

		appointmentService.delete(a1.getId());
		try {
			a2 = appointmentService.get(a1.getId());
		} catch (NoSuchElementException e) {
			a2 = null;
		}
		assertThat(a2 == null).isTrue();

	}

}
