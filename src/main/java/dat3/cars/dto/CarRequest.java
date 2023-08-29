package dat3.cars.dto;

import dat3.cars.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

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


}
