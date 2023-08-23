package dat3.cars.repository;

import dat3.cars.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    boolean isInitialized = false;

    @BeforeEach
    void setUp() {
        if(!isInitialized){
            carRepository.deleteAll();
            carRepository.save(new Car("Tesla", "Model 3", 90.0, 25));
            carRepository.save(new Car("Subaru", "Outback", 55.0, 12));
            carRepository.save(new Car("Infiniti", "Q50", 68.0, 11));
            isInitialized = true;

        }
    }

    @Test
    public void countAll(){
        long count = carRepository.count();
        assertEquals(3, count);
    }
}