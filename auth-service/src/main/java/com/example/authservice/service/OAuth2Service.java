package com.example.authservice.service;

import com.example.authservice.model.AccessToken;

public interface OAuth2Service {
    AccessToken getAccessToken(String clientId, String clientSecret, String redirectUri, String code, String state);
}
