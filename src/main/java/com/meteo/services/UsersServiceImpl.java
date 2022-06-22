package com.meteo.services;


import com.meteo.model.UserEntity;
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
    private List<UserEntity> userEntities = Stream.of(
            new UserEntity("mouhsin", "ghoraf", new BigDecimal(444444), "Oujda"),
            new UserEntity("ines", "ghoraf", new BigDecimal(55555), "Oujda"),
            new UserEntity("mohamed", "ghoraf", new BigDecimal(88888), "Oujda"),
            new UserEntity("hamza", "ghoraf", new BigDecimal(999999), "Oujda"),
            new UserEntity("yemna", "ghoraf", new BigDecimal(999999), "Oujda"))
            .collect(Collectors.toList());

    @Override
    public UserEntity addUser(UserEntity userEntity) {
        userEntities.add(userEntity);
        return userEntity;
    }

    @Override
    public List<UserEntity> getListUsers() {
        return userEntities;
    }

    @Override
    public UserEntity findUsersById(Long searchedId) {
        return userEntities.stream()
                .filter(x -> searchedId.equals((x.getId())))
                .findAny()
                .orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity userEntity = findUsersById(id);
        userEntities.remove(userEntity);
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        UserEntity userEntity1 = findUsersById(userEntity.getId());
        if (userEntity1 != null) {
            userEntity1.setNom(userEntity.getNom());
            userEntity1.setPrenom(userEntity.getPrenom());
            userEntity1.setAdresse(userEntity.getAdresse());
            userEntity1.setSalaire(userEntity.getSalaire());
        }
    }

}