package com.skcc.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.skcc.demo.context.auth.domain.authority.account.AccountRepository;
import com.skcc.demo.context.auth.domain.authority.account.model.Account;
import com.skcc.demo.context.auth.domain.authority.company.CompanyRepository;
import com.skcc.demo.context.auth.domain.authority.company.model.Company;
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
			CompanyRepository companyRepository, PermissionRepository permissionRepository, SubMenuRepository subMenuRepository, TopMenuRepository topMenuRepository) {
		
		return (args)->{
			createMenus(subMenuRepository, topMenuRepository, permissionRepository, roleRepository);
			createUsers(accountRepository, roleRepository, companyRepository, permissionRepository);
			
		};
		
	}
	private void createMenus(SubMenuRepository subMenuRepository, TopMenuRepository topMenuRepository, PermissionRepository permissionRepository
			, RoleRepository roleRepository) {
		TopMenu topmenu1 = new TopMenu("상위메뉴1");
		topMenuRepository.save(topmenu1);
		SubMenu subMenu1 = new SubMenu("하위1", topmenu1.getId());
		SubMenu subMenu2 = new SubMenu("하위2", topmenu1.getId());
		SubMenu subMenu3 = new SubMenu("하위3", topmenu1.getId());
		subMenuRepository.save(subMenu1);
		subMenuRepository.save(subMenu2);
		subMenuRepository.save(subMenu3);
		Permission per1 = new Permission("하위1관리권한", PerLevel.ADMIN, subMenu1.getId());
		Permission per2 = new Permission("하위1쓰기권한", PerLevel.EDIT, subMenu1.getId());
		Permission per3 = new Permission("하위1읽기권한", PerLevel.VIEW, subMenu1.getId());
		Permission per4 = new Permission("하위2관리권한", PerLevel.ADMIN, subMenu2.getId());
		Permission per5 = new Permission("하위2쓰기권한", PerLevel.EDIT, subMenu2.getId());
		Permission per6 = new Permission("하위2읽기권한", PerLevel.VIEW, subMenu2.getId());
		Permission per7 = new Permission("하위3관리권한", PerLevel.ADMIN, subMenu3.getId());
		Permission per8 = new Permission("하위3쓰기권한", PerLevel.EDIT, subMenu3.getId());
		Permission per9 = new Permission("하위3읽기권한", PerLevel.VIEW, subMenu3.getId());
		
		
		Role role1 = new Role("관리자", RoleDivision.SYS_ADMIN,true);
		Role role2 = new Role("상담사", RoleDivision.COUNSELOR,true);
		roleRepository.save(role1);
		roleRepository.save(role2);
		per1.setRoleId(role1.getId());

		per2.setRoleId(role1.getId());

		per3.setRoleId(role1.getId());
		per4.setRoleId(role1.getId());
		
		per5.setRoleId(role1.getId());
		
		per6.setRoleId(role1.getId());
		
		per7.setRoleId(role2.getId());
		
		per8.setRoleId(role2.getId());
		per9.setRoleId(role2.getId());
		permissionRepository.save(per1);
		permissionRepository.save(per2);
		permissionRepository.save(per3);
		permissionRepository.save(per4);
		permissionRepository.save(per5);
		permissionRepository.save(per6);
		permissionRepository.save(per7);
		permissionRepository.save(per8);
		permissionRepository.save(per9);
		
		
		
		
		
	}
	private void createUsers(AccountRepository accountRepository, RoleRepository roleRepository,
			CompanyRepository companyRepository, PermissionRepository permissionRepository) {
		Company sk = new Company("sk");
		companyRepository.save(sk);
		
		
		//Account account = new Account("사용자1", "123", "users@sk.com", companyRepository.findByName("sk").getId(), true);
		//account.setRoleId(roleRepository.findByName("관리자"));
		//accountRepository.save(account);
		
	}
}
