package com.skcc.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.skcc.demo.context.auth.domain.authority.account.AccountRepository;
import com.skcc.demo.context.auth.domain.authority.account.model.Account;
import com.skcc.demo.context.auth.domain.authority.permission.PermissionRepository;
import com.skcc.demo.context.auth.domain.authority.permission.model.PerLevel;
import com.skcc.demo.context.auth.domain.authority.permission.model.Permission;
import com.skcc.demo.context.auth.domain.authority.role.RoleRepository;
import com.skcc.demo.context.auth.domain.authority.role.model.Role;
import com.skcc.demo.context.auth.domain.authority.role.model.RoleDivision;
import com.skcc.demo.context.bcm.domain.functions.menu.SubMenuRepository;
import com.skcc.demo.context.bcm.domain.functions.menu.TopMenuRepository;
import com.skcc.demo.context.bcm.domain.functions.menu.model.SubMenu;
import com.skcc.demo.context.bcm.domain.functions.menu.model.TopMenu;

@EnableJpaAuditing
@SpringBootApplication
public class AuthSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthSampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner insertData(AccountRepository accountRepository, RoleRepository roleRepository,
			PermissionRepository permissionRepository, SubMenuRepository subMenuRepository,
			TopMenuRepository topMenuRepository) {

		return (args) -> {
			createMenus(subMenuRepository, topMenuRepository, permissionRepository, roleRepository);

		};

	}

	private void createMenus(SubMenuRepository subMenuRepository, TopMenuRepository topMenuRepository,
			PermissionRepository permissionRepository, RoleRepository roleRepository) {
		TopMenu topmenu1 = new TopMenu("관리자 메뉴");
		TopMenu topmenu2 = new TopMenu("상담사 메뉴");
		topmenu1.setUrl("/admin");
		topmenu2.setUrl("/counselor");
		topMenuRepository.save(topmenu1);
		topMenuRepository.save(topmenu2);
		SubMenu subMenu1 = new SubMenu("메뉴 관리", topmenu1.getId());
		SubMenu subMenu2 = new SubMenu("사용자 관리", topmenu1.getId());
		SubMenu subMenu3 = new SubMenu("권한 관리", topmenu1.getId());
		SubMenu subMenu4 = new SubMenu("상담 게시판", topmenu2.getId());
		subMenuRepository.save(subMenu1);
		subMenuRepository.save(subMenu2);
		subMenuRepository.save(subMenu3);
		subMenuRepository.save(subMenu4);
		Permission per1 = new Permission("관리권한", PerLevel.ADMIN, subMenu1.getId());
		Permission per2 = new Permission("쓰기권한", PerLevel.EDIT, subMenu1.getId());
		Permission per3 = new Permission("읽기권한", PerLevel.VIEW, subMenu1.getId());
		Permission per4 = new Permission("관리권한", PerLevel.ADMIN, subMenu2.getId());
		Permission per5 = new Permission("쓰기권한", PerLevel.EDIT, subMenu2.getId());
		Permission per6 = new Permission("읽기권한", PerLevel.VIEW, subMenu2.getId());
		Permission per7 = new Permission("관리권한", PerLevel.ADMIN, subMenu3.getId());
		Permission per8 = new Permission("쓰기권한", PerLevel.EDIT, subMenu3.getId());
		Permission per9 = new Permission("읽기권한", PerLevel.VIEW, subMenu3.getId());
		Permission per10 = new Permission("관리권한", PerLevel.ADMIN, subMenu4.getId());
		Permission per11 = new Permission("쓰기권한", PerLevel.EDIT, subMenu4.getId());
		Permission per12= new Permission("읽기권한", PerLevel.VIEW, subMenu4.getId());
		permissionRepository.save(per1);
		permissionRepository.save(per2);
		permissionRepository.save(per3);
		permissionRepository.save(per4);
		permissionRepository.save(per5);
		permissionRepository.save(per6);
		permissionRepository.save(per7);
		permissionRepository.save(per8);
		permissionRepository.save(per9);
		permissionRepository.save(per10);
		permissionRepository.save(per11);
		permissionRepository.save(per12);
		

		Role role1 = new Role("관리자", RoleDivision.SYS_ADMIN, true);
		Role role2 = new Role("상담사", RoleDivision.COUNSELOR, true);
		role1.getPerIdList().add(per1.getId());
		role1.getPerIdList().add(per2.getId());
		role1.getPerIdList().add(per3.getId());
		role1.getPerIdList().add(per4.getId());
		role1.getPerIdList().add(per5.getId());
		role1.getPerIdList().add(per6.getId());
		role1.getPerIdList().add(per7.getId());
		role1.getPerIdList().add(per8.getId());

		role1.getPerIdList().add(per9.getId());

		role1.getPerIdList().add(per10.getId());
		role1.getPerIdList().add(per11.getId());
		role1.getPerIdList().add(per12.getId());

		role2.getPerIdList().add(per10.getId());
		role2.getPerIdList().add(per11.getId());
		role2.getPerIdList().add(per12.getId());

		roleRepository.save(role1);
		roleRepository.save(role2);

	}

}
