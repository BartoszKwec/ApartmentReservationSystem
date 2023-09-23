package pl.kwec.apartmentreservationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class ApartmentReservationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApartmentReservationSystemApplication.class, args);
    }

}
