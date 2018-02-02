package com.msrazavi.tamim.service;

import com.msrazavi.tamim.model.UserEntity;

public interface UserService {
    void save(UserEntity user);

    UserEntity findByUsername(String username);
}
