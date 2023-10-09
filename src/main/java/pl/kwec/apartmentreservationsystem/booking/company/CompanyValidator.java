package pl.kwec.apartmentreservationsystem.booking.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyValidator {

    private static final String NAME_OF_COMPANY_IS_ALREADY_USED = "Name of company is already used";
    private final CompanyRepository companyRepository;

    public void validateCompanyName(final String companyName){
        if(companyRepository.existsByCompanyName(companyName)){
            throw new CompanyException(NAME_OF_COMPANY_IS_ALREADY_USED);
        }
    }

}
