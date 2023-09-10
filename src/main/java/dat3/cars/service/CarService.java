package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CarService {

    CarRepository carRepository;

    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public List<CarResponse> getCars (boolean includeAllCars){

        List<Car> cars = carRepository.findAll();
        List<CarResponse> responseCar = new ArrayList<>();
        for(Car car: cars){
            CarResponse cr = new CarResponse(car, includeAllCars);
            responseCar.add(cr);
        }
        return responseCar;
    }

    public CarResponse addCar(CarRequest bodyCar){

        if(carRepository.existsById(bodyCar.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This Car already exists");
        }
        Car newCar = CarRequest.getCarEntity(bodyCar);
        newCar = carRepository.save(newCar);

        return new CarResponse(newCar, true);
    }

    //ResponseEntity er en klasse inkl metoder der anvendes - org.springframework.http.HttpEntity<T>
    // ()-> en Lambda funktion der ikke tager en parameter med.
    public ResponseEntity<Boolean> editCar(CarRequest bodyCar, int id) {

        Car car = carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "CAr with id does not exist"));
        if(!Objects.equals(bodyCar.getId(), id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot change id");
        }
        car.setBrand(bodyCar.getBrand());
        car.setModel(bodyCar.getModel());
        car.setPricePrDay(bodyCar.getPricePrDay());
        car.setBestDiscount(bodyCar.getBestDiscount());
        return ResponseEntity.ok(true);
    }

    public CarResponse findCarById(int id){
        Car car = carRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this id does not exist"));
        return new CarResponse(car, true);
    }

    public List<CarResponse> getCarsByBrandAndModel(String brand, String model){
        List<Car> cars = carRepository.findByBrandAndModel(brand,model);
        List<CarResponse> responses = cars.stream().map((car -> new CarResponse(car, false))).toList();
        return responses;
    }

    public List<CarResponse> getCarsWithoutReservation(){
        List<Car> cars = carRepository.findByReservationsEmpty();
        List<CarResponse> responses = cars.stream().map((car -> new CarResponse(car, false))).toList();
        return responses;
    }

    public List<CarResponse> getCarWithBestDiscount(){
        List<Car> cars = carRepository.findCarsWithHighestDiscount();
        List<CarResponse> responses = cars.stream().map((car -> new CarResponse(car, true))).toList();
        return responses;
    }

//Mangler deletebyid se Lars løsning
}
