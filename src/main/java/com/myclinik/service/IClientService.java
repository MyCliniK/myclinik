package com.myclinik.service;

import com.myclinik.model.Client;

import java.util.List;

public interface IClientService {
	List<Client> findAll();
}
