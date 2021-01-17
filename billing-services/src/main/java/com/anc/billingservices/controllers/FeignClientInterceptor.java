package com.anc.billingservices.controllers;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

//@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_TYPE = "Bearer";


    @Override
    public void apply(RequestTemplate requestTemplate) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("apply " + authentication);
//        if (authentication != null) {
//            System.out.println(authentication.getDetails());
//            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
//            String header = String.format("%s %s", TOKEN_TYPE, details.getTokenValue());
//            System.out.println(details.getTokenValue());
//            requestTemplate.header(AUTHORIZATION_HEADER, header);
//        }
    }
}
