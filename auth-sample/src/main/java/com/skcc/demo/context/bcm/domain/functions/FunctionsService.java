package com.skcc.demo.context.bcm.domain.functions;

import java.util.List;

import com.skcc.demo.context.bcm.domain.functions.menu.model.SubMenu;
import com.skcc.demo.context.bcm.domain.functions.menu.model.TopMenu;

public interface FunctionsService {
	List<SubMenu> getSubMenuList();
	List<TopMenu> getTopMenuList();
}
