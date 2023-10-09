package pl.kwec.apartmentreservationsystem.booking.company.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kwec.apartmentreservationsystem.booking.building.Building;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest{
        private String companyName;
        private String description;
        private String address;
        private List<Building> buildings;

}
