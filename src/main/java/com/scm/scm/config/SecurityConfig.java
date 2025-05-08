package com.scm.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.scm.scm.services.Impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

   
  // UserDetails user1=User
  // .withDefaultPasswordEncoder()
  // .username("admin123")
  // .password("admin123")
  // .roles("ADMIN","USER")
  // .build();
  // UserDetails user2=User
  // .withUsername("dsarkar")
  // .password("1234")
  // .build();


  // @Bean   
  // public UserDetailsService userDetailsService(){
  //   return new InMemoryUserDetailsManager(user1);
  // }

  @Autowired
  private SecurityCustomUserDetailService securityCustomUserDetailService;
  @Bean
  public AuthenticationProvider authenticationProvider(){
      DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
      //user details service object
        daoAuthenticationProvider.setUserDetailsService(securityCustomUserDetailService);
        //password encoder service
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
      return daoAuthenticationProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
  }
}
