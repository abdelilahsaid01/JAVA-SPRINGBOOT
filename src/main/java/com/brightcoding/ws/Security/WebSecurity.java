package com.brightcoding.ws.Security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.brightcoding.ws.Services.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserService userDetailsService,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService=userDetailsService;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and() //accepter la communication avec d'autres api de URL different (Spring/Angular)
		.csrf().disable()
			.authorizeRequests()
//			.antMatchers(HttpMethod.POST,"/users")	//N'accepter que les request httpPost de Racine users
			.antMatchers(HttpMethod.POST,SecurityConstants.SIGN_UP_URL)
			.permitAll()
				.anyRequest().authenticated().
				and().addFilter(getAuthenticationFilter())
				.addFilter(new AuthorizationFilter(authenticationManager()))
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);	//En mode MicroService 'cest recommendé de garder cette session en status
				//.and().addFilter(new AuthenticationFilter(authenticationManager()));
		
	}
	
	protected AuthenticationFilter getAuthenticationFilter() throws Exception{
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/users/login");
		return filter;
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.userDetailsService(userDetailsService)	.passwordEncoder(bCryptPasswordEncoder);}
	
	

}
