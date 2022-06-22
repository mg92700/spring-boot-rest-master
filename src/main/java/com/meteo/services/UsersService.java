package com.meteo.services;


import com.meteo.model.UserEntity;

import java.util.List;

public interface UsersService {

    UserEntity addUser(UserEntity userEntity);

    List<UserEntity> getListUsers();

    UserEntity findUsersById(Long searchedId);

    void deleteUser(Long id);

    void updateUser(UserEntity userEntity);

}