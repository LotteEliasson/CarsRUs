package dat3.cars.config;

import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DeveloperData implements ApplicationRunner {

    CarRepository carRepository;

    public DeveloperData(CarRepository carRepository){
        this.carRepository = carRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Car> cars = new ArrayList<>();

        cars.add(new Car("Toyota", "Camry", 50.0, 10));
        cars.add(new Car("Honda", "Civic", 45.0, 12));
        cars.add(new Car("Ford", "F-150", 70.0, 8));
        cars.add(new Car("Chevrolet", "Malibu", 55.0, 15));
        cars.add(new Car("Nissan", "Altima", 48.0, 11));
        cars.add(new Car("BMW", "3 Series", 80.0, 20));
        cars.add(new Car("Mercedes-Benz", "E-Class", 85.0, 18));
        cars.add(new Car("Audi", "A4", 75.0, 16));
        cars.add(new Car("Lexus", "RX", 70.0, 14));
        cars.add(new Car("Hyundai", "Elantra", 45.0, 13));
        cars.add(new Car("Kia", "Optima", 52.0, 9));
        cars.add(new Car("Tesla", "Model 3", 90.0, 25));
        cars.add(new Car("Subaru", "Outback", 55.0, 12));
        cars.add(new Car("Mazda", "CX-5", 58.0, 10));
        cars.add(new Car("Jeep", "Wrangler", 75.0, 14));
        cars.add(new Car("Ram", "1500", 70.0, 13));
        cars.add(new Car("GMC", "Sierra", 72.0, 11));
        cars.add(new Car("Volkswagen", "Golf", 48.0, 15));
        cars.add(new Car("Volvo", "XC60", 75.0, 12));
        cars.add(new Car("Acura", "MDX", 65.0, 10));
        cars.add(new Car("Infiniti", "Q50", 68.0, 11));
        cars.add(new Car("Chrysler", "300", 60.0, 14));
        cars.add(new Car("Jaguar", "XF", 85.0, 18));
        cars.add(new Car("Land Rover", "Discovery", 90.0, 20));
        cars.add(new Car("Porsche", "911", 120.0, 25));
        cars.add(new Car("Ferrari", "488 GTB", 250.0, 30));
        cars.add(new Car("Lamborghini", "Aventador", 300.0, 35));
        cars.add(new Car("Bugatti", "Chiron", 400.0, 40));
        cars.add(new Car("McLaren", "720S", 350.0, 38));
        cars.add(new Car("Koenigsegg", "Regera", 450.0, 45));
        cars.add(new Car("Rolls-Royce", "Phantom", 400.0, 42));
        cars.add(new Car("Bentley", "Continental GT", 380.0, 40));
        cars.add(new Car("Maserati", "Ghibli", 280.0, 32));
        cars.add(new Car("Alfa Romeo", "Giulia", 260.0, 30));
        cars.add(new Car("Fiat", "500", 45.0, 10));
        cars.add(new Car("MINI", "Cooper", 55.0, 12));
        cars.add(new Car("Smart", "Fortwo", 40.0, 8));
        cars.add(new Car("Dodge", "Charger", 70.0, 15));
        cars.add(new Car("Buick", "Encore", 50.0, 11));
        cars.add(new Car("Cadillac", "Escalade", 95.0, 18));
        cars.add(new Car("Lincoln", "Navigator", 90.0, 16));
        cars.add(new Car("Chrysler", "Pacifica", 65.0, 14));
        cars.add(new Car("Aston Martin", "DB11", 150.0, 30));
        cars.add(new Car("Lexus", "LS", 110.0, 20));
        cars.add(new Car("Genesis", "G70", 70.0, 12));
        cars.add(new Car("Kia", "Soul", 50.0, 10));
        cars.add(new Car("Hyundai", "Santa Fe", 60.0, 13));

    }
}
