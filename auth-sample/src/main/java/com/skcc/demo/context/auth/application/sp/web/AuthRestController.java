package com.skcc.demo.context.auth.application.sp.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skcc.demo.context.auth.domain.authority.AuthorityService;
import com.skcc.demo.context.auth.domain.authority.account.AccountRepository;
import com.skcc.demo.context.auth.domain.authority.account.model.Account;
import com.skcc.demo.context.auth.domain.authority.role.RoleRepository;
import com.skcc.demo.context.auth.domain.authority.role.model.Role;

@RestController
public class AuthRestController {
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private RoleRepository roleRepository;
	/************사용자관리*************/
	@PostMapping("/admin/usermanage/auth/new")
	public ResponseEntity<?> createAccount(@RequestBody Account account){
		
		authorityService.createAccount(account);
		
		return new ResponseEntity<>("{}",HttpStatus.CREATED);
	}
	
	@PutMapping("/admin/usermanage/auth/edit/{id}")
	public ResponseEntity<?> editAccount(@PathVariable("id")Long id, @RequestBody Account account)
	{	
		if(account!= null) {
		authorityService.editAccount(id, account);
		authorityService.updateAccount(account);
		return new ResponseEntity<>("{}", HttpStatus.OK); 
		}
		return new ResponseEntity<>("{}",HttpStatus.BAD_REQUEST); 
	}
	
	@DeleteMapping("/admin/usermanage/auth/delete/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable("id")Long id){
		accountRepository.deleteById(id);
		return new ResponseEntity<>("{}", HttpStatus.OK); 
	}
	/************권한 관리***********/
	@PostMapping("/admin/permission/edit")
	@ResponseBody
	public ResponseEntity<?> editPermissions(@RequestBody Role role){
		authorityService.editPermission(role.getId(),role);
		return new ResponseEntity<>("{}", HttpStatus.OK); 
	}
	
	@PostMapping("/admin/permission/new/role")
	public ResponseEntity<?> createRole(@RequestBody Role role){
		
		authorityService.createRole(role);
		
		return new ResponseEntity<>("{}",HttpStatus.CREATED);
	}
	
	@PutMapping("/admin/permission/edit/role/{id}")
	public ResponseEntity<?> editRole(@PathVariable("id")Long id, @RequestBody Role role)
	{	
		if(role!= null) {
		authorityService.editRole(id, role);
		return new ResponseEntity<>("{}", HttpStatus.OK); 
		}
		return new ResponseEntity<>("{}",HttpStatus.BAD_REQUEST); 
	}
	
	@DeleteMapping("/admin/permission/delete/role/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable("id")Long id){
		authorityService.deleteRole(id);
		return new ResponseEntity<>("{}", HttpStatus.OK); 
	}
	

}
