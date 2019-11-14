package com.skcc.demo.context.auth.domain.authority;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.skcc.demo.context.auth.domain.authority.account.model.Account;
import com.skcc.demo.context.auth.domain.authority.permission.model.Permission;
import com.skcc.demo.context.auth.domain.authority.role.model.Role;
import com.skcc.demo.context.auth.domain.authority.role.model.RoleDivision;

public interface AuthorityService extends UserDetailsService{

	Long joinUser(Account member);

	Page<Account> findAllUsers(Pageable pageable);

	List<Role> getRoles(RoleDivision roleDivision);

	void createAccount(Account account);

	void editAccount(Long id, Account account);

	List<Permission> getPermissions();

	void editPermission(Long id, Role role);

	void createPermission(Long id, String name);

	void deletePers(Long id);

	void createRole(Role role);

	void editRole(Long id, Role role);

	void deleteRole(Long id);

	boolean checkRoles(String roleName);
	
	void updateAccount(Account account);
	
	//User getUserInfo(); @Secured Annotation Test
}
