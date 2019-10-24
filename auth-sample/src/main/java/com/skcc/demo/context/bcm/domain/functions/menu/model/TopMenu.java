package com.skcc.demo.context.bcm.domain.functions.menu.model;

import javax.persistence.Entity;

import com.skcc.demo.context.base.domain.AbstractEntity;

import lombok.Data;

@Data
@Entity
public class TopMenu extends AbstractEntity {
	private String name;
	private Boolean usage = true;
	
	public TopMenu() {
		
	}
	public TopMenu(String name) {
		this.name= name;
	}
}
