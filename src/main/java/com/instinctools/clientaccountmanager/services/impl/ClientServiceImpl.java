package com.instinctools.clientaccountmanager.services.impl;

import java.util.List;

import com.instinctools.clientaccountmanager.dao.BasicDao;
import com.instinctools.clientaccountmanager.dao.impl.AccountDao;
import com.instinctools.clientaccountmanager.dao.impl.ClientDao;
import com.instinctools.clientaccountmanager.model.Account;
import com.instinctools.clientaccountmanager.model.Client;
import com.instinctools.clientaccountmanager.services.ClientService;

public class ClientServiceImpl implements ClientService {
	BasicDao<Client> clientDao = new ClientDao();
	BasicDao<Account> accountDao = new AccountDao();

	public ClientServiceImpl() {
	}

	public Client create(String name) {
		return clientDao.create(new Client(name));
	}

	public void delete(int id) {
		clientDao.delete(id);
	}
	
	public Account updateAccount(Integer id, String name) {
		Account updatedAccount = accountDao.get(id);
		updatedAccount.setName(name);
		return accountDao.update(updatedAccount);
	}

	public Account createAccount(Integer userId, String name) {
		Account newAccount = new Account(name);
		Client clientToChange = clientDao.get(userId);
		clientToChange.addAccount(newAccount);
		clientDao.update(clientToChange);
		return newAccount;
	}
	
	public void deleteAccount(Integer id) {
		accountDao.delete(id);
	}
	
	public Client get(int id) {
		return clientDao.get(id);
	}

	public List<Client> getAllUsers() {
		return clientDao.readAll();
	}

}
