package dat3.cars.service;

import dat3.cars.dto.ReservationRequest;
import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    CarRepository carRepository;
    MemberRepository memberRepository;
    ReservationRepository reservationRepository;

    //Constructor injection
    public ReservationService(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationResponse> getReservations(){

        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationResponse> responses = new ArrayList<>();
        for(Reservation reservation: reservations){
            ReservationResponse res = new ReservationResponse(reservation);
            responses.add(res);
        }
        return responses;
    }

    public ReservationResponse reserveCar(ReservationRequest bodyReservation){
        if(bodyReservation.getDate().isBefore(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date is in the past");
        }
        Member member = memberRepository.findById(bodyReservation.getUserName()).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this username does not exist"));
        Car car = carRepository.findById(bodyReservation.getCarId()).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this id does not exist"));
        if(reservationRepository.existsByCarIdAndRentalDate(bodyReservation.getCarId(), bodyReservation.getDate())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car is reserved on this date");
        }
        Reservation res = new Reservation(bodyReservation.getDate(), member, car);

        return new ReservationResponse(res);
    }


}
