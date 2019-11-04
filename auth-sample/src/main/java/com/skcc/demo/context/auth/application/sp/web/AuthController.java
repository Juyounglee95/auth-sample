package com.skcc.demo.context.auth.application.sp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skcc.demo.context.auth.domain.authority.AuthorityService;
import com.skcc.demo.context.auth.domain.authority.account.AccountRepository;
import com.skcc.demo.context.auth.domain.authority.account.model.Account;
import com.skcc.demo.context.auth.domain.authority.permission.PermissionRepository;
import com.skcc.demo.context.auth.domain.authority.permission.model.Permission;
import com.skcc.demo.context.auth.domain.authority.role.RoleRepository;
import com.skcc.demo.context.auth.domain.authority.role.model.Role;
import com.skcc.demo.context.auth.domain.authority.role.model.RoleDivision;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AuthController {
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	private AuthorityService authorityService;
	
	@GetMapping("/") //메인페이지
	public String index() {
		return "/index";
	}
	
	  // 회원가입 페이지
    @GetMapping("/signup")
    public String dispSignup() {
        return "/signup";
    }
    
    // 회원가입 처리
    @PostMapping("/signup")
    public String execSignup(Account member) {
        authorityService.joinUser(member);

        return "redirect:/login";
    }
    // 로그인 페이지
    @GetMapping("/login")
    public String dispLogin() {
        return "/login";
    }
    // 로그인 결과 페이지
    @GetMapping("/login/result")
    public String dispLoginResult() {
        return "/index";
    }

//    // 로그아웃 결과 페이지
    @GetMapping("/logout/result")
    public String dispLogout() {
        return "/logout";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String dispDenied() {
        return "/denied";
    }

    // 내 정보 페이지
    @GetMapping("/user/info")
    public String dispMyInfo() {
        return "/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "/admin";
    }
    //권한 관리 페이지
    @GetMapping("/admin/usermanage")
    public String disManageUsers(@PageableDefault Pageable pageable, Model model) {
    	model.addAttribute("userList", authorityService.findAllUsers(pageable));
    	return "/users/list";
    }
    
    @GetMapping("/roles")
    @ResponseBody
    public List<Role> getAllRoles(@RequestParam(value="roleDivision", required=true) RoleDivision roleDivision) {
    	return authorityService.getRoles(roleDivision);
    }
    
    @GetMapping("/admin/usermanage/create")
    public String createUsers(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
        model.addAttribute("account", accountRepository.findById(id).orElse(new Account()));
        return "/users/form";	
    }
    
    @GetMapping("/admin/permission/getperlist")
    public String getAllPermissions(@RequestParam(value ="roleId", defaultValue="0")Long roleId, Model model){
    	System.out.println(roleId);
    	model.addAttribute("permissionList", permissionRepository.findAll());
    	model.addAttribute("role", roleRepository.findById(roleId).get());
    //	model.addAttribute("role", roleRepository.findById(roleId).get().getRoleDivision().getValue());
    	//model.addAttribute("role_name",roleRepository.findById(roleId).get().getName());
    	return "/permission/list";
    }
    
    @GetMapping("/admin/permission")
    public String managePermission( Model model) 
    {	//System.out.println(roleId);
    	//if(roleId!=0) {
    	//model.addAttribute("permissionList", permissionRepository.findAll());
    	
    //	}
    	return"/permission/list";
    }
    @GetMapping("/permissions")
    @ResponseBody
    public List<Permission> getAllPermissions(@RequestParam(value="roleId", required=true)Long roleId) {
    	return authorityService.getPermissions();
    }

}
