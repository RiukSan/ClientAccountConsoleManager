package com.instinctools.clientaccountmanager.services;

import com.instinctools.clientaccountmanager.model.Account;

public interface AccountService {
	public Account update(Integer id, String name);

	public Account create(Integer userId, String name);

	public void delete(Integer id);
}
