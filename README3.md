NB!!!!
Jeg ved godt min byggeerver fejler, efter der blev lagt Security ind og members blev defineret ud fra USerWithRole, passer mine test i MemberTEst ikke længere, men jeg har ikke haft overskud til at se på dette også.
SAmtidig uploader jeg måske senere søndag nogle flere test i forhold til REpository og Service, måske får jeg også rettet fejlene til MemberTEsts........


- Where and why you have used a @OneToMany annotation
- 

  
@OneToMany er anvendt I Car og i Member Entity, da en Car hhv. en Member kan have mange Reservations. MappetBy referere til at Car/Member har en relationtion til en Entity klasse her Reservations, hvori de fremkommer som foreign keys. Derved kan man gennem objektet reservations lave en liste af Car hhv Members der har en tilknytning til en eller flere reservationer.
List<Reservation> reservations = new ArrayList<>();
 
- Where an why you have used a @ManyToOne annotation

  
@ManyToOne anvendes I Reservation Entity, for at definere at mange reservationer kan have tilknyttet en specifik Car hhv Member. 

- The purpose of the CascadeType, FetchType and mappedBy attributes you can use with one-to-many

  
Jeg har ikke helt fanget CascadeType.
FetchType anvendes for at håndtere lister, default er LAZY, hvilket betyder kun de adspurgte data hentes fra databasen, hvorimod EAGER henter alt fra en liste hver gang. Har man en liste med mange data tusinder eller millioner øges performance ved at anvende LAZY, da men ikke skal hente millioner af data fra databasen for eksempelvis at finde en specifik person. Eager kan bruges ved mindre lister og hvor man ønsker at udelade queries for at hente data, da Listen automatisk hentes når relationten bliver kaldt. Jeg har kun anvendt LAZY som default I min Car’R’Us.
MappedBy er forklaret i forb med @OneToMany.

- How/where you have (if done) added user defined queries to you repositories

  
Defined Queries er primært anvendt i CarRepositoriet hvor der laves specifikke kald til databasen for at frembringe lister af div, såsom Car med specifik Brand samt Model etc.
List<Car> findByBrandAndModel(String brand, String model);
List<Car> findByReservationsEmpty();

@Query("SELECT c FROM Car c WHERE c.bestDiscount = (SELECT MAX(c2.bestDiscount) FROM Car c2)")
    List<Car> findCarsWithHighestDiscount();

- a few words, explaining what you had to do on your Azure App Service in order to make your Spring Boot App connect to your Azure MySqlDatabase

  
Der er jeg lidt i tvivl om hvad menes…..
Jeg har en Databasen på Azures server hvori jeg har nogle definitioner på hostname, username og password. Disse anvendes i min Spring Boot App, hvori jeg programmere min Backend, jeg logger på den respektive Database via Enviroment Variables. Maven har en automatisk funktion der pakker min Backend i en .jar fil, som så igen anvendes af min Azure Web App for at køre min Backend. Sådan ca husker jeg det. Men mig bekendt gør er der ingen direkte def i Azure Web App der connect’er til min Databasee, den er en selvstændig enhed og kan anvendes i flere applikationer uafhængigt af hinanden. 


- a few words about where you have used inheritance in your project, and how it's reflected in your database

  
Det er anvendt I forbindelse med Security, hvor username, password samt email er defineret i UserWithRole og Member Entity er extended med UserWithRole, samt kaldt som en Superclass i Contructor for Member.
Databasen har ikke længere en selvstændig member tabel, men members er nu implimenteret i user_with_role i stedet.

- What are the pros & cons of using the Single Table Strategy for inheritance?

  
Det ved jeg ikke umiddelbart…….

- how are passwords stored in the database with the changes suggested in part-6 of the exercise

  
Det fangede jeg heller ikke, troede som andre at det blev krypteret via en encoder, men det hørte jeg ikke var tillfældet. Så pas……..

