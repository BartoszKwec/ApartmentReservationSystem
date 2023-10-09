package pl.kwec.apartmentreservationsystem.booking.company.model;

import jakarta.persistence.*;
import lombok.*;
import pl.kwec.apartmentreservationsystem.booking.building.Building;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String companyName;

    private String description;
    private String address;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Building> buildings;

}
