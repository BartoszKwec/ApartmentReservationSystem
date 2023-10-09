package pl.kwec.apartmentreservationsystem.booking.building;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kwec.apartmentreservationsystem.booking.company.CompanyRepository;

@Service
@RequiredArgsConstructor
public class BuildingValidator {

    private final CompanyRepository companyRepository;
}
