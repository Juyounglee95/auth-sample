package com.skcc.demo.context.auth.domain.authority.company.model;

import javax.persistence.Entity;

import com.skcc.demo.context.base.domain.AbstractEntity;

import lombok.Data;

@Data
@Entity
public class Company extends AbstractEntity{
	private String name;
	
	public Company() {
		
	}
	public Company(String name) {
		this.name= name;
	}
}
