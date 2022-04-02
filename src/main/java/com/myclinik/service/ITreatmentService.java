package com.myclinik.service;

import com.myclinik.model.Treatment;

import java.util.List;

public interface ITreatmentService {
	List<Treatment> findAll();
    void save(Treatment treatment);
    Treatment get(long id);
    void delete(long id);
}
