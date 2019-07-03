package com.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Cat implements Serializable{
	private Integer id;
	private String name;
	private String color;
	private String sex;
}
