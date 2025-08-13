package com.abbascoban.business.services;

import com.abbascoban.jwt.AuthRequest;
import com.abbascoban.jwt.AuthResponse;

public interface IAuthServices {

    public AuthResponse authenticate(AuthRequest request);
}
