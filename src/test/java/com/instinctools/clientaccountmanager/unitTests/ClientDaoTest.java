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

import com.instinctools.clientaccountmanager.dao.impl.ClientDao;
import com.instinctools.clientaccountmanager.model.Client;

public class ClientDaoTest {
	@Mock
	private ClientDao clientDao = mock(ClientDao.class);

	private static final Logger LOGGER = LogManager.getLogger(ClientDao.class);

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
		when(clientDao.getAllClients()).thenReturn(allUsers);
		assertTrue(clientDao.getAllClients().contains(client1) && clientDao.getAllClients().contains(client2)
				&& clientDao.getAllClients().contains(client3));
	}

	@Test
	public void createNewClientTest() {
		Client client = new Client("new_client");
		when(clientDao.create(client)).thenReturn(client);
		Client newClient = clientDao.create(client);
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
		}).when(clientDao).delete(anyInt());
		clientDao.delete(new Random().nextInt(Integer.MAX_VALUE));
	}

	@Test
	public void getClientTest() {
		Client client1 = new Client("client1");
		client1.setId(1);
		List<Client> allUsers = new ArrayList<Client>();
		allUsers.add(client1);
		when(clientDao.get(1)).thenReturn(allUsers.get(0));
		Client client = clientDao.get(1);
		assertTrue("client1".equals(client.getName()));
	}
	
	@Test
	public void updateClientTest() {
		Client client = new Client("client1");
		client.setId(1);
		client.setName("updated_name");
		when(clientDao.update(client)).thenReturn(client);
		Client updatedClient = clientDao.update(client);
		assertTrue("updated_name".equals(updatedClient.getName()));
	}

}
