package ait.cohort34.accouting.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRegisterDto {
    String login;
    String password;
    String firstName;
    String lastName;
}