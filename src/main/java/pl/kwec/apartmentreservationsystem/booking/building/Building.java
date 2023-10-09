package pl.kwec.apartmentreservationsystem.booking.building;

import jakarta.persistence.*;
import lombok.*;
import pl.kwec.apartmentreservationsystem.booking.company.model.Company;
import pl.kwec.apartmentreservationsystem.booking.room.Room;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "building")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @Column(nullable = false)
    private String buildingName;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Room> rooms;
}
