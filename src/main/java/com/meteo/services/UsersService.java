package com.meteo.services;


import com.meteo.model.User;

import java.util.List;

public interface UsersService {

    User addUser(User user);

    List<User> getListUsers();

    User findUsersById(Long searchedId);

    void deleteUser(Long id);

    void updateUser(User user);

}