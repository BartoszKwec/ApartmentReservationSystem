package pl.kwec.apartmentreservationsystem.booking.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ErrorCompanyResponse {
    private String message;
    private HttpStatus statusCode;
}

