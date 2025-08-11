package com.abbascoban.business.services.impl;

import com.abbascoban.business.dto.UserDto;
import com.abbascoban.business.services.IUserServices;
import com.abbascoban.data.entity.User;
import com.abbascoban.data.repository.UserRepository;
import com.abbascoban.jwt.AuthRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements IUserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto register(AuthRequest request) {
        User user= new User();
        UserDto userDto= new UserDto();
        user.setUsername(request.getUsername());

        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);
        BeanUtils.copyProperties(savedUser,userDto);
        return userDto;


    }
}
