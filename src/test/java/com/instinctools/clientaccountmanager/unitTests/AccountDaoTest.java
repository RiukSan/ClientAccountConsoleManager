package com.instinctools.clientaccountmanager.unitTests;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.instinctools.clientaccountmanager.dao.impl.AccountDao;
import com.instinctools.clientaccountmanager.model.Account;

public class AccountDaoTest {
	@Mock
	private AccountDao accountDao = mock(AccountDao.class);

	private static final Logger LOGGER = LogManager.getLogger(AccountDao.class);

	@Test
	public void createNewAccountTest() {
		Account account = new Account("new_account");
		when(accountDao.create(account)).thenReturn(account);
		Account newAccount = accountDao.create(account);
		assertTrue("new_account".equals(newAccount.getName()));
	}

	@Test
	public void deleteAccountTest() {
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				LOGGER.info("called with arguments: " + Arrays.toString(args));
				return null;
			}
		}).when(accountDao).delete(anyInt());
		accountDao.delete(new Random().nextInt(Integer.MAX_VALUE));
	}

	@Test
	public void getAccountTest() {
		Account account1 = new Account("account1");
		account1.setId(1);
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(account1);
		when(accountDao.get(1)).thenReturn(accounts.get(0));
		Account account = accountDao.get(1);
		assertTrue("account1".equals(account.getName()));
	}
	
	@Test
	public void updateAccountTest() {
		Account account = new Account("account1");
		account.setId(1);
		account.setName("updated_name");
		when(accountDao.update(account)).thenReturn(account);
		Account updatedAccount = accountDao.update(account);
		assertTrue("updated_name".equals(updatedAccount.getName()));
	}

}
