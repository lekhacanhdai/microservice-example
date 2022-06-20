package com.example.resourceserverexample;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class UserController {

    private final TokenStore tokenStore;

    @GetMapping("/users")
    public String getUser(Authentication auth){
        return "name: " + auth.getName() + "\n" + "authorities: " + auth.getAuthorities() +
                "\n" + "user id: " + getAdditionalInfo(auth).get("user_id");
    }

    @GetMapping("/admin")
    public String getAdmin(Authentication auth){
        return "name: " + auth.getName() + "\n" + "authorities: " + auth.getAuthorities() +
                "\n" + "user id: " + getAdditionalInfo(auth).get("user_id");
    }

    private Map<String, Object> getAdditionalInfo(Authentication auth){
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        return accessToken.getAdditionalInformation();
    }
}
