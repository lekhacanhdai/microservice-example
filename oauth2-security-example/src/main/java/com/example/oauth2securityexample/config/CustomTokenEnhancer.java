package com.example.oauth2securityexample.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> additonalInfor = new HashMap<>();

        additonalInfor.put("user_id", new Random().nextInt());

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additonalInfor);
        return oAuth2AccessToken;
    }
}
