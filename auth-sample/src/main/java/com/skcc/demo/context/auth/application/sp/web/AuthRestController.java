package com.skcc.demo.context.auth.application.sp.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skcc.demo.context.auth.domain.authority.AuthorityService;
import com.skcc.demo.context.auth.domain.authority.account.model.Account;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
	@Autowired
	private AuthorityService authorityService;
	@PostMapping
	@ResponseBody
	public Map<String, Object> createAccount(@RequestBody Map<String,Object> params){
		System.out.println("save");
		Map<String, Object> resultMap = new HashMap<String,Object>();
		//authorityService.createAccount(params);
		
		return resultMap; 
	}
}
