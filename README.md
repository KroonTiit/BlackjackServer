# BlackjackServer
Cybernetica proovitöö

Server töötab Springbooti põhjal ning pakub RESTful teenust blackjacki mängimiseks. Andmeid hoitakse hsqldb andmebaasis mälus. Salvestatakse ainult kasutaja nime ning konto seisu. 

Compileeritud war fail asub /target kaustikus ning on testitud töötama Tomcat 8.5 peale.
Client-iga suhtlemisesks kasutatakse Json vormingut.
Server kasutab lokaalmasina porti 8080.
