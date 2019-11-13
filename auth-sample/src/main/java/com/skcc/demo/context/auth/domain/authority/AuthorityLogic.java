package com.skcc.demo.context.auth.domain.authority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
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
import com.skcc.demo.context.auth.domain.authority.permission.PermissionRepository;
import com.skcc.demo.context.auth.domain.authority.permission.model.PerLevel;
import com.skcc.demo.context.auth.domain.authority.permission.model.Permission;
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
	
	@Autowired
	private PermissionRepository permissionRepository;

	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		
		Account account = accountRepository.findByEmail(userEmail).orElseThrow(()->new UsernameNotFoundException(userEmail+"이 존재하지 않습니다."));
		
		String roleName = roleRepository.findById(account.getRoleId()).get().getRoleDivision().getValue(); //RoleDivision에 따라서 접근할 수 있는 페이지 제한
		
		
	        return new User(account.getEmail(), account.getPassword(),getAuthorities("ROLE_"+roleName)); //반드시 "ROLE_"로 시작해야한다
	        //new org.springframework.security.core.userdetails.User
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
	      account.setPassword(passwordEncoder.encode(account.getPassword())); //PasswordEncoder를 사용하지 않고 가입하는 경우 Password형식 오류로 로그인 불가
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

	@Override
	public void editAccount(Long id, Account account) {
		Account persistAccount = accountRepository.findById(id).get();
		Role role = roleRepository.findByName(account.getRoleName());
		account.setRoleId(role.getId());
		if(account.getPassword().equals(persistAccount.getPassword())) {
			BeanUtils.copyProperties(account, persistAccount, "id", "password"); 
			
			
		}else {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			BeanUtils.copyProperties(account, persistAccount, "id");
			
		}
		accountRepository.save(persistAccount);
	}

	@Override
	public List<Permission> getPermissions() {
		return permissionRepository.findAll();
	}

	@Override
	public void editPermission(Long id, Role role) {
		Role persistRole = roleRepository.findById(id).get();
		BeanUtils.copyProperties(role,persistRole, "name","roleUsage", "roleDivision");
		roleRepository.save(persistRole);
		
	}

	@Override
	public void createPermission(Long menuId, String menuName) {
		Permission adPermission = new Permission("관리권한",PerLevel.ADMIN, menuId);

		Permission viPermission = new Permission("읽기권한",PerLevel.VIEW, menuId);

		Permission edPermission = new Permission("쓰기권한",PerLevel.EDIT, menuId);
		permissionRepository.save(adPermission);
		permissionRepository.save(viPermission);
		permissionRepository.save(edPermission);
		
	}

	@Override
	public void deletePers(Long id) {
		List<Permission> perList=permissionRepository.findByResourceId(id);
		List<Role> roleList = roleRepository.findAll();
		/*****ADMIN VIEW EDIT permission****/
		Long per1 = perList.get(0).getId();
		Long per2 = perList.get(1).getId();
		Long per3 = perList.get(2).getId();
		
		for(Role role : roleList) {
			if(role.getPerIdList().contains(per1)) {
				int idx = role.getPerIdList().indexOf(per1);
				role.getPerIdList().remove(idx);
				roleRepository.save(role);
			}
			if(role.getPerIdList().contains(per2)) {
				int idx = role.getPerIdList().indexOf(per2);
				role.getPerIdList().remove(idx);

				roleRepository.save(role);
			}
			if(role.getPerIdList().contains(per3)) {
				int idx = role.getPerIdList().indexOf(per3);
				role.getPerIdList().remove(idx);

				roleRepository.save(role);
			}
		}
		permissionRepository.deleteById(per1);

		permissionRepository.deleteById(per2);
		permissionRepository.deleteById(per3);
	}

	@Override
	public void createRole(Role role) {
		roleRepository.save(role);
		
	}

	@Override
	public void editRole(Long id, Role role) {
		//사용여부 x로 변경된 경우, 해당 역할을 가진 사용자 role null로 수정
		List<Account> hasRoleUser = accountRepository.findByRoleId(id);
		for(Account account:hasRoleUser) {
			account.setRoleId(null);
			account.setRoleName(null);
			accountRepository.save(account);
		}
		Role persistRole = roleRepository.findById(id).get();
		BeanUtils.copyProperties(role,persistRole, "id");
		roleRepository.save(persistRole);
		
		
	}

	@Override
	public void deleteRole(Long id) {
		//해당 역할 가진 사용자 role null로 수정
		List<Account> hasRoleUser = accountRepository.findByRoleId(id);
		for(Account account:hasRoleUser) {
			account.setRoleId(null);
			account.setRoleName(null);
			accountRepository.save(account);
		}
		roleRepository.deleteById(id);
	}

	@Override
	public boolean checkRoles(String roleName) {
		Boolean roleusage = roleRepository.findByName(roleName).getRoleUsage(); 
		return roleusage;
	}
	
	

}
