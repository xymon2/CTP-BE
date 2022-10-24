package com.litcode.server.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.litcode.server.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthorizationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private UserRepository userRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String userid = request.getParameter("id");
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userid,
                password);
        return authenticationManager.authenticate(authenticationToken);
    }
}

// @Override
// protected void successfulAuthentication( HttpServletRequest request,
// HttpServletResponse response,
// FilterChain chain, Authentication authResult ) throws IOException,
// ServletException {

// String userId = (String) authResult.getPrincipal();

// String jwtToken = JWT.create().withSubject(principalDetailis.getUsername())
// .withExpiresAt(new Date(System.currentTimeMillis() +
// JwtProperties.EXPIRATION_TIME))
// .withClaim("userId", principalDetailis.getUser().getUserId())
// .withClaim("username", principalDetailis.getUser().getUserName())
// .sign(Algorithm.HMAC512(JwtProperties.SECRET));

// response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX +
// jwtToken);
