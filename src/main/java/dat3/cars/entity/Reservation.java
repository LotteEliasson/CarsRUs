package dat3.cars.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Reservation extends AdminDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    private LocalDate rentalDate;

    @ManyToOne
    Member member;

    @ManyToOne
    Car car;

    public Reservation(LocalDate rentalDate, Member member, Car car) {
        this.rentalDate = rentalDate;
        this.member = member;
        this.car = car;
        //This er referencen til den reservation man opretter en specifik reservation.
        car.addReservation(this);
        member.addReservation(this);
    }
}
