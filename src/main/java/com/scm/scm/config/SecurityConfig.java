package com.scm.scm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;

    SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
   
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

  @Bean
  public AuthenticationProvider authenticationProvider(){
      DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
      //user details service object
        daoAuthenticationProvider.setUserDetailsPasswordService(null);
        //password encoder service
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
      return daoAuthenticationProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
  }
}
