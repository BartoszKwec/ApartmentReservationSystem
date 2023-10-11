package pl.kwec.apartmentreservationsystem.authentication.login.model;

import lombok.Data;
import lombok.Builder;


@Builder
@Data
public class LoginResponse {

    private String accessToken;
    @Builder.Default
    private String tokenType = "Bearer ";
}
