package com.viaflow.dao;

import java.util.List;

public interface DaoBase<T> {
	T insert(T object);

	T update(T object);

	T delete(int id);

	T findById(int id);

	List<T> findAll();

	T disable(int id);
}
