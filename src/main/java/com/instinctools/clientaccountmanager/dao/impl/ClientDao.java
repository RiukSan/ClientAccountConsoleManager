package com.instinctools.clientaccountmanager.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.instinctools.clientaccountmanager.HibernateUtil;
import com.instinctools.clientaccountmanager.dao.BasicDao;
import com.instinctools.clientaccountmanager.model.Client;

public class ClientDao implements BasicDao<Client> {
	
	private Session session;

	@Override
	public Client create(Client newClient) {
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(newClient);
		transaction.commit();
		return newClient;
	}

	@Override
	public Client get(Integer id) {
		session = HibernateUtil.getSessionFactory().openSession();
		Client client = (Client) session.load(Client.class, id);
		return client;
	}

	@Override
	public Client update(Client updatedClient) {
		session.close();
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(updatedClient);
		transaction.commit();
		return updatedClient;
	}

	@Override
	public void delete(int id) {
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Client client = session.load(Client.class, id);
		session.delete(client);
		transaction.commit();
	}

	@Override
	public List<Client> getAllClients() {
		session = HibernateUtil.getSessionFactory().openSession();
		Query<Client> query = session.createQuery("from Client", Client.class);
		List<Client> list = query.getResultList();
		return list;
	}

}
