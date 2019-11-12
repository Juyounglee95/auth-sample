package com.skcc.demo.context.bcm.domain.functions;

import java.util.List;

import com.skcc.demo.context.bcm.domain.functions.menu.model.SubMenu;
import com.skcc.demo.context.bcm.domain.functions.menu.model.TopMenu;

public interface FunctionsService {
	List<SubMenu> getSubMenuList();
	List<TopMenu> getTopMenuList();
	void createSubMenu(SubMenu submenu);
	void editSubMenu(Long id, SubMenu submenu);
	void deleteSubMenu(Long id);
	void createTopMenu(TopMenu topmenu);
	void editTopMenu(Long id, TopMenu topmenu);
	void deleteTopMenu(Long id);
}
