package com.skcc.demo.context.bcm.domain.functions;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skcc.demo.context.auth.domain.authority.AuthorityService;
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
	@Autowired
	AuthorityService authorityService;
	
	@Override
	public List<SubMenu> getSubMenuList() {
		
		return subMenuRepository.findAll();
	}

	@Override
	public List<TopMenu> getTopMenuList() {
		
		return topMenuRepository.findAll();
	}

	@Override
	public void createSubMenu(SubMenu submenu) {
		subMenuRepository.save(submenu);
		authorityService.createPermission(submenu.getId(), submenu.getName());
		
	}

	@Override
	public void editSubMenu(Long id, SubMenu submenu) {
		SubMenu persistSub = subMenuRepository.findById(id).get();
		BeanUtils.copyProperties(submenu, persistSub, "id");
		subMenuRepository.save(persistSub);
		
	}

	@Override
	public void deleteSubMenu(Long id) {
		subMenuRepository.deleteById(id);
		authorityService.deletePers(id); //menu와 연결되어있는 권한 & 역할 내에 권한 삭제
		
	}

	@Override
	public void createTopMenu(TopMenu topmenu) {
		topMenuRepository.save(topmenu);
		
	}

	@Override
	public void editTopMenu(Long id, TopMenu topmenu) {
		TopMenu persistTop = topMenuRepository.findById(id).get();
		if(!topmenu.getTopMenuUsage()) { //상위 메뉴를 사용하지 않게 수정하면 SET TOPMENUID OF하위메뉴 NULL
			setNullTopId(id);
		}
		BeanUtils.copyProperties(topmenu, persistTop, "id");
		topMenuRepository.save(persistTop);
		
	}

	@Override
	public void deleteTopMenu(Long id) {
		topMenuRepository.deleteById(id);
		setNullTopId(id);
		
		
	}
	
	public void setNullTopId(Long id) {
		List<SubMenu> subList=subMenuRepository.findByTopMenuId(id);
		for(SubMenu sub: subList) {
			sub.setTopMenuId(null);
		}
	}

}
