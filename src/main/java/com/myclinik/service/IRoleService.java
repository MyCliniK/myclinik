package com.myclinik.service;

import com.myclinik.model.Role;

import java.util.List;

public interface IRoleService {
	List<Role> findAll();

	Role get(Long id);

	void save(Role role);

	void delete(Long id);

	void update(Long id, Role role);
}
