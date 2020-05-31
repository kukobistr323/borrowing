package com.borrowing.user.web;

import com.borrowing.user.model.User;
import com.borrowing.user.model.UserDto;
import com.borrowing.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Vlad Kukoba
 */
@RestController
@RequestMapping(UserController.PATH_MAIN)
public class UserController {

    public static final String PATH_MAIN = "/users";

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<User> getUsers() {
        return modelMapper.map(userService.getAllUsers(), new TypeToken<List<UserDto>>() {
        }.getType());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody UserDto createUser(@RequestBody UserDto userDto) {
        return modelMapper.map(userService.createUser(modelMapper.map(userDto, User.class)), UserDto.class);
    }
}
