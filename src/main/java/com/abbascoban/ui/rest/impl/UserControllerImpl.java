package com.abbascoban.ui.rest.impl;

import com.abbascoban.business.dto.UserDto;
import com.abbascoban.business.services.IUserServices;
import com.abbascoban.jwt.AuthRequest;
import com.abbascoban.rootentity.RootEntity;
import com.abbascoban.ui.rest.IUserController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class UserControllerImpl implements IUserController {

    @Autowired
    private IUserServices userServices;


    @PostMapping("/register")
    @Override
    public RootEntity<UserDto> register(@Valid @RequestBody AuthRequest authRequest, WebRequest webRequest) {
        UserDto register = userServices.register(authRequest);
       return RootEntity.ok(register,webRequest);
    }
}
