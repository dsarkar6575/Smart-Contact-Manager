package com.scm.scm.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

  public static String getEmailOfLoggedUser(Authentication authentication){

  

    if(authentication instanceof OAuth2AuthenticationToken){

      // for manual user
      var aOAuth2AuthenticationToken=(OAuth2AuthenticationToken) authentication;
      var clientId=aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

      var oauth2User=(OAuth2User) authentication.getPrincipal();
      String username="";

    //for signin with google user
    if(clientId.equalsIgnoreCase("google")){
      
      System.out.println("Getting email from Google");
      username=oauth2User.getAttribute("email").toString();
    }else if(clientId.equalsIgnoreCase("github")){


     //for signin with github user
     System.out.println("Getting email from GitHub"); 
     username=oauth2User.getAttribute("email") !=null ? oauth2User.getAttribute("email") 
        : oauth2User.getAttribute("login").toString()+"@gmail.com";
    }
    return username;
    }
    System.out.println("Getting data from local database..");
    return authentication.getName();
  }
}
