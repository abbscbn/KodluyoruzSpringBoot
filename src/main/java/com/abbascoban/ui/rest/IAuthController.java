package com.abbascoban.ui.rest;

import com.abbascoban.jwt.AuthRequest;
import com.abbascoban.jwt.AuthResponse;
import com.abbascoban.rootentity.RootEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.WebRequest;

public interface IAuthController {
    public RootEntity<AuthResponse> authenticate(AuthRequest request, WebRequest webRequest);
}
