package com.instinctools.clientaccountmanager.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.instinctools.clientaccountmanager.HibernateUtil;
import com.instinctools.clientaccountmanager.dao.BasicDao;
import com.instinctools.clientaccountmanager.model.Account;

public class AccountDao implements BasicDao<Account> {

	private Session session;

	@Override
	public Account get(Integer id) {
		session = HibernateUtil.getSessionFactory().openSession();
		Account account = (Account) session.load(Account.class, id);
		return account;
	}

	@Override
	public List<Account> getAllClients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account create(Account newAccount) {
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(newAccount);
		transaction.commit();
		return newAccount;
	}

	@Override
	public Account update(Account newAccount) {
		session.close();
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(newAccount);
		transaction.commit();
		return newAccount;
	}

	@Override
	public void delete(int id) {
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Account acc = session.load(Account.class, id);
		session.delete(acc);
		transaction.commit();
	}

}
