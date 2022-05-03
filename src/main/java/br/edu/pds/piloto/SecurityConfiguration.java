package br.edu.pds.piloto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


protected void configure(HttpSecurity http) throws Exception {
	
	http.authorizeRequests()
	
	.antMatchers("/css/**", "/js/**","/resources/**","/error").permitAll()
	.antMatchers("/").permitAll().antMatchers("/admin").access("hasAuthority('ADMIN')")
	
	.anyRequest().authenticated().and().formLogin().loginPage("/login")
	.permitAll().and().logout()
	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	.logoutSuccessUrl("/login").permitAll().and().httpBasic();
	
	http.csrf().disable();
	http.headers().frameOptions().disable();
}


@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.inMemoryAuthentication().withUser("edilson").password(passwordEncoder().encode("2022")).authorities("ADMIN")
.and().withUser("user").password(passwordEncoder().encode("password")).authorities("USER");

}
}
