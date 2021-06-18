package com.taller1.VarelaFanny.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.taller1.VarelaFanny.model.UserRole;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;

	@Autowired
	private MyCustomUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

//		httpSecurity.formLogin().loginPage("/login").permitAll().and().authorizeRequests()    
//		.antMatchers("/index").permitAll()    
//		.antMatchers("/measurements/").hasAnyRole("admin")         
//		.antMatchers("/visits/").hasAnyRole("operator")         
//		.antMatchers("/physicalcheckups/**").hasRole("operator")
//		.antMatchers("/checkMeasurs/**").hasRole("operator") 
//		.anyRequest().authenticated().and().httpBasic().and().logout()         
//		.invalidateHttpSession(true).clearAuthentication(true)         
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")        
//		.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
//		

		 httpSecurity.csrf().disable().authorizeRequests().antMatchers("/", "index").permitAll()
		 .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/", true).and().logout()
		 .logoutUrl("/logout").clearAuthentication(true).invalidateHttpSession(true).logoutSuccessUrl("/login");
		
	
		
//		httpSecurity.authorizeRequests().antMatchers("/secure/**").authenticated().anyRequest().permitAll().and().httpBasic().and().logout()
//				.invalidateHttpSession(true).clearAuthentication(true)
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
//				.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}
}