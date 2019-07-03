package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CatDao;
import com.model.Cat;
import com.service.CatService;

@Service
public class CatServiceImpl implements CatService{

	@Autowired
	CatDao dao;
	public List<Cat> selectAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	public void add(Cat cat) {
		dao.add(cat);
		
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public void update(Cat cat) {
		dao.update(cat);
		
	}

	public Cat selectById(Integer id) {
		return dao.selectById(id);
	}

}
