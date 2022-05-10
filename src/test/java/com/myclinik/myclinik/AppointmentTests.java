package com.myclinik.myclinik;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.myclinik.model.Client;
import com.myclinik.model.Treatment;
import com.myclinik.model.Appointment;
import com.myclinik.service.IClientService;
import com.myclinik.service.ITreatmentService;
import com.myclinik.service.TreatmentService;
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
	private ITreatmentService TreatmentService;
	@Autowired
	private IAppointmentService AppointmentService;

	@Test
	@Transactional
	final void testAppointment() {
		String strDate = "2022-12-12";
		// LocalDateTime date = LocalDateTime.parse(strDate);
		Appointment a1 = new Appointment();
		Client c1 = new Client();
		Treatment t1 = new Treatment();
		a1.setDone(false);
		a1.setPaid(false);
		a1.setClient(c1);
		a1.setTreatment(t1);

		AppointmentService.save(a1);
		Appointment a2 = AppointmentService.get(a1.getId());
		assertThat(a2.equals(a1)).isTrue();

		// AppointmentService.delete(a1.getId());
		AppointmentService.delete(a2.getId());
		try {
			a2 = AppointmentService.get(a1.getId());
		} catch (NoSuchElementException e) {
			a2 = null;
		}
		assertThat(a2 == null).isTrue();

	}

}
