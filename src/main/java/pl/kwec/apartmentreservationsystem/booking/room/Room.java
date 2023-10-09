package pl.kwec.apartmentreservationsystem.booking.room;

import jakarta.persistence.*;
import lombok.*;
import pl.kwec.apartmentreservationsystem.booking.building.Building;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "room")
public class Room {

    @Id
    private Integer roomNumber;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    private String description;
}
