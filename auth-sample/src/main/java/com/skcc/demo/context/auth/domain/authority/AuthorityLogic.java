package com.skcc.demo.context.auth.domain.authority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.skcc.demo.context.auth.domain.authority.role.model.Role;
import com.skcc.demo.context.auth.domain.authority.role.model.RoleDivision;
@Service
@Transactional
public class AuthorityLogic implements AuthorityService{
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		System.out.println(userEmail);
		Account account = accountRepository.findByEmail(userEmail).orElseThrow(()->new UsernameNotFoundException(userEmail+"이 존재하지 않습니다."));
		System.out.println("account: "+account.getName());
		//System.out.println("ROLE: "+roleRepository.findById(account.getRoleId()).get().getRoleDivision().getValue());
		System.out.println(account.getRoleId());
		String roleName = roleRepository.findById(account.getRoleId()).get().getRoleDivision().getValue();
		System.out.println("ROLE: "+roleName);
		
	        return new User(account.getEmail(), account.getPassword(),getAuthorities("ROLE_"+roleName)); 
	        //new org.springframework.security.core.userdetails.User
	}
	
	private String getRoleName(Long roleId){
		String roleName = roleRepository.findById(roleId).get().getRoleDivision().getValue();
		return "ROLE_"+roleName;
		
	}

	private Collection<? extends GrantedAuthority> getAuthorities(String roleName) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		 authorities.add(new SimpleGrantedAuthority(roleName));
        return authorities;
	}

	@Override
	@Transactional
	public Long joinUser(Account account) {
			
		  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	      account.setPassword(passwordEncoder.encode(account.getPassword()));
	      account.setRoleId((long)1);
	      account.setRoleName(roleRepository.findById((long)1).get().getName());
	        return accountRepository.save(account).getId();
	}

	@Override
	public Page<Account> findAllUsers(Pageable pageable) {
		pageable = PageRequest.of(pageable.getPageNumber()<=0?0:pageable.getPageNumber()-1,
				pageable.getPageSize());
		return accountRepository.findAll(pageable);
	}

	@Override
	public List<Role> getRoles(RoleDivision roleDivision) {
		
		return roleRepository.findAllByRoleDivision(roleDivision);
	}

	@Override
	public void createAccount(Account account) {
		Role role = roleRepository.findByName(account.getRoleName());
		account.setRoleId(role.getId());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    account.setPassword(passwordEncoder.encode(account.getPassword()));
	    accountRepository.save(account);		
	}
	
	

}
