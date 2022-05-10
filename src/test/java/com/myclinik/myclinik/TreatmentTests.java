package com.myclinik.myclinik;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.myclinik.model.Treatment;
import com.myclinik.model.Appointment;
import com.myclinik.service.ITreatmentService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.NoSuchElementException;

@SpringBootTest
public class TreatmentTests {

	@Autowired
	private ITreatmentService treatmentService;

	@Test
	@Transactional
	final void testTreatment() {
		Treatment t1 = new Treatment();
		t1.setName("Depilación Piernas");
		t1.setSubservice("Ambas");
		t1.setPrice(20.22f);
		t1.setDuration(50);
		t1.setConsents("Firmados ya");
		List<Appointment> appointments = new ArrayList<Appointment>();
		t1.setAppointments(appointments);

		treatmentService.save(t1);
		Treatment t2 = treatmentService.get(t1.getId());
		assertThat(t2.equals(t1)).isTrue();

		t1.setName("Depilación Cara");
		treatmentService.save(t1);
		t2 = treatmentService.get(t1.getId());
		assertThat(t2.getName().equals("Depilación Piernas")).isFalse();

		treatmentService.delete(t1.getId());
		try {
			t2 = treatmentService.get(t1.getId());
		} catch (NoSuchElementException e) {
			t2 = null;
		}
		assertThat(t2 == null).isTrue();
	}

}
