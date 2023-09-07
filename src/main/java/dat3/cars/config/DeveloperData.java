package dat3.cars.config;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.ReservationRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DeveloperData implements ApplicationRunner {

    CarRepository carRepository;
    MemberRepository memberRepository;
    ReservationRepository reservationRepository;


    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
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
        carRepository.saveAll(cars);



        List<Member> members = new ArrayList<>();

        members.add(new Member("john_doe", "securepass1", "john.doe@example.com", "John", "Doe", "123 Main St", "New York", "10001"));
        members.add(new Member("jane_smith", "mypassword123", "jane.smith@example.com", "Jane", "Smith", "456 Elm St", "Los Angeles", "90001"));
        members.add(new Member("bob_jones", "p@ssw0rd", "bob.jones@example.com", "Bob", "Jones", "789 Oak St", "Chicago", "60601"));
        memberRepository.saveAll(members);

        Car car1 = new Car("bil", "bil", 100,10);
        carRepository.save(car1);

        Member member1 = new Member("test", "test", "test,", "test", "test", "test","test","test");
        memberRepository.save(member1);
        LocalDate date1 = LocalDate.of(2023,12,12);
        LocalDate date2 = date1.plusDays(1);
        Reservation r1 = new Reservation(date1,member1, car1);
        Reservation r2 = new Reservation(date2, member1, car1);
        reservationRepository.save(r1);
        reservationRepository.save(r2);

        System.out.println("Cars: " + car1.getReservations().size());
        System.out.println("Members: " + member1.getReservations().size());
        System.out.println("Found: " + reservationRepository.existsByCarIdAndRentalDate(car1.getId(),date1));
        System.out.println("NOT Found: " + reservationRepository.existsByCarIdAndRentalDate(car1.getId(),date1.plusDays(5)));

        setupUserWithRoleUsers();
    }


    @Autowired
    UserWithRolesRepository userWithRolesRepository;

    final String passwordUsedByAll = "test12";

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {

        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user--1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user--2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user--3", passwordUsedByAll, "user3@a.dk");
        UserWithRoles user4 = new UserWithRoles("user--4", passwordUsedByAll, "user4@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        //No Role assigned to user4
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
    }





}
