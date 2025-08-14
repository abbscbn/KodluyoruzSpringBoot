package com.abbascoban.ui.rest.impl;

import com.abbascoban.business.services.IAuthServices;
import com.abbascoban.jwt.AuthRequest;
import com.abbascoban.jwt.AuthResponse;
import com.abbascoban.jwt.RefreshTokenRequest;
import com.abbascoban.rootentity.RootEntity;
import com.abbascoban.ui.rest.IAuthController;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
public class AuthControllerImpl implements IAuthController {

    @Autowired
    private IAuthServices authServices;

    @PostMapping("/authenticate")
    @Override
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest request, WebRequest webRequest) {

        return RootEntity.ok(authServices.authenticate(request),webRequest);

    }
    @PostMapping("/refreshtoken")
    @Override
    public RootEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request, WebRequest webRequest) {
        return RootEntity.ok(authServices.refreshToken(request),webRequest);
    }
}
