package com.instinctools.clientaccountmanager.services;

import java.util.List;

import com.instinctools.clientaccountmanager.model.Account;
import com.instinctools.clientaccountmanager.model.Client;

public interface ClientService {

	Client create(String name);

	void delete(int id);

	Client get(int id);

	List<Client> getAllUsers();

	void deleteAccount(Integer id);

	Account updateAccount(Integer id, String name);

	Account createAccount(Integer id, String name);
}
