package com.skcc.demo.context.bcm.domain.functions.menu.model;

import javax.persistence.Entity;

import com.skcc.demo.context.base.domain.AbstractEntity;
import com.skcc.demo.context.base.domain.AggregateRoot;

import lombok.Data;

@Data
@Entity
public class SubMenu extends AbstractEntity implements AggregateRoot{
	private String name;
	private Long topMenuId;
	private Boolean subMenuUsage = true;
	
	public SubMenu() {
		
	}
	public SubMenu(String name, Long topMenuId) {
		this.name = name;
		this.topMenuId = topMenuId;
	}
}
