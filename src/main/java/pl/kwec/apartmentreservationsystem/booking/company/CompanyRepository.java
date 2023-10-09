package pl.kwec.apartmentreservationsystem.booking.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kwec.apartmentreservationsystem.booking.company.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {


    boolean existsByCompanyName(String companyName);

    @Override
    boolean existsById(Integer integer);
}
