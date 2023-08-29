package dat3.cars.api;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.service.CarService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("api/cars")
public class CarController {
    CarService carService;
    public CarController(CarService carService){
        this.carService = carService;
    }


    //Security Admin only
    //Gennem service layer laver man et styret carResponse.
    @GetMapping
    List<CarResponse> getCars(){
        return carService.getCars(false);
    }

    @GetMapping(path = "/{id}")
    CarResponse getCarById(@PathVariable int id) throws Exception{
        return carService.findCarById(id);
    }

    //Security --> anonymous
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CarResponse addCar(@RequestBody CarRequest bodyC){
        return carService.addCar(bodyC);
    }

    //Security ???
    @PutMapping("/{id}")
    ResponseEntity<Boolean> editCar(@RequestBody CarRequest bodyC, @PathVariable int id){
        return carService.editCar(bodyC,id);
    }



}

