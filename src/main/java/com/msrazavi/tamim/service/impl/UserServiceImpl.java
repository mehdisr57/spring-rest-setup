package com.msrazavi.tamim.service.impl;

import com.msrazavi.tamim.model.UserEntity;
import com.msrazavi.tamim.repository.RoleRepository;
import com.msrazavi.tamim.repository.UserRepository;
import com.msrazavi.tamim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
