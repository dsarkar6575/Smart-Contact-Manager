package com.scm.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
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

  @Autowired
  private OAuthAuthenticationSuccessHandler handler;

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
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home", "/register", "/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        // default login form
        httpSecurity.formLogin(formLogin -> {

            //
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.successForwardUrl("/user/dashboard");
            // formLogin.failureForwardUrl("/login?error=true");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");

            // formLogin.failureHandler(new AuthenticationFailureHandler() {

            // @Override
            // public void onAuthenticationFailure(HttpServletRequest request,
            // HttpServletResponse response,
            // AuthenticationException exception) throws IOException, ServletException {
            // // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method
            // 'onAuthenticationFailure'");
            // }

            // });

            // formLogin.successHandler(new AuthenticationSuccessHandler() {

            // @Override
            // public void onAuthenticationSuccess(HttpServletRequest request,
            // HttpServletResponse response,
            // Authentication authentication) throws IOException, ServletException {
            // // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method
            // 'onAuthenticationSuccess'");
            // }

            // });
            // formLogin.failureHandler(authFailtureHandler);

        });

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        // oauth configurations

        httpSecurity.oauth2Login(oauth -> {
            oauth.loginPage("/login");
            oauth.successHandler(handler);
        });

        httpSecurity.logout(logoutForm -> {
            logoutForm.logoutUrl("/do-logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });

     
        return httpSecurity.build();

    }

  @Bean
  public PasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
  }
}
