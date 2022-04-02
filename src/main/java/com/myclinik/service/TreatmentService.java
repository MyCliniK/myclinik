package com.myclinik.service;

import com.myclinik.model.Treatment;
import com.myclinik.repository.TreatmentRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreatmentService implements ITreatmentService {
	@Autowired
	private TreatmentRepository repository;

	@Override
	public List<Treatment> findAll() {
		var treatments = (List<Treatment>) repository.findAll();
		return treatments;
	}

	public void save(Treatment treatment) {
		repository.save(treatment);
	}

	public Treatment get(long id) {
		return repository.findById(id).get();
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
}
