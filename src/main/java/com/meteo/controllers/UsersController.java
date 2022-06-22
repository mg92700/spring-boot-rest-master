package com.meteo.controllers;

import com.meteo.model.UserEntity;
import com.meteo.repository.UserRepository;
import com.meteo.services.UsersService;
import com.meteo.utils.ExceptionResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Api(value = "gestion des users", description = "Operations pour la gestion des users")
public class UsersController {

    private Logger logger = LoggerFactory.getLogger(UsersController.class);
    private final UsersService usersService;

    @Autowired
    private UserRepository userRepository;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @ApiOperation(value = "add a new users")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created user")}
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addUsers(@Valid @RequestBody UserEntity userEntity) {
        usersService.addUser(userEntity);
        return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
    }

    @ApiOperation(value = "View a list of available user", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping
    public ResponseEntity getUsers() {
        List<UserEntity> userEntities = usersService.getListUsers();
        if (userEntities != null) {
            logger.info("list of users:" + userEntities);
            return new ResponseEntity<>(userEntities, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @GetMapping(path = "/all")
    public @ResponseBody Iterable < UserEntity > getAllUsers() {
        return userRepository.findAll();
    }

    @ApiOperation(value = "find a user by its id", response = UserEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping("/{id}")
    public ResponseEntity getUsersById(@PathVariable("id") long id) {
        UserEntity userEntity = usersService.findUsersById(id);
        if (userEntity != null) {
            logger.info("user:" + userEntity);
            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        }
        throw new ExceptionResponse();
    }

    @ApiOperation(value = "update a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated user"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")}
    )
    @PutMapping
    public ResponseEntity updateUsers(@RequestBody UserEntity userEntity) {
        if (usersService.findUsersById(userEntity.getId()) != null) {
            logger.info("user:" + userEntity);
            usersService.updateUser(userEntity);
            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        }
        throw new ExceptionResponse();
    }

    @ApiOperation(value = "delete a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted user"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsers(@PathVariable("id") Long id) {
        UserEntity userEntity = usersService.findUsersById(id);
        if (userEntity != null) {
            usersService.deleteUser(id);
            logger.info("Deleted:");
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
        //   throw new ResourceNotFoundException();
    }
}