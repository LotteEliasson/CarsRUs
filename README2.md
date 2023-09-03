SVAR TIL ANDEN AFLEVERING.
-What are the benefits of using a RESTful API
REST er stateless, er ikke afhængig af gemt data på servere men alle requests indeholder alle data hver gang et request udføres. Det betyder at man nemmere kan fordele trafikken fra clienterne på flere servere via Loadbalancere, derved kan man undgå overbelastning af den enkelte server.
REST  anvendes til at håndtere data mellem webapplikationer og server, vores GET-,POST-;PUT-;PATCH-;DELETE-metoder vi anvender i vores Controllere er simple og forståelige http metoder der nemme at anvende.
REST kan anvendes sammen med flere andre dataformater såsom JSON,HTML i vores tilfælde.


- What is JSON, and why does JSON fit so well with REST?
JSON anvendes til at sende data mellem web browser og servere, det er let forståeligt tekstbaseret, hvilket gør det nemt at anvende i praksis. Man kan nemt konvertere requests fra client via @RequestBody til Java, der anvendes i vores DTO’er etc i applikationen. Og omvendt konverteres vores Java til JSON i responses til client(her Web browser). 

- How to design simple CRUD endpoints using spring boot and DTOs to separate api from data structure  -> Focus on your use of DTO's

  
-  What is the advantage of using DTOs to separate api from data structure when designing rest endpoints


- Explain shortly the concept mocking in relation to software testing
Ved Mocking isolerer man den kode man ønsker at teste fra afhængigheder, såsom Database, Dependencies etc. Man indsætter Mockdata som konstanter I erstatning for den faktiske data.

- How did you mock database features, either using an in-memory database and/or Mockito
Her vis den kode der laves for cars, når de tests er lavet @beforeEach…..


- Explain the concept Build Server and the role Github Actions play here
Byggeserver
- Explain maven, relevant parts in maven, and how maven is used in our CI setup. Explain where maven is used by your GitHub Actions Script(s)

  
-  Understand and chose cloud service models (IaaS, PaaS, SaaS, DBaaS)for your projects -> Just explain what you have used for this handin


