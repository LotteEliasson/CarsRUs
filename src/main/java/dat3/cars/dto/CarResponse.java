package dat3.cars.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.cars.entity.Car;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor //A must for @Builder
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) //denne annotering der gør man kan udelukke Null værdier - if(includeAll)
public class CarResponse {

    //Hvorfor er de ikke private her?
    int id;
    String brand;
    String model;
    double pricePrDay;

    int bestDiscount;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime created;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime edited;

    //Convert Car Entity to Car DTO
    public CarResponse(Car c, boolean includeAllVarCar){
        this.id = c.getId();
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
        if(includeAllVarCar){
            this.bestDiscount = c.getBestDiscount();
            this.created = c.getCreated();
            this.edited = c.getEdited();
        }
    }

}
