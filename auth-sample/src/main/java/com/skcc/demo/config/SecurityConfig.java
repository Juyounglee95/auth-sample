package com.skcc.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.skcc.demo.context.auth.domain.authority.AuthorityService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private AuthorityService authorityService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// 페이지 권한 설정
				.antMatchers("/", "/login", "/error**", "/signup").permitAll()
				.antMatchers("/manage/**").hasAnyRole("MANAGER")
				.antMatchers("/counselor/**").hasAnyRole("COUNSELOR")
				.antMatchers("/partner/**").hasRole("PARTNER_COMPANY")
				.antMatchers("/members/**").hasRole("MEMBER_COMPANY")
				.antMatchers("/admin/**").hasRole("SYS_ADMIN")
				.antMatchers("/usermanage/**").hasRole("SYS_ADMIN")
				.antMatchers("/**").permitAll().and() // 로그인 설정
				.formLogin().loginPage("/login")
							.defaultSuccessUrl("/login/result").permitAll()
				.failureUrl("/login?error")
				.and() // 로그아웃	// 설정
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
				.clearAuthentication(true)
	            .logoutSuccessUrl("/logout/result")
				.invalidateHttpSession(true).and()
				// 403 예외처리 핸들링
				.exceptionHandling().accessDeniedPage("/login/denied");
	}
	@Override
	@Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authorityService).passwordEncoder(passwordEncoder());
    }
	
	
}
