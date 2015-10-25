package com.giong.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

import com.giong.service.interfaces.mt.IUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final int TOKEN_VALIDITY_SECONDS = 86400;
	private final String APPLICATION_KEY = "application.key";
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	IUserDetailsService userDetailsService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this.rememberMeAuthenticationProvider()).userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/javax.faces.resource/**", "/resources/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/faces/**").authenticated().and().formLogin().loginPage("/welcome.xhtml").loginProcessingUrl("/j_spring_security_check")
				.successHandler(this.savedRequestAwareAuthenticationSuccessHandler()).permitAll().and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/")
				.invalidateHttpSession(true).deleteCookies("JSESSIONID").and().rememberMe().rememberMeServices(this.rememberMeServices()).and().csrf().disable();
	}
	
	@Override
	@Bean(name = "authenticationManager")
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		final PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Bean(name = "savedRequestAwareAuthenticationSuccessHandler")
	public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
		final SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
		auth.setTargetUrlParameter("targetUrl");
		return auth;
	}
	
	/*
	 * -------------------------------------------------------------	Remember-Me stuff	------------------------------------------------------
	 */
	@Bean(name = "rememberMeFilter")
	public RememberMeAuthenticationFilter rememberMeFilter() {
		final RememberMeAuthenticationFilter rememberMeFilter = new RememberMeAuthenticationFilter(this.authenticationManager, this.rememberMeServices());
		return rememberMeFilter;
	}
	
	@Bean(name = "rememberMeServices")
	public RememberMeServices rememberMeServices() {
		
		final JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(this.dataSource);
		
		final PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices = new PersistentTokenBasedRememberMeServices(this.APPLICATION_KEY, this.userDetailsService,
				jdbcTokenRepository);
		persistentTokenBasedRememberMeServices.setAlwaysRemember(true);
		persistentTokenBasedRememberMeServices.setTokenValiditySeconds(this.TOKEN_VALIDITY_SECONDS);
		
		return persistentTokenBasedRememberMeServices;
	}
	
	@Bean(name = "rememberMeAuthenticationProvider")
	public RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
		final RememberMeAuthenticationProvider rememberMeAuthenticationProvider = new RememberMeAuthenticationProvider(this.APPLICATION_KEY);
		return rememberMeAuthenticationProvider;
	}
	
}
