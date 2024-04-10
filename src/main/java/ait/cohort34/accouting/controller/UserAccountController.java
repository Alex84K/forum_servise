package ait.cohort34.accouting.controller;

import ait.cohort34.accouting.dto.RolesDto;
import ait.cohort34.accouting.dto.UserDto;
import ait.cohort34.accouting.dto.UserEditDto;
import ait.cohort34.accouting.dto.UserRegisterDto;
import ait.cohort34.accouting.servise.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class UserAccountController  {
    final UserAccountService userAccountService;

    @PostMapping("/register")
    public UserDto register(UserRegisterDto userRegisterDto) {
        return userAccountService.register(userRegisterDto);
    }

    @GetMapping("/user/login")
    public UserDto getUser(@PathVariable String login) {
        return userAccountService.getUser(login);
    }

    @PostMapping("/login")
    public  UserDto login(){
        return null;
        // TO DO
    }


    @DeleteMapping("/user/{login}")
    public UserDto removeUser(@PathVariable String login) {
        return userAccountService.removeUser(login);
    }

    @PutMapping("/user/{login}")
    public UserDto updateUser(@PathVariable String login, @PathVariable UserEditDto userEditDto) {
        return userAccountService.updateUser(login, userEditDto);
    }

    @PutMapping("/user/{login}/role/{role}")
    public RolesDto addRole(String login, String role) {
        return userAccountService.changeRolesList(login, role, true);
    }

    @DeleteMapping("/user/{login}/role/{role}")
    public RolesDto deleteRole(String login, String role) {
        return userAccountService.changeRolesList(login, role, false);
    }
    @PutMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword() {
        // TO DO
    }
}
