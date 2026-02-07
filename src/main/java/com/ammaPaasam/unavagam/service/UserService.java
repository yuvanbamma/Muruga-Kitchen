package com.ammaPaasam.unavagam.service;

import com.ammaPaasam.unavagam.entity.User;

import java.util.UUID;

public interface UserService {

    User findUserByEmail(String email) throws Exception;
}
