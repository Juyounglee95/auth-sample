package com.skcc.demo.context.bcm.application.sp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skcc.demo.context.bcm.domain.functions.FunctionsService;
import com.skcc.demo.context.bcm.domain.functions.menu.SubMenuRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class BcmController {
	@Autowired
	FunctionsService functionsService;
	
	@Autowired
	SubMenuRepository subMenuRepository;
	  @GetMapping("/admin/menu")
	    public String manageMenu(@RequestParam(value="id", defaultValue = "0")Long id, Model model) {
	    	
	    	model.addAttribute("topMenuList", functionsService.getTopMenuList());
	    	if(id!=0) {
	    		model.addAttribute("subMenuList", subMenuRepository.findByTopMenuId(id) );
	    	}else {
	    		model.addAttribute("subMenuList", functionsService.getSubMenuList());
	    	}
	    	return "/menu/list";
	    }
}
