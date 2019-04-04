package hr.java.web.antunovic.moneyapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth
		.inMemoryAuthentication()
		.withUser("admin")
		.password(passwordEncoder.encode("adminpass"))
		.roles("ADMIN", "USER")
		.and()
		.withUser("user")
		.password(passwordEncoder.encode("userpass"))
		.roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests()
		.antMatchers("/**")
		.hasRole("USER")
		.antMatchers("/unos/**").permitAll()
		.and()
		.formLogin()
		//.loginPage("/login")
		.defaultSuccessUrl("/unos", true)
		.and()
		.logout();
		//.logoutSuccessUrl("/login");
	}
	
}
