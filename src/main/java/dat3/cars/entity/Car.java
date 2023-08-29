package dat3.cars.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//lombok
@Getter
@Setter
@NoArgsConstructor

//Entity framework
//Mappe frem og tilbage til databasen.
@Entity
public class Car extends AdminDetails{
    //Autogenerer id som pk i databasen for hver objekt
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

//    @CreationTimestamp
//    private LocalDateTime created;
//    @UpdateTimestamp
//    private LocalDateTime lastEdited;

    //Constructor
    public Car(String brand, String model, double pricePrDay, int bestDiscount) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.bestDiscount = bestDiscount;
    }
}
