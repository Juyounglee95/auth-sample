package com.skcc.demo.context.auth.domain.authority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import com.skcc.demo.context.auth.domain.authority.members.model.Account;
import com.skcc.demo.context.auth.domain.authority.role.RoleRepository;
@Service
@Transactional
public class AuthorityLogic implements AuthorityService{
	@Autowired
	private AccountRepository memberRepository;
	
	private RoleRepository roleRepository;
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Account account = memberRepository.findByEmail(userEmail);
		List<Long> roleIdList = account.getRoleIdList();
		for(Long roleId : roleIdList) {
			
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		
		 if (("admin@example.com").equals(userEmail)) {
	            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
	        } else {
	            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
	        }

	        return new User(account.getEmail(), account.getPassword(),getAuthorities(account));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Account account) {
		String[] userRoles = account.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
	}

	@Override
	@Transactional
	public Long joinUser(Account member) {
		  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	      member.setPassword(passwordEncoder.encode(member.getPassword()));

	        return memberRepository.save(member).getId();
	}
	
	

}
