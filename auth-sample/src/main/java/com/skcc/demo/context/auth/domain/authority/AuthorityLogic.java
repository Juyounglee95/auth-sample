package com.skcc.demo.context.auth.domain.authority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.skcc.demo.context.auth.domain.authority.account.AccountRepository;
import com.skcc.demo.context.auth.domain.authority.account.model.Account;
import com.skcc.demo.context.auth.domain.authority.role.RoleRepository;
@Service
@Transactional
public class AuthorityLogic implements AuthorityService{
	@Autowired
	private AccountRepository accountRepository;
	
	private RoleRepository roleRepository;
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Account account = accountRepository.findByEmail(userEmail).orElseThrow(()->new UsernameNotFoundException(userEmail+"이 존재하지 않습니다."));
		List<String> roleDivList = getRoleDivision(account.getRoleIdList());
	        return new User(account.getEmail(), account.getPassword(),getAuthorities(roleDivList)); 
	        //new org.springframework.security.core.userdetails.User
	}
	
	private List<String> getRoleDivision(List<Long> roleIdList){
		List<String> roleDivList = new ArrayList<>();
		for(Long roleId: roleIdList) {
			roleDivList.add(roleRepository.findById(roleId).get().getRoleDivision().getValue());
		}
		return roleDivList;
		
	}

	private Collection<? extends GrantedAuthority> getAuthorities(List<String> roleDivList) {
		String[] userRoles = roleDivList.toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
	}

	@Override
	@Transactional
	public Long joinUser(Account account) {
			
		  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	      account.setPassword(passwordEncoder.encode(account.getPassword()));
	      

	        return accountRepository.save(account).getId();
	}
	
	

}
