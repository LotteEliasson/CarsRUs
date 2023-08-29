package dat3.cars.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

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
