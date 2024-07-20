package com.sumit.gateway.controller;

import com.sumit.gateway.models.AuthResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

//    private Logger logger= LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(
           @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
           @AuthenticationPrincipal OidcUser user,
           Model model
    ){

        System.out.println("user email id : {} "+user.getEmail());

        //creating auth response object
        AuthResponse authResponse=new AuthResponse();

        //setting email to auth response
        authResponse.setUserId(user.getEmail());

        //setting take to auth response
        authResponse.setAccessToken(client.getAccessToken().getTokenValue());

        authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());

        authResponse.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());

        List<String> authority = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());


        authResponse.setAuthorities(authority);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }

}