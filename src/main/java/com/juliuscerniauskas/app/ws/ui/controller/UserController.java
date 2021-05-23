package com.juliuscerniauskas.app.ws.ui.controller;

import com.juliuscerniauskas.app.ws.feature.VcatchFeatures;
import com.juliuscerniauskas.app.ws.feature.impl.v2.VCatchEnumFeatures;
import com.juliuscerniauskas.app.ws.service.UserService;
import com.juliuscerniauskas.app.ws.ui.model.request.UserDetailsRequestModel;
import com.juliuscerniauskas.app.ws.ui.model.response.UserResponse;
import com.juliuscerniauskas.app.ws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUser() {
        return "getUser was called";
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) {
        UserResponse returnValue = new UserResponse();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);

        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }

    @PutMapping
    public String updateUser() {
        System.out.println(VCatchEnumFeatures.FEATURE_ONE.isActive());
        System.out.println(VCatchEnumFeatures.FEATURE_TWO.isActive());
//        System.out.println(VcatchFeatures.FEATURE_ONE.isActive());
        return "updateUser was called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "deleteUser was called";
    }

}
