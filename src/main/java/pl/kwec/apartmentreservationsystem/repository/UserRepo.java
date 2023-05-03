package pl.kwec.apartmentreservationsystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kwec.apartmentreservationsystem.model.User;

@Repository
public interface UserRepo extends CrudRepository<User,Long> {
}
