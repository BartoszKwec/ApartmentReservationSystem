package pl.kwec.apartmentreservationsystem.authentication.login.model;

import lombok.Data;

@Data
public class LoginRequest {

    private String login;
    private String password;
}