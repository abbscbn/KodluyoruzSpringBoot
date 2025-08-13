package com.abbascoban.business.services.impl;

import com.abbascoban.business.services.IAuthServices;
import com.abbascoban.data.entity.User;
import com.abbascoban.data.repository.UserRepository;
import com.abbascoban.exception.BaseExcepiton;
import com.abbascoban.exception.ErrorMessage;
import com.abbascoban.exception.ErrorMessageType;
import com.abbascoban.jwt.AuthRequest;
import com.abbascoban.jwt.AuthResponse;
import com.abbascoban.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtService jwtService;



    @Override
    public AuthResponse authenticate(AuthRequest request) {


            UsernamePasswordAuthenticationToken auth= new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
            authenticationProvider.authenticate(auth);

            Optional<User> optUser = userRepository.findByUsername(request.getUsername());

            String token= jwtService.generateToken(optUser.get());
            return new AuthResponse(token);


    }
}
