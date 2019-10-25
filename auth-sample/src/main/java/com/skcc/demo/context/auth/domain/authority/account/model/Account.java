package com.skcc.demo.context.auth.domain.authority.account.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.skcc.demo.context.base.domain.AbstractEntity;
import com.skcc.demo.context.base.domain.AggregateRoot;

import lombok.Data;

@Data
@Entity
public class Account extends AbstractEntity implements AggregateRoot{
	@NotNull
	private String password;
    @NotNull
	private String name;
    @NotNull
    @Email
	private String email;
	private Long companyId;
	private String companyName;
	private Boolean memberUsage = true; 
	
	@ElementCollection
	private List<Long> roleIdList= new ArrayList<Long>();
	
	public Account() {
			
		}
	public Account(String name, String password, String email, Long companyId, Boolean memberUsage) {
			this.name= name;
			this.password= password;
			this.email= email;
			this.companyId= companyId;
			this.memberUsage = memberUsage;
		}
}
