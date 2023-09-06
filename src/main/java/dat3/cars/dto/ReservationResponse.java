package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.cars.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReservationResponse {

    //Lav på sigt hvilke værdier man ønsker at returnere hvis reservationen gik godt.
    int id;
    int carId;
    String brand;
    String model;

    @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    LocalDate reservationDate;

    public ReservationResponse(Reservation reservation) {
        this.id = reservation.getId();
        this.carId = reservation.getCar().getId();
        this.brand = reservation.getCar().getBrand();
        this.model = reservation.getCar().getModel();
        this.reservationDate = reservation.getRentalDate();
    }
}
