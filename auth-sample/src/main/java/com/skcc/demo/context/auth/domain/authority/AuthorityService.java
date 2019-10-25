package com.skcc.demo.context.auth.domain.authority;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.skcc.demo.context.auth.domain.authority.account.model.Account;

public interface AuthorityService extends UserDetailsService{

	Long joinUser(Account member);

}
