package pl.kwec.apartmentreservationsystem.booking.company;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.kwec.apartmentreservationsystem.booking.company.model.Company;
import pl.kwec.apartmentreservationsystem.booking.company.model.CompanyRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    public static final String COMAPNY_SUCCESSFUL_CREATED = "Company successful created";
    public static final String COMPANY_NOT_FOUND_MESSAGE = "Company not found";
    private final CompanyRepository companyRepository;
    private final CompanyValidator companyValidator;


    public ResponseEntity<?> registerCompany(final CompanyRequest request){
        try{
            companyValidator.validateCompanyName(request.getCompanyName());
            final Company company = createCompany(request);
            saveCompany(company);

            return new ResponseEntity<>(COMAPNY_SUCCESSFUL_CREATED, HttpStatus.OK);
        }catch(CompanyException companyException){
            final ErrorCompanyResponse errorCompanyResponse = ErrorCompanyResponse.
                    builder()
                    .message(companyException.getMessage())
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .build();

            return new ResponseEntity<>(errorCompanyResponse,HttpStatus.BAD_REQUEST);
        }
    }

    public Company createCompany(final CompanyRequest request) {
        return Company.builder()
                .companyName(request.getCompanyName())
                .address(request.getAddress())
                .description(request.getDescription())
                .buildings(request.getBuildings())
                .build();

    }

    public Company saveCompany(final Company company) {
        return companyRepository.save(company);
    }

    public Company getCompanyById(final Integer companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyException(COMPANY_NOT_FOUND_MESSAGE));
    }

    public boolean existsByCompanyName(final String companyName) {
        return companyRepository.existsByCompanyName(companyName);
    }

    public boolean existByCompanyId(final Integer companyId) {
        return companyRepository.existsById(companyId);
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }
}
