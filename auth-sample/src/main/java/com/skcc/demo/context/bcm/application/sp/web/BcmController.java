package com.skcc.demo.context.bcm.application.sp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.skcc.demo.context.auth.domain.authority.account.model.Account;
import com.skcc.demo.context.bcm.domain.functions.FunctionsService;
import com.skcc.demo.context.bcm.domain.functions.menu.SubMenuRepository;
import com.skcc.demo.context.bcm.domain.functions.menu.TopMenuRepository;
import com.skcc.demo.context.bcm.domain.functions.menu.model.SubMenu;
import com.skcc.demo.context.bcm.domain.functions.menu.model.TopMenu;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class BcmController {
	@Autowired
	FunctionsService functionsService;

	@Autowired
	SubMenuRepository subMenuRepository;
	
	@Autowired
	TopMenuRepository topMenuRepository;
	
	@GetMapping("/admin/menu")
	public String manageMenu(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {

		model.addAttribute("topMenuList", functionsService.getTopMenuList());
		if (id != 0) {
			model.addAttribute("topMenuById", topMenuRepository.findById(id).get());
			model.addAttribute("subMenuList", subMenuRepository.findByTopMenuId(id));
		} 
		return "/menu/list";
	}
	
	@GetMapping("/admin/menu/create/{type}")
	public String createMenu(@PathVariable(value = "type")String type, @RequestParam(value = "id",defaultValue = "0")Long id,Model model) {
		if(type.equals("sub")) {
			model.addAttribute("topMenuList", topMenuRepository.findAll());
			model.addAttribute("submenu", subMenuRepository.findById(id).orElse(new SubMenu()));
			
			return "/menu/subform";
		}else if(type.equals("top")){
			model.addAttribute("topmenu", topMenuRepository.findById(id).orElse(new TopMenu()));
			return "/menu/topform";
		}
		return "/error";
	}
}
