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
import com.skcc.demo.context.auth.domain.authority.company.CompanyRepository;
import com.skcc.demo.context.auth.domain.authority.role.model.Role;
import com.skcc.demo.context.auth.domain.authority.role.model.RoleDivision;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AuthController {
	@Autowired
	private AccountRepository accountRepository;
	private AuthorityService authorityService;
	private CompanyRepository companyRepository;
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
    @GetMapping("/usermanage")
    public String disManageUsers(@PageableDefault Pageable pageable, Model model) {
    	model.addAttribute("userList", authorityService.findAllUsers(pageable));
    	return "/users/list";
    }
    
    @GetMapping("/roles")
    @ResponseBody
    public List<Role> getAllRoles(@RequestParam(value="roleDivision", required=true) RoleDivision roleDivision) {
    	return authorityService.getRoles(roleDivision);
    }
    
    @GetMapping("/usermanage/create")
    public String createUsers(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
        model.addAttribute("account", accountRepository.findById(id).orElse(new Account()));
        model.addAttribute("companies", companyRepository.findAll());
        return "/users/form";
    }

}
