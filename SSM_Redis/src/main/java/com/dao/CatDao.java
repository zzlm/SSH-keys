package com.dao;

import java.util.List;

import com.model.Cat;

public interface CatDao {

	List<Cat> selectAll();
	void add(Cat cat);
	void delete(Integer id);
	void update(Cat cat);
	Cat selectById(Integer id);
}
