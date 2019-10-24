package com.skcc.demo.context.auth.domain.authority.user.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import com.skcc.demo.context.base.domain.AbstractEntity;
import com.skcc.demo.context.base.domain.AggregateRoot;

import lombok.Data;

@Data
@Entity
public class User extends AbstractEntity implements AggregateRoot{
	
	private String password;
	private String name;
	private String email;
	private Long companyId;
	private String companyName;
	private Boolean userUsage = true; 
	
	@ElementCollection
	private List<Long> roleIdList= new ArrayList<Long>();
	
	public User() {
			
		}
	public User(String name, String password, String email, String phoneNum, Long companyId, Boolean userUsage) {
			this.name= name;
			this.password= password;
			this.email= email;
			this.companyId= companyId;
			this.userUsage = userUsage;
		}
}
