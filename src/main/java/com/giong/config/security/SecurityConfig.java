package com.giong.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.giong.service.interfaces.mt.IUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	public static final String APPLICATION_SECURITY_KEY = "application-security.key";
	
	private final int TOKEN_VALIDITY_SECONDS = 30;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private IUserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/javax.faces.resource/**", "/resources/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		
		// @formatter:off
		http
			.csrf().disable().httpBasic()
			.and()
				.headers().cacheControl().and()
			.and()
				.sessionManagement()
				.sessionFixation().none()
			.and()
				.authorizeRequests().antMatchers("/**").authenticated()
			.and()
				.formLogin().permitAll()
							.loginPage("/pages/login.xhtml")
			.and()
				.logout().logoutUrl("/logout")
						 .invalidateHttpSession(true)
						 .deleteCookies("JSESSIONID", AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY)
						 .logoutSuccessUrl("/")
			.and()
				.rememberMe().tokenRepository(this.tokenRepository()).tokenValiditySeconds(this.TOKEN_VALIDITY_SECONDS);
		;		
		// @formatter:on
	}
	
	@Override
	@Bean(name = "authenticationManager")
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}
	
	@Bean(name = "tokenRepository")
	public PersistentTokenRepository tokenRepository() {
		final JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(this.dataSource);
		return db;
	}
	
}
