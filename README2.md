SVAR TIL ANDEN AFLEVERING.

-What are the benefits of using a RESTful API

REST er stateless, er ikke afhængig af gemt data på servere men alle requests indeholder alle data hver gang et request udføres. Det betyder at man nemmere kan fordele trafikken fra clienterne på flere servere via Loadbalancere, derved kan man undgå overbelastning af den enkelte server.
REST  anvendes til at håndtere data mellem webapplikationer og server, vores GET-,POST-;PUT-;PATCH-;DELETE-metoder vi anvender i vores Controllere er simple og forståelige http metoder der nemme at anvende.
REST kan anvendes sammen med flere andre dataformater såsom JSON,HTML i vores tilfælde.


- What is JSON, and why does JSON fit so well with REST?
JSON anvendes til at sende data mellem web browser og servere, det er let forståeligt tekstbaseret, hvilket gør det nemt at anvende i praksis. Man kan nemt konvertere requests fra client via @RequestBody til Java, der anvendes i vores DTO’er etc i applikationen. Og omvendt konverteres vores Java til JSON i responses til client(her Web browser). 

- How to design simple CRUD endpoints using spring boot and DTOs to separate api from data structure  -> Focus on your use of DTO's
  Tror næste svar dækker over begge DTO spørgsmål. Ellers har jeg bare ikke lige forstået ovenstående korrekt.
-  What is the advantage of using DTOs to separate api from data structure when designing rest endpoints
I Entity klasserne har vi datastrukturen der definerer vores tabeller og koloner i Databasen, hvor DTO definerer hvilke af disse data der skal sendes til brugeren. I Cars'r'Us er det delt op i Request og Respones, for lettere at administrere hvilke data der kræves på hhv forspørgsler og svar i Service laget. Man kan også lettere definerer brugerens adgang ved denne opdeling så den varierer, da man kan tilføje nye Constructorer for ændret adgang til oplysninnger etc.

- Explain shortly the concept mocking in relation to software testing
Ved Mocking isolerer man den kode man ønsker at teste fra afhængigheder, såsom Database, Dependencies etc. Man indsætter Mockdata som konstanter I erstatning for den faktiske data.

- How did you mock database features, either using an in-memory database and/or Mockito
Jeg har anvendt H2 database og den ligger som Dependency i pom.xml filen altså en tilføjelse i Spring Boot, hvilket er en in-memory database, dog lidt langsommere end Mockito, som opretter en faktisk midlertidig database for test.
      

- Explain the concept Build Server and the role Github Actions play here
Byggeserver, er en server der tester koden der bliver Pushed til Github om det er muling at bygge(Build) projektet efter der tilføjes nye elementer. Github Actions er en feature indbygget i Github der udfører den Build test for os, hver gang man tilføjer til det Master. Hvis man vil undgå at projektet konstant fejler og ønsker andre kan følge ens arbejde undervejs, kan man eksempeltvis anvende Branches.

- Explain maven, relevant parts in maven, and how maven is used in our CI setup. Explain where maven is used by your GitHub Actions Script(s)
Maven er vores pom.xml fil, de Dependencies vi tilføjer hertil adminstrere Maven. Derved bliver de features automatiseret i vores applikation, såsom Build, H2, Tests,
  
-  Understand and chose cloud service models (IaaS, PaaS, SaaS, DBaaS)for your projects -> Just explain what you have used for this handin
  Paas og DBaas


