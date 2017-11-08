package com.instinctools.clientaccountmanager.services;

import java.util.List;

import com.instinctools.clientaccountmanager.model.Client;

public interface ClientService {

	public Client create(String name);

	public void delete(int id);

	public Client get(int id);

	public List<Client> getAllUsers();
}
