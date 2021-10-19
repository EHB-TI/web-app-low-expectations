# Goal
*In verschillende steden en dorpen bestaan er diensten waar je allerlei zaken kan lenen. Deze diensten worden vaak gebruikt door maar beperken zich niet tot scholen, scoutsverenigingen en jeugddiensten.  
Voorbeelden van zaken die kunnen uitgeleend worden zijn borden, bestek, discolampen, etc. voor feestjes, kampen en andere gelegenheden. 
Dit gebeurd vaak vrij onorthodox en ongeorganiseerd. Personen komen vaak binnen, kiezen dan wat ze willen en de fysiek aanwezige persoon moet dan manueel de zaken ingeven in een Excel bestandje. Men kan ook bellen naar deze dienst als men zich bewust is van de reeds aanwezige voorwerpen. Niet erg handig dus, maar wel eenvoudig te digitaliseren!*

# Goal criteria
*Dat we door het gebruik van de applicatie een duidelijk overzicht kan bemachtigen (van zowel de aanwezige voorwerpen, als de reservaties) alsook de fouten tolerantie dus enorm naar beneden brengen. Wanneer de mensen achter de geleende voorwerpen komen kunnen we een automatisch document creëren die kan worden uitgeprint en ondertekent alsook kan verstuurd worden via mail ter bevestiging.*
 
# Acceptance criteria
*Tonen we aan dat we de ontleningsapplicatie effectief kan gebruikt worden om allerlei zaken te kunnen ontlenen. De fysieke persoon hoeft niet meer aanwezig zijn om reservaties te kunnen maken. 
Er kan succesvol worden teruggevallen op de genereerde documenten tijdens afhaling om betwistingen.*

# Threat model
(zie pdf voor images)
## Data Flows 
We bouwen een webapplicatie waarop materiaal kan gereserveerd worden. Deze applicatie bestaat uit de website die een klantvriendelijke interface voorziet, een database die al het beschikbare alsook het uitgeleende materiaal bijhoudt, en een API die de brug vormt tussen de website en de database. Hiervoor worden standaard create, read, update en delete requests gebruikt tussen client, website en api. 

## Architectuur 

Er wordt gebruik gemaakt van de AWS infrastructuur. (zie onderstaande figuur) 
DNS resolutie gebeurt via Route 53, die de communicatie doorstuurt naar Cloudfront (Caching, DDOS bescherming).  
Cloudfront stuurt de requests door naar een Elastic Load Balancer (High availability, SSL termination) die deze verdeelt over twee verschillende EC2 instanties (= virtuele machines waar de website draait op ASP.NET).  
Eventuele authenticatie gebeurt met Cognito(Social sign in/eventueel met multi factor authentication). 
De requests worden via een andere Load Balancer verdeeld onder twee EC2 instanties waar de API op draait. 
Data wordt opgeslaan op DynamoDB (High availability, scalability). 
Tot slot worden er access logs opgeslaan dankzij Cloudtrail, en gebeuren er continu health checks op de EC2 instanties dankzij de Load Balancer. 

## Wat kan er mis lopen & hoe wordt het opgevangen? 

### SPOOFING 
De site is enkel bereikbaar via https. Het verkeer is dus geëncrypteerd. Verder maken we gebruik van Cognito. Cognito voorziet bij aanmelden een Json Web Token als signatuur.  

### TAMPERING
Zoals hierboven vermeld is de site enkel bereikbaar via https, en wordt bij aanmelden een JWT aangemaakt als signatuur. 
Binnen de AWS infrastructuur is alle verkeer gefilterd op IP adres (Een EC2 instantie aanvaardt bijvoorbeeld enkel communicatie van het IP adres van zijn Load Balancer). 
 
### REPUDIATION
Cloudtrail slaat alle requests op in access logs. 
 
### INFORMATION DISCLOSURE
 
### DENIAL OF SERVICE
Cloudfront heeft ingebouwde bescherming tegen DOS aanvallen. Indien deze toch zou falen, kan de aanval verdeeld worden over de Load Balancers. 
 
### PRIVILEGE ESCALATION
De AWS root account is beveiligd met MFA en wordt in principe nooit gebruikt, enkel User Accounts worden gebruikt om AWS services te beheren. 

# Deployment
*Aan te vullen*
