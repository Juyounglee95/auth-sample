package com.skcc.demo.context.auth.application.sp.web;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.skcc.demo.context.auth.domain.authority.AuthorityService;
import com.skcc.demo.context.auth.domain.authority.account.model.Account;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AuthController {

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
    @GetMapping("/usermanage")
    public String disManageUsers(@PageableDefault Pageable pageable, Model model) {
    	model.addAttribute("userList", authorityService.findAllUsers(pageable));
    	return "/users/list";
    }

}
