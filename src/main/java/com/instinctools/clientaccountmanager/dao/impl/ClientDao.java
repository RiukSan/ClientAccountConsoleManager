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
	public Client create(Integer userId, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client create(String name) {
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Client client = new Client();
		client.setName(name);
		session.save(client);
		transaction.commit();
		return client;
	}

	@Override
	public Client read(Integer id) {
		session = HibernateUtil.getSessionFactory().openSession();
		Client client = (Client) session.load(Client.class, id);
		return client;
	}

	@Override
	public Client update(Integer id, String name) {
		// TODO Auto-generated method stub
		return null;
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
	public List<Client> readAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		Query<Client> query = session.createQuery("from Client", Client.class);
		List<Client> list = query.getResultList();
		return list;
	}

}
