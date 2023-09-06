package dat3.cars.api;

import dat3.cars.dto.ReservationRequest;
import dat3.cars.dto.ReservationResponse;
import dat3.cars.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    List<ReservationResponse> getReservations(){
        return reservationService.getReservations();
    }



    //@Requestbody er JSON ReservationResponse er det vi matcher på
    @PostMapping
    ReservationResponse makeReservation(@RequestBody ReservationRequest request){
       ReservationResponse response = reservationService.reserveCar(request);
       return response;
    }
}
