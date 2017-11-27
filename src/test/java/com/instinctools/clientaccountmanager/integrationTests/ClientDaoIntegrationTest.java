package com.instinctools.clientaccountmanager.integrationTests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import com.instinctools.clientaccountmanager.dao.impl.ClientDao;
import com.instinctools.clientaccountmanager.model.Client;

public class ClientDaoIntegrationTest {
	private ClientDao clientDao = new ClientDao();
	
	@Test
	public void createNewClientTest() {
		Client client = new Client("new_client");
		clientDao.create(client);
		Integer clientId = client.getId();
		Client newClient = clientDao.get(clientId);
		assertTrue(client.getName().equals(newClient.getName()));
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void deleteClientTest() {
		Client client = new Client("new_client");
		clientDao.create(client);
		Integer clientId = client.getId();
		clientDao.delete(clientId);
		Client deletedClient = clientDao.get(clientId);
		deletedClient.getId();
	}
	
	@Test
	public void updateClientTest() {
		Client client = new Client("new_client");
		clientDao.create(client);
		Integer clientId = client.getId();
		client.setName("updated_client");
		clientDao.update(client);
		Client updatedClient = clientDao.get(clientId);
		assertTrue(updatedClient.getName().equals(client.getName()));
	}
	
	@Test
	public void getAllClientsTest() {
		Client client = new Client("new_client");
		clientDao.create(client);
		List<Client> allClients = clientDao.getAllClients();
		assertTrue(allClients.contains(client));
	}
}
