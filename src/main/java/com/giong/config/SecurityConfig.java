package com.giong.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.giong.managedbean.LoginManagedBean;
import com.giong.service.interfaces.mt.IUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final int TOKEN_VALIDITY_SECONDS = 86400;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userService).passwordEncoder(this.passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.authorizeRequests().antMatchers("/faces/login.xhtml").permitAll()
								.antMatchers("/javax.faces.resource/**").permitAll()
								.antMatchers("/faces/**").authenticated()
//								.antMatchers("/faces/maintainance_setup/system_administrator/**").access("hasRole('SYS_ADMIN')")
//								.antMatchers("/faces/maintainance_setup/administrator/**").access("hasRole('ADMIN')")
//								.antMatchers("/faces/approval/**").access("hasRole('OFFICER')")
			.and()
				.formLogin().successHandler(this.savedRequestAwareAuthenticationSuccessHandler()).loginPage("/faces/login.xhtml").defaultSuccessUrl("/faces/home.xhtml", true)
			.and()
				.logout().invalidateHttpSession(true).deleteCookies("JSESSIONID")
			.and()
				.rememberMe().tokenRepository(this.persistentTokenRepository()).tokenValiditySeconds(this.TOKEN_VALIDITY_SECONDS)
			.and()
				.csrf().disable();
	}
	
	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		final PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Bean(name = "persistentTokenRepository")
	public PersistentTokenRepository persistentTokenRepository() {
		final JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(this.dataSource);
		return db;
	}
	
	@Bean(name = "savedRequestAwareAuthenticationSuccessHandler")
	public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
		final SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
		auth.setTargetUrlParameter("targetUrl");
		return auth;
	}
	
	@Bean(name = "loginBean")
	public LoginManagedBean loginBean() {
		return new LoginManagedBean();
	}
}
