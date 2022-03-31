package com.myclinik.service;

import com.myclinik.model.Appointment;

import java.util.List;

public interface IAppointmentService {
	List<Appointment> findAll();
}
