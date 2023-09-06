package dat3.cars.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

//lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//Entity framework
//Mappe frem og tilbage til databasen.
@Entity
public class Car extends AdminDetails{
    //Autogenerer id som primary key i databasen for hver objekt
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "car_brand", length = 50) //unik def for kolonner
    private String brand;
    @Column(name = "car_model", length = 60)
    private String model;
    @Column(name = "rental_price_day")
    private double pricePrDay;
    @Column(name = "max_discount")
    private int bestDiscount;

    //Lagt i en @SuperClass og extendet i hver class der har brug for denne feature
//    @CreationTimestamp
//    private LocalDateTime created;
//    @UpdateTimestamp
//    private LocalDateTime lastEdited;

    //En bil har mange reservationer,
    // men en reservation har kun en bil.
    //Her er fetchType default Lazy, derfor ikke nødvendigt at tilføje i denne oneToMany.
    @OneToMany(mappedBy = "car")
    List<Reservation> reservations;

    public void addReservation(Reservation reservation){
        if(reservations == null){
            reservations = new ArrayList<>();
        }
        reservations.add(reservation);
    }
    //Constructor
    public Car(String brand, String model, double pricePrDay, int bestDiscount) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.bestDiscount = bestDiscount;
    }
}
