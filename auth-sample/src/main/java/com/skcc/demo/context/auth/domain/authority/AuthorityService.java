package com.skcc.demo.context.auth.domain.authority;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.skcc.demo.context.auth.domain.authority.account.model.Account;

public interface AuthorityService extends UserDetailsService{

	Long joinUser(Account member);

	Page<Account> findAllUsers(Pageable pageable);

}
