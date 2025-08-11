package com.abbascoban.ui.rest;

import com.abbascoban.business.dto.UserDto;
import com.abbascoban.jwt.AuthRequest;
import com.abbascoban.rootentity.RootEntity;
import org.springframework.web.context.request.WebRequest;

public interface IUserController {
    public RootEntity<UserDto> register(AuthRequest authRequest, WebRequest webRequest);
}
