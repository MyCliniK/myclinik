package com.myclinik.service;

import com.myclinik.model.Role;
import com.myclinik.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {
	@Autowired
	private RoleRepository repository;

	@Override
	public List<Role> findAll() {
		var roles = (List<Role>) repository.findAll();
		return roles;
	}
	public Role get(Long id) {
		var role = (Role) repository.findById(id).get();
		return role;
	}
	public Role create(){
		Role role = new Role();
		return role;
	}
	public void save(Role role){
		repository.save(role);
	}
	public void delete(Long id){
		repository.deleteById(id);
	}
	public void update(Long id, Role newRole){
		newRole.setId(id);
		repository.save(newRole);
	}
}
