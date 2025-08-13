package com.abbascoban.ui.rest.impl;

import com.abbascoban.business.services.IAuthServices;
import com.abbascoban.jwt.AuthRequest;
import com.abbascoban.jwt.AuthResponse;
import com.abbascoban.rootentity.RootEntity;
import com.abbascoban.ui.rest.IAuthController;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
}
