package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarServiceTest {
    @Autowired
    CarRepository carRepository;
    CarService carService;

    Car c1, c2, c3;
    boolean isInitialized = false;
    @BeforeEach
    void setUp() {
        if(!isInitialized)
            carRepository.deleteAll();
            c1 = carRepository.save(new Car("brand1", "model1", 10.0, 1));
            c2 = carRepository.save(new Car("brand2", "model2", 20.0, 2));
            carService = new CarService(carRepository);
            isInitialized = true;

    }

    @Test
    void testGetCarAllDetails() {
        List<CarResponse> carResponses = carService.getCars(true);
        assertEquals(2, carResponses.size(), "2 members");
        LocalDateTime time = carResponses.get(0).getCreated();
        assertNotNull(time, "Exp dates to set when true" );
    }

    @Test
    void testGetMembersNoDetails() {
        List<CarResponse> carResponse = carService.getCars(false);
        assertEquals(2, carResponse.size(), "Expect list of 2 cars");
        //Tester at de forventede 2 biler ikke inkluderer de detaljer i includeAllCars, da sat til false.
        LocalDateTime time = carResponse.get(1).getCreated();
        assertNull(time, "Exp no dates when false");
    }


    @Test
    void testEditCar_ExistingId(){
        CarRequest request = new CarRequest(c2);
        request.setBrand("newBrand");
        request.setBestDiscount(1000);

       carService.editCar(request, c2.getId());

        assertEquals("newBrand", request.getBrand());
        assertEquals(1000, request.getBestDiscount());

    }

    @Test
    void testEditCar_NonExistingId(){
        CarRequest request = new CarRequest();
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()-> carService.editCar(request, 45 ));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testAddCar(){
        c3 = carRepository.save(new Car("brand3", "model3", 30.0, 3));
        carService = new CarService(carRepository);
        assertEquals("model3", c3.getModel());
        assertEquals(30, c3.getPricePrDay());
    }

    @Test
    void testAddCar_ExistingId(){
        CarRequest request = new CarRequest(c1.getId(),"brand2", "model2", 20.0, 2);
        carService = new CarService(carRepository);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()-> carService.addCar(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }


    @Test
    void testFindByIdFound() {
        CarResponse response = carService.findCarById(c1.getId());
        assertEquals("model1",response.getModel());
        assertEquals(10.0, response.getPricePrDay());


    }
    @Test
    void testFindByIdNotFound() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()-> carService.findCarById(45));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }




}