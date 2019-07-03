package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.CatDao;
import com.model.Cat;

@Controller
public class CatController {

	@Autowired
	CatDao dao;
	@RequestMapping("catShow.do")
	public String catShow(HttpServletRequest req){

		List<Cat> selectAll = dao.selectAll();
		req.setAttribute("cat", selectAll);
		return "catShow";
	}
	
	@RequestMapping("deleteCat.do")
	public String deleteCat(Integer id,HttpServletRequest req){
		dao.delete(id);

		return "redirect:/catShow.do";
	}
	
	@RequestMapping("updateCatShow.do")
	public String updateCatShow(Integer id,HttpServletRequest req){
		
		req.setAttribute("id", id);
		return "updateCat";
	}
	
	@RequestMapping("updateCat.do")
	public String updateCat(Integer id,String name,String color,String sex,HttpServletRequest req){
		Cat cat = dao.selectById(id);
		cat.setName(name);
		cat.setColor(color);
		cat.setSex(sex);

		dao.update(cat);
		return "redirect:/catShow.do";
	}
	
	@RequestMapping("addCat.do")
	public String insertCat(String name,String color,String sex,HttpServletRequest req){
		Cat cat = new Cat();
		cat.setId(0);
		cat.setName(name);
		cat.setColor(color);
		cat.setSex(sex);

		dao.add(cat);
		return "redirect:/catShow.do";
	}
}
