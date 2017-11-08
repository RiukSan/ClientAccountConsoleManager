package com.instinctools.clientaccountmanager.dao;

import java.util.List;

public interface BasicDao<T> {
	
	public T create(Integer userId, String name);
	
	public T create(String name);

	public T read(Integer id);
	
	public T update(Integer id, String name);

	public void delete(int id);
	
	public List<T> readAll();
}
