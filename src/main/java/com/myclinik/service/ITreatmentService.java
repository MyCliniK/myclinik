package com.myclinik.service;

import com.myclinik.model.Treatment;

import java.util.List;

public interface ITreatmentService {
	List<Treatment> findAll();
    void save(Treatment treatment);
    Treatment get(Long id);
    void delete(Long id);
	void update(Long id, Treatment treatment);
}
