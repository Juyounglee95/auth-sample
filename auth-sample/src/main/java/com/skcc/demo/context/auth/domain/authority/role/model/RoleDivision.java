package com.skcc.demo.context.auth.domain.authority.role.model;

public enum RoleDivision {
	
	SYS_ADMIN("SYS_ADMIN"), //시스템관리
	PARTNER_COMPANY("PARTNER_COMPANY"), //협력
	MEMBER_COMPANY("MEMBER_COMPANY"), //회원
	COUNSELOR("COUNSELOR"), //상담,
	MANAGER("MANAGER"); //관리
	
	private String value;
	
	RoleDivision(String value){
		this.value = value;
		
	}
	public String getValue() {
		return this.value;
	}
}
