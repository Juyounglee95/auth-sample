package com.skcc.demo.context.bcm.application.sp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skcc.demo.context.bcm.domain.functions.FunctionsService;
import com.skcc.demo.context.bcm.domain.functions.menu.model.SubMenu;
import com.skcc.demo.context.bcm.domain.functions.menu.model.TopMenu;


@RestController
public class BcmRestController {
	@Autowired
	FunctionsService functionsService;
	/****하위메뉴 post put delete****/
	@PostMapping("/admin/menu/new/sub")
	public ResponseEntity<?> createSubMenu(@RequestBody SubMenu submenu){
		
		functionsService.createSubMenu(submenu);
		
		return new ResponseEntity<>("{}",HttpStatus.CREATED);
	}
	
	@PutMapping("/admin/menu/edit/sub/{id}")
	public ResponseEntity<?> editSubMenu(@PathVariable("id")Long id, @RequestBody SubMenu submenu){
		if(submenu!=null) {
			functionsService.editSubMenu(id, submenu);
			return new ResponseEntity<>("{}", HttpStatus.OK); 
			
		}
		return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST); 
	}
	
	@DeleteMapping("/admin/menu/delete/sub/{id}")
	public ResponseEntity<?> deleteSubMenu(@PathVariable("id")Long id){
		functionsService.deleteSubMenu(id);
		return new ResponseEntity<>("{}", HttpStatus.OK); 
		
	}
	
	/****상위메뉴 post put delete****/
	@PostMapping("/admin/menu/new/top")
	public ResponseEntity<?> createTopMenu(@RequestBody TopMenu topmenu){
		
		functionsService.createTopMenu(topmenu);
		
		return new ResponseEntity<>("{}",HttpStatus.CREATED);
	}
	
	@PutMapping("/admin/menu/edit/top/{id}")
	public ResponseEntity<?> editTopMenu(@PathVariable("id")Long id, @RequestBody TopMenu topmenu){
		if(topmenu!=null) {
			functionsService.editTopMenu(id, topmenu);
			return new ResponseEntity<>("{}", HttpStatus.OK); 
			
		}
		return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST); 
	}
	
	@DeleteMapping("/admin/menu/delete/top/{id}")
	public ResponseEntity<?> deleteTopMenu(@PathVariable("id")Long id){
		functionsService.deleteTopMenu(id);
		return new ResponseEntity<>("{}", HttpStatus.OK); 
		
	}
}
