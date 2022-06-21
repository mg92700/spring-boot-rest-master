package com.meteo.services;


import com.meteo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class UsersServiceImpl implements UsersService {

    private Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
    private List<User> users = Stream.of(
            new User("mouhsin", "ghoraf", new BigDecimal(444444), "Oujda"),
            new User("ines", "ghoraf", new BigDecimal(55555), "Oujda"),
            new User("mohamed", "ghoraf", new BigDecimal(88888), "Oujda"),
            new User("hamza", "ghoraf", new BigDecimal(999999), "Oujda"),
            new User("yemna", "ghoraf", new BigDecimal(999999), "Oujda"))
            .collect(Collectors.toList());

    @Override
    public User addUser(User user) {
        users.add(user);
        return user;
    }

    @Override
    public List<User> getListUsers() {
        return users;
    }

    @Override
    public User findUsersById(Long searchedId) {
        return users.stream()
                .filter(x -> searchedId.equals((x.getId())))
                .findAny()
                .orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        User user = findUsersById(id);
        users.remove(user);
    }

    @Override
    public void updateUser(User user) {
        User user1 = findUsersById(user.getId());
        if (user1 != null) {
            user1.setNom(user.getNom());
            user1.setPrenom(user.getPrenom());
            user1.setAdresse(user.getAdresse());
            user1.setSalaire(user.getSalaire());
        }
    }

}