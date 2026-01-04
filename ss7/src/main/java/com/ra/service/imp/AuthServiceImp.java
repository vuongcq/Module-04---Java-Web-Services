package com.ra.service.imp;

import com.ra.model.dto.user.LoginRequestDTO;
import com.ra.model.dto.user.UserResponseDTO;
import com.ra.security.UserPriciple;
import com.ra.security.jwt.JwtProvider;
import com.ra.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public UserResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication;
        authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(),loginRequestDTO.getPassword())
        );
        UserPriciple userPriciple = (UserPriciple) authentication.getPrincipal(); // chuyen doi dl
        return UserResponseDTO.builder()
                .username(userPriciple.getUsername())
                .typeToken("Bearer")
                .accessToken(jwtProvider.generateToken(userPriciple))
                .roles(userPriciple.getUser().getRoles())
                .build();

    }
}
