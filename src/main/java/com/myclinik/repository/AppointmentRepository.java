package com.myclinik.repository;

import java.util.List;

import com.myclinik.model.Appointment;
import com.myclinik.model.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

}

