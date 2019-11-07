package com.skcc.demo.context.bcm.domain.functions;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skcc.demo.context.bcm.domain.functions.menu.SubMenuRepository;
import com.skcc.demo.context.bcm.domain.functions.menu.TopMenuRepository;
import com.skcc.demo.context.bcm.domain.functions.menu.model.SubMenu;
import com.skcc.demo.context.bcm.domain.functions.menu.model.TopMenu;

@Service
@Transactional
public class FunctionsLogic implements FunctionsService {
	@Autowired
	SubMenuRepository subMenuRepository;
	@Autowired
	TopMenuRepository topMenuRepository;
	@Override
	public List<SubMenu> getSubMenuList() {
		
		return subMenuRepository.findAll();
	}

	@Override
	public List<TopMenu> getTopMenuList() {
		
		return topMenuRepository.findAll();
	}

}
