package com.myclinik.service;

import com.myclinik.model.Appointment;
import com.myclinik.repository.AppointmentRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService implements IAppointmentService {
	@Autowired
	private AppointmentRepository repository;

	@Override
	public List<Appointment> findAll() {
		var appointments = (List<Appointment>) repository.findAll();
		return appointments;
	}

    public void save(Appointment appointment) {
        repository.save(appointment);
    }

    public Appointment get(Long appointmentId) {
        return repository.findById(appointmentId).get();
    }

    public void delete(Long appointmentId) {
        repository.deleteById(appointmentId);
    }

	public void update(Long id, Appointment newAppointment) {
		newAppointment.setId(id);
		repository.save(newAppointment);
	}

	/*public Client findClient(Long client){
		
	}*/
}
