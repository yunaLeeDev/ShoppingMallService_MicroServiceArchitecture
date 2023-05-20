package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId); // User ID로 검색. DB에서
    Iterable<UserEntity> getUserByAll(); // 반복적인 (Iterable) 데이터에서.
    UserDto deleteUser(String userId);
}
