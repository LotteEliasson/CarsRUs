# CarsRUs
[![Build and deploy JAR app to Azure Web App - car-lfe](https://github.com/LotteEliasson/CarsRUs/actions/workflows/master_car-lfe.yml/badge.svg)](https://github.com/LotteEliasson/CarsRUs/actions/workflows/master_car-lfe.yml)

Svar til første aflevering.


The idea with, and reasons for why to use, a ORM-mapper
-	Håndterer data mellem OOP i Spring Boot og databasen, ORM simplificerer kommunikationen og man undgår en masse kode JDBC samt SQL. 
The meaning of the terms JPA, Hibernate and Spring Data JPA and how they are connected
-	JPA, Java Persistance API. Måden hvorpå OOP og relationel database interagerer (Mapped), inkluderer ORM Object Relational Mapping.
-	Hibernate, Framework der anvendes til at adgang til databaseen fra applikationen.
-	Spring Data JPA, En del af Spring Boot hvorpå man på en simpel måde kan få adgang til datalaget. Gennem Interface repository samt annoteringer, såsom @Entity. 

How to create simple Java entities and map them to a database via the Spring Data API
   How you did that in your code
            //Mapper frem og tilbage til databasen.
            @Entity
            public class Car {
            -	Spring Data JPA vil via annoteringen @Entity sørge for Mappingen fra Car klasse og databasen

How to control the mapping between individual fields in an Entity class and their matching columns in the database
   How you did that in your code

            @Column(name = "car_brand", length = 50) //unik def for kolonner
            private String brand;
            @Column(name = "car_model", length = 60)
            private String model;
            @Column(name = "rental_price_day")
            private double pricePrDay;
            @Column(name = "max_discount")
            private int bestDiscount;

How to auto generate IDs, and how to ensure we are using a specific database's preferred way of doing it (Auto Increment in our case for  MySQL)
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            int id;

How to use and define repositories and relevant query methods using Spring Data JPAs repository pattern
   How you did that in your code
            Package repository  - Interface CarRepository:
            public interface CarRepository extends JpaRepository<Car, Integer> {
            }

            Package config – Class DeveloperData:
            CarRepository carRepository;
            
            public DeveloperData(CarRepository carRepository){
                this.carRepository = carRepository;
            }

How to write simple "integration" tests, using H2 as a mock-database instead of MySQL
   
      Ctrl+shift+t inkl setUp@/Before på respektive class, Interface etc.

Herunder tester jeg CarRepository ved at injection af CarRepository via annotationen @Autowired
      @Autowired
      CarRepository carRepository;

Sletter evt data og opretter dataset før hver test via h2 som en in memory database, hurtig og nem løsning til test.

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


Test der tester om CarRepository er i stand til at tælle de oprettede objekter i databasen, returneres de tre midlertidige objekter I databasen oprettet i @BeforeEach er det true. 

Forventer(assertEquals) tre, er det sandt går testen igennem.

         @Test
             public void countAll(){
                 long count = carRepository.count();
                 assertEquals(3, count);
             }
         }


How to add (dev) connection details for you local MySQL database
   How you did that
-	Tilføjer Miljøvariabler I Configuration, samt tilsvarende i application.properties.
      Enviomental Variables:
 	          #JDBC_DATABASE_URL=jdbc:mysql://localhost:3306/cars;JDBC_USERNAME=root;JDBC_PASSWORD=Skolekode1
 	    application.properties:
 	         #Kriterier for at kunne logge på databasen
            #Miljøvariable 'gemt' i Configurations øger sikkerheden
            spring.datasource.url=${JDBC_DATABASE_URL}
            spring.datasource.username=${JDBC_USERNAME}
            spring.datasource.password=${JDBC_PASSWORD}
