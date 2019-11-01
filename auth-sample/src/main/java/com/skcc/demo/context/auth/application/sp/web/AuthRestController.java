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

@RestController
@RequestMapping("/admin/usermanage/auth")
public class AuthRestController {
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private AccountRepository accountRepository;
	
	@PostMapping("/new")
	public ResponseEntity<?> createAccount(@RequestBody Account account){
		
		authorityService.createAccount(account);
		
		return new ResponseEntity<>("{}",HttpStatus.CREATED);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<?> editAccount(@PathVariable("id")Long id, @RequestBody Account account)
	{	
		if(account!= null) {
		authorityService.editAccount(id, account);
		return new ResponseEntity<>("{}", HttpStatus.OK); 
		}
		return new ResponseEntity<>("{}",HttpStatus.BAD_REQUEST); 
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable("id")Long id){
		accountRepository.deleteById(id);
		return new ResponseEntity<>("{}", HttpStatus.OK); 
	}
}
