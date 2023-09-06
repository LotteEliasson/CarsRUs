package dat3.cars.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

//Entity framework
//Mappe frem og tilbage til databasen.
@Entity
public class Member extends AdminDetails{

    @Id
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;
    private boolean approved;
    private int ranking;

//    @CreationTimestamp
//    private LocalDateTime created;
//    @UpdateTimestamp
//    private LocalDateTime lastEdited;
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
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }
}
