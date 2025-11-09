package com.detech.gsrt.controller.implementation;

import com.detech.gsrt.controller.api.AuthenticationApi;
import com.detech.gsrt.dto.auth.AuthenticationRequest;
import com.detech.gsrt.dto.auth.AuthenticationResponse;
import com.detech.gsrt.modeles.auth.ExtendedUser;
import com.detech.gsrt.services.ApplicationUserDetailsService;
import com.detech.gsrt.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationContollerImpl implements AuthenticationApi {

    @Autowired
    private ApplicationUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate (AuthenticationRequest request) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getLogin());
        final String jwt = this.jwtUtil.generateToken(new ExtendedUser(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()));
        return ResponseEntity.ok(AuthenticationResponse.builder().accessTokeen(jwt).build());
    }

}
