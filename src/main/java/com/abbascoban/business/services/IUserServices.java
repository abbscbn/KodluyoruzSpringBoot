package com.abbascoban.business.services;

import com.abbascoban.business.dto.UserDto;
import com.abbascoban.jwt.AuthRequest;

public interface IUserServices {
    public UserDto register(AuthRequest request);
}
