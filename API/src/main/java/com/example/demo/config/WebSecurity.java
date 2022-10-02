package com.example.demo.config; 
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; 
import org.springframework.security.config.http.SessionCreationPolicy;  
import org.springframework.security.web.SecurityFilterChain; 

@Configuration
@EnableWebSecurity 
@EnableGlobalMethodSecurity(
  prePostEnabled = true//, // @PreAuthorize and @PostAuthorize 
  //securedEnabled = true // @Secured 
  //, jsr250Enabled = true   // @RoleAllowed 
  )  
public class WebSecurity {
    
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {   
		/*
		* 1. cookies desactived
		* 2. CORS Configuration enabled whit defaul values
		* 3. disable CSRF filter 
        * 4. allow all petitions from allow server but it should be inside cors allow server
		*/ 

		http 
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.cors().and()
			.csrf().disable()
			.authorizeRequests() 
                .antMatchers("/", "/index", "/**").permitAll()
                .anyRequest().permitAll();

				 
	    return http.build();
	} 
 
}