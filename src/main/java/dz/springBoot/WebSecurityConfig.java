package dz.springBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import dz.springBoot.service.CustomUserDetailsService;

 
/**
 * Configuration 
 * 
 * @author AEK
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	@Qualifier("userDetailsService")
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

//		//ROLE_SUPERUSER ROLE_USER ROLE_ADMIN ROLE_NEW
//		http.authorizeRequests() 
//			.antMatchers("/**").hasAnyAuthority("ROLE_USER", "ROLE_NEW", "ROLE_SUPERUSER", "ROLE_ADMIN")
//			.antMatchers("/**").hasAnyAuthority("ROLE_SUPERUSER", "ROLE_ADMIN")
//			.antMatchers("/**").hasAnyAuthority("ROLE_SUPERUSER", "ROLE_ADMIN")
//			.antMatchers("/**").hasAuthority("ROLE_SUPERUSER")
//			
//			.antMatchers("/api/users").hasAnyAuthority("ROLE_SUPERUSER", "")
//            
//			.anyRequest().authenticated()
//			.and()
//			
//			.formLogin().permitAll()
//			.and()
//			
//			.logout().permitAll()
//			.and()
//			
//			.exceptionHandling().accessDeniedPage("/403");
		http.cors().and().csrf().disable();
	
	}

}
