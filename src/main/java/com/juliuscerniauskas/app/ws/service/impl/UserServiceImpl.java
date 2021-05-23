package com.juliuscerniauskas.app.ws.service.impl;

import com.juliuscerniauskas.app.ws.io.entity.UserEntity;
import com.juliuscerniauskas.app.ws.repository.UserRepository;
import com.juliuscerniauskas.app.ws.service.UserService;
import com.juliuscerniauskas.app.ws.shared.Utils;
import com.juliuscerniauskas.app.ws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Utils utils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, Utils utils, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.utils = utils;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto createUser(UserDto user) {

        if (userRepository.existsByEmail(user.getEmail())) throw new RuntimeException("Record already exists");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(utils.generateUserId(30));

        UserEntity savedUser = userRepository.save(userEntity);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(savedUser, returnValue);

        return returnValue;
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntityByEmail = userRepository.findUserEntityByEmail(email);

        if (userEntityByEmail == null) throw new UsernameNotFoundException(email);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntityByEmail, returnValue);

        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        UserEntity userEntityByEmail = userRepository.findUserEntityByEmail(identifier);

        if (userEntityByEmail == null) throw new UsernameNotFoundException(identifier);

        return new User(userEntityByEmail.getEmail(), userEntityByEmail.getEncryptedPassword(), new ArrayList<>());
    }
}
