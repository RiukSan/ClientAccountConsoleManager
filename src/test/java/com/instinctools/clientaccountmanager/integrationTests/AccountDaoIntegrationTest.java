package com.instinctools.clientaccountmanager.integrationTests;

import static org.junit.Assert.assertTrue;

import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import com.instinctools.clientaccountmanager.dao.impl.AccountDao;
import com.instinctools.clientaccountmanager.model.Account;

public class AccountDaoIntegrationTest {
	private AccountDao accountDao = new AccountDao();
	
	@Test
	public void createNewAccountTest() {
		Account account = new Account("new_account");
		accountDao.create(account);
		Integer accountId = account.getId();
		Account newAccount = accountDao.get(accountId);
		assertTrue(account.getName().equals(newAccount.getName()));
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void deleteAccountTest() {
		Account account = new Account("new_account");
		accountDao.create(account);
		Integer accountId = account.getId();
		accountDao.delete(accountId);
		Account deletedAccount = accountDao.get(accountId);
		deletedAccount.getId();
	}
	
	@Test
	public void updateAccountTest() {
		Account account = new Account("new_account");
		accountDao.create(account);
		Integer accountId = account.getId();
		account.setName("updated_account");
		accountDao.update(account);
		Account updatedAccount = accountDao.get(accountId);
		assertTrue(updatedAccount.getName().equals(account.getName()));
	}
}
