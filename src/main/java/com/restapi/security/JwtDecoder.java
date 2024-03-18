package com.restapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtDecoder {
    public DecodedJWT decode(String token){
        return JWT.require(Algorithm.HMAC256("secret")).build().verify("secret");
    }
}
