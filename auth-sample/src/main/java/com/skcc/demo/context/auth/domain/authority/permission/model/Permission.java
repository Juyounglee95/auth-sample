package com.skcc.demo.context.auth.domain.authority.permission.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

import com.skcc.demo.context.base.domain.AbstractEntity;
import com.skcc.demo.context.base.domain.AggregateRoot;

import lombok.Data;

@Data
@Entity
public class Permission extends AbstractEntity implements AggregateRoot{
	private String name;
	
	@Enumerated
	private PerLevel perLevel;
	
	private Long resourceId;
	private Boolean usage=true;
	
	public Permission() {
		
	}
	public Permission(String name, PerLevel perLevel, Long resourceId) {
		this.name= name;
		this.perLevel = perLevel;
		this.resourceId = resourceId;
	}
}
