package pl.kwec.apartmentreservationsystem.booking.building;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuildingService {

    private final BuildingRepository buildingRepository;

    public Building saveBuilding(final Building building){
        return buildingRepository.save(building);
    }
}
