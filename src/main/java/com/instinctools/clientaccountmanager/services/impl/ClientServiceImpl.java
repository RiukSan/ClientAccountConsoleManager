package com.instinctools.clientaccountmanager.services.impl;

import java.util.List;

import com.instinctools.clientaccountmanager.dao.BasicDao;
import com.instinctools.clientaccountmanager.dao.impl.ClientDao;
import com.instinctools.clientaccountmanager.model.Client;
import com.instinctools.clientaccountmanager.services.ClientService;

public class ClientServiceImpl implements ClientService {
	BasicDao<Client> clientDao = new ClientDao();

	public ClientServiceImpl() {
	}

	public Client create(String name) {
		return clientDao.create(name);
	}

	public void delete(int id) {
		clientDao.delete(id);
	}

	public Client get(int id) {
		return clientDao.read(id);
	}

	public List<Client> getAllUsers() {
		return clientDao.readAll();
	}

}
