package com.instinctools.clientaccountmanager.dao;

import java.util.List;

public interface BasicDao<T> {
	
	T create(T object);

	T get(Integer id);
	
	T update(T object);

	void delete(int id);
	
	List<T> getAllClients();
}
