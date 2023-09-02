package dat3.cars.dto;

import dat3.cars.entity.Car;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CarRequest {

    int id;
    String brand;
    String model;
    double pricePrDay;
    int bestDiscount;

    //Hvorfor gør vi dette??
    public static Car getCarEntity(CarRequest c){
        return new Car(c.getBrand(), c.getModel(), c.getPricePrDay(), c.getBestDiscount());
    }

    public CarRequest(Car c){
            this.id = c.getId();
            this.brand = c.getBrand();
            this.model = c.getModel();
            this.pricePrDay = c.getPricePrDay();
            this.bestDiscount = c.getBestDiscount();
    }



}
