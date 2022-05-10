package com.myclinik.service;

import com.myclinik.model.Appointment;
import com.myclinik.model.Client;

import java.util.List;

public interface IAppointmentService {
	List<Appointment> findAll();

	void save(Appointment appointment);

	Appointment get(Long appointmentId);

	void delete(Long appointmentId);

	void update(Long id, Appointment newAppointment);
	// Long findClient();
}
