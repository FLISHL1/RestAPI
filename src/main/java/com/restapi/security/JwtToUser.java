package com.restapi.security;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.restapi.entity.Role;
import com.restapi.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToUser {
    public User convert(DecodedJWT jwt){
        return User.builder()
                .id(Long.valueOf(jwt.getSubject()))
                .username(jwt.getClaim("u").asString())
                .roles(extractRolesFromClaim(jwt))
                .build();
    }

    private List<Role> extractRolesFromClaim(DecodedJWT jwt){
        Claim claim = jwt.getClaim("rol");
        if (claim.isNull() || claim.isMissing()) return List.of();
        return claim.asList(Role.class);
    }
}
