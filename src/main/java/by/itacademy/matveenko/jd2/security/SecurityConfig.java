package by.itacademy.matveenko.jd2.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import by.itacademy.matveenko.jd2.service.IUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    IUserService userServiceImpl;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	   return new BCryptPasswordEncoder();
	   }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/", "/base_page", "/registration/**", "/resources/**")
                .permitAll()
	                .anyRequest()
	                .authenticated()
                .and()
	               .formLogin()
	                //.loginPage("/login")
	                //.loginProcessingUrl("/authenticateTheUser")
	                .failureUrl("/base_page?error")
	                .defaultSuccessUrl("/news_list")
	                .permitAll()
                .and()
	                .logout()
	                .logoutSuccessUrl("/")
	                .permitAll();
    }
    
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl).passwordEncoder(bCryptPasswordEncoder());
    }
    
    public class EncodingFilter implements Filter {

		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			chain.doFilter(request, response);
		}		
	}    
}