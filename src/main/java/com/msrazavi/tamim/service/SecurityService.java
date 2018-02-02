package com.msrazavi.tamim.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
