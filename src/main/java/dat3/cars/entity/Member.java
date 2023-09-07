package dat3.cars.entity;

import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
//Entity framework
//Mappe frem og tilbage til databasen.
//extends UserWithRoles ligger Member og UserWithRoles tabeller sammen i DB.
@Entity
public class Member extends UserWithRoles {

    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;
    private boolean approved;
    private int ranking;


    //List anvendes kun fordi der ikke er rigtig mange members. Ellers vil man nøjes med de manyToOne relationer i Reservation klasse.
    //Listen er ikke nødvendig for at reservationer inkl member og car oprettes i databasetabel Reservations.
    @OneToMany(mappedBy = "member")
    List<Reservation> reservations = new ArrayList<>();

    //Metode for at tilføje members til ovenstående List. Til OOP (ønsker man at anvende listerne til visuelt at fremvisning evt i browser).
    // Hvor Databasen anvender Foreign key og primary key til at connecte samme forbindelser. Derved kan man via en query til at kalde lister via "linkede" tabeller.
    public void addReservation(Reservation reservation){
        if(reservations == null){
            reservations = new ArrayList<>();
        }
        reservations.add(reservation);
    }

    public Member(String username, String password, String email, String firstName,
                  String lastName, String street, String city, String zip) {
        super(username, password,email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }
}
