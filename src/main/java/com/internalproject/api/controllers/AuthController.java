package com.internalproject.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;


@Api(value = "Authentication controller")
@RestController
@RequestMapping("/api")
public class AuthController {

    private final TokenEndpoint tokenEndPoint;

    @Autowired
    public AuthController(TokenEndpoint tokenEndPoint) {
        this.tokenEndPoint = tokenEndPoint;
    }

    @ApiImplicitParam(paramType = "Header", name = HttpHeaders.AUTHORIZATION, value = "Basic authentication.", required = true)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OAuth2AccessToken> login(Principal principal, @RequestParam
            Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return tokenEndPoint.postAccessToken(principal, parameters);
    }
}
