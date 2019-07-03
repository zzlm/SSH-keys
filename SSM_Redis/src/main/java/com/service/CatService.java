package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.model.Cat;


public interface CatService {

	List<Cat> selectAll();
	void add(Cat cat);
	void delete(Integer id);
	void update(Cat cat);
	Cat selectById(Integer id);
}
