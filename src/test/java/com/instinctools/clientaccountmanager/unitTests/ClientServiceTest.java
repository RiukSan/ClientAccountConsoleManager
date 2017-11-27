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

import com.instinctools.clientaccountmanager.model.Account;
import com.instinctools.clientaccountmanager.model.Client;
import com.instinctools.clientaccountmanager.services.ClientService;

public class ClientServiceTest {
	@Mock
	private ClientService clientService = mock(ClientService.class);

	private static final Logger LOGGER = LogManager.getLogger(ClientService.class);

	@Test
	public void getAllClientsTest() {
		Client client1 = new Client("client1");
		client1.setId(1);
		Client client2 = new Client("client2");
		client1.setId(2);
		Client client3 = new Client("client3");
		client1.setId(3);
		List<Client> allUsers = new ArrayList<Client>();
		allUsers.add(client1);
		allUsers.add(client2);
		allUsers.add(client3);
		when(clientService.getAllUsers()).thenReturn(allUsers);
		assertTrue(clientService.getAllUsers().contains(client1) && clientService.getAllUsers().contains(client2)
				&& clientService.getAllUsers().contains(client3));
	}

	@Test
	public void createNewClientTest() {
		when(clientService.create("new_client")).thenReturn(new Client("new_client"));
		Client newClient = clientService.create("new_client");
		assertTrue("new_client".equals(newClient.getName()));
	}

	@Test
	public void deleteClientTest() {
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				LOGGER.info("called with arguments: " + Arrays.toString(args));
				return null;
			}
		}).when(clientService).delete(anyInt());
		clientService.delete(new Random().nextInt(Integer.MAX_VALUE));
	}

	@Test
	public void getClientTest() {
		Client client1 = new Client("client1");
		client1.setId(1);
		List<Client> allUsers = new ArrayList<Client>();
		allUsers.add(client1);
		when(clientService.get(1)).thenReturn(allUsers.get(0));
		Client client = clientService.get(1);
		assertTrue("client1".equals(client.getName()));
	}

	@Test
	public void createNewAccountTest() {
		when(clientService.createAccount(1, "new_account")).thenReturn(new Account("new_account"));
		Account newAccount = clientService.createAccount(1, "new_account");
		assertTrue("new_account".equals(newAccount.getName()));
	}

	@Test
	public void updateAccountTest() {
		when(clientService.updateAccount(1, "updated_account")).thenReturn(new Account("updated_account"));
		Account newAccount = clientService.updateAccount(1, "updated_account");
		assertTrue("updated_account".equals(newAccount.getName()));
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
		}).when(clientService).deleteAccount(anyInt());
		clientService.deleteAccount(new Random().nextInt(Integer.MAX_VALUE));
	}

}
