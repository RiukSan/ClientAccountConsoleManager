package com.instinctools.clientaccountmanager.services.impl;

import com.instinctools.clientaccountmanager.dao.BasicDao;
import com.instinctools.clientaccountmanager.dao.impl.AccountDao;
import com.instinctools.clientaccountmanager.model.Account;
import com.instinctools.clientaccountmanager.services.AccountService;

public class AccountServiceImpl implements AccountService {
	BasicDao<Account> accountDao = new AccountDao();

	public AccountServiceImpl() {
	}

	public Account update(Integer id, String name) {
		return accountDao.update(id, name);
	}

	public Account create(Integer userId, String name) {
		return accountDao.create(userId, name);
	}

	public void delete(Integer id) {
		accountDao.delete(id);
	}

}
