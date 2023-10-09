package pl.kwec.apartmentreservationsystem.booking.company;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kwec.apartmentreservationsystem.booking.company.model.Company;
import pl.kwec.apartmentreservationsystem.booking.company.model.CompanyRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<?> registerCompany(@RequestBody final CompanyRequest request){
        return companyService.registerCompany(request);
    }

    @GetMapping("/{companyId}")
    public Company getCompanyById (@PathVariable Integer companyId){
        return companyService.getCompanyById(companyId);
    }

    @GetMapping("/exists/{companyName}")
    public ResponseEntity<Boolean> existsByCompanyName(@PathVariable String companyName){
        boolean exists = companyService.existsByCompanyName(companyName);
        return ResponseEntity.ok(exists);
    }
}
