package pl.kwec.apartmentreservationsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private String surname;

    private String mail;

    public User(){
    }

    public User(Long id, String name, String surname, String mail){
        this.id=id;
        this.mail=mail;
        this.name=name;
        this.surname=surname;
    }

}
