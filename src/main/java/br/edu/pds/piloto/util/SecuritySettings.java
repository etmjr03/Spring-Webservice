package br.edu.pds.piloto.util;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
public class SecuritySettings extends WebSecurityConfigurerAdapter{

@Autowired
private DataSource dataSource;

@Bean
public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}

protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	 auth.jdbcAuthentication().dataSource(dataSource)
	.usersByUsernameQuery("Select usuarios.nome as username, usuarios.senha as password, 1 as enable from usuarios where nome =?")
	.authoritiesByUsernameQuery("Select usuarios.nome as username, roles.nome_role as authority from permissoes inner join usuarios on usuarios.id=permissoes.usuario_id inner join roles on roles.id=permissoes.role_id where usuarios.nome=?")
	.passwordEncoder(new BCryptPasswordEncoder());
}

protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable().authorizeRequests()
	.antMatchers("/listarRole", "/cadastrarRole", "/salvarRole", "/editarRole", "/excluirRole").hasAuthority("role") 
	.antMatchers("/listarPermissao", "/cadastrarPermissao", "/salvarPermissao", "/editarPermissao", "/excluirPermissao").hasAuthority("permissao") 
	.antMatchers("/listarUsuario", "/cadastrarUsuario", "/salvarUsuario", "/editarUsuario", "/excluirUsuario").hasAuthority("usuario")
	.antMatchers("/listarCidade", "/cadastrarCidade", "/salvarCidade", "/editarCidade", "/excluirCidade").hasAuthority("cidade")
	.antMatchers("/listarEstado", "/cadastrarEstado", "/salvarEstado", "/editarEstado", "/excluirEstado").hasAuthority("estado")
	
	.and().formLogin().permitAll().loginPage("/login").permitAll()
	.and().logout()
	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	.logoutSuccessUrl("/")
	.and()
	.exceptionHandling()
	.accessDeniedPage("/negarAcesso");
}
}
