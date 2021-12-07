# Security Test Defenders
## Inhoud:
1.	Evaluatiecriteria
    - Evaluatiecriteria wachtwoorden en aanmelden
    - Evaluatiecriteria HTTPS
    - Evaluatiecriteria ivm beveiliging tegen web vulnerabilities
    - conclusie/aanbevelingen
2.	DAST (grey box enumeration/tests)
    - Search Engine Discovery Reconnaissance for Information Leakage
    - Fingerprint web server A RECORD: ehbdefendersblog.com
    - Review webserver meta files for information leakage
    - Applications on Webserver enumeration
    - Webpage Content Information Leakage	
    - Application entry points
    - Application execution paths mapping
    - Application Framework
    - Conclusie/aanbevelingen
3.	SCA (software composition analysis
    - CVE
    - Conclusie/Aanbevelingen
	
## EVALUATIECRITERIA
Deze criteria werden beschreven in de opdracht zoals te raadplegen op cas.ehb.be en hebben als doel de beveiliging van het aanmeldproces en wachtwoordgebruik op de webapplicatie te verbeteren.

### Evaluatiecriteria wachtwoorden en aanmelden
**X** Wachtwoord kan minder dan 8 tekens bevatten. \
**X** Geen verificatielink ontvangen om wachtwoord te bevestigen. Dit maakt het onmogelijk om met deze gebruiker de rest van de functionaliteiten van de site uit te proberen. \
**V** Wachtwoord kan langer dan 64 tekens zijn. \
**V** Verdedigt tegen brute force attacks door middel van tijdsinterval tussen mislukte inlogpogingen te verhogen. \
**V** De gebruiker kan zich afmelden.\
**V** De applicatie geeft duidelijk aan dat de gebruiker al dan niet is aangemeld. \
**X** Na aanmelden kan de gebruikers zijn of haar gegevens opvragen: Niet mogelijk zo lang je de verificatielink niet ontvangen hebt.

### Evaluatiecriteria HTTPS
- Pagina’s enkel beschikbaar over https (301 Moved Permanently).
- Rechtstreekse toegang via IP (104.21.9.158) niet mogelijk.
- ssl labs report grade: A.
- CAA record is beschikbaar.
- Domein staat op hsts preload list.\

### Evaluatiecriteria ivm beveiliging tegen typische web vulnerabilities
- Sessiecookies zijn SameSite: Lax.
- MIME sniffing wordt tegen gegaan dmv X-Content-Type-Options: nosniff.
- Formulieren bevatten CSRF tokens.
- De sessie verloopt na een uur.
- Pogingen tot XSS en SQL injectie mislukken. (voorkomen door character escaping of sanitation).

### Conclusie/Aanbevelingen
1.	**Het verificatieproces na het registreren van een nieuwe gebruiker is nog niet compleet. Dit moet gebeuren alvorens de applicatie verder kan getest worden**
2.	**Controle bij de creatie van een nieuw wachtwoord moet aangepast worden zodat dit controlleert op een minimale lenge van 8 karakters.**

## DAST: GREY BOX ENUMERATION/TESTS
In de eerste fase vertrekt deze test vanuit een black box perspectief (de enige beschikbare informatie is de URL ehbdefendersblog.com) en probeert zoveel mogelijk beschikbare informatie te verzamelen die potentieel een gevaar kunnen vormen voor veiligheid van de webapplicatie.
Gebruikte tools zijn Netcat, Burpsuite, cUrl, wget.
In een tweede fase wordt de webapplicatie gebruikt zoals bedoelt voor een gebruiker. 
Hiervoor krijgt het test team de aanmeldgegevens ter beschikking van een gebruiker zonder speciale rechten. (email: diyej36510@tinydef.com, passwoord: qafbaf-beJboz-serka8)

### Search Engine Discovery Reconnaissance for Information Leakage
-	Geen sporen gevonden

### Fingerprint web server A RECORD: ehbdefendersblog.com
-	IP ADRES: 104.21.9.158
-	DNS SERVER: CLOUDFLARE
-	HOST: Azure (https://ehbdefendersapp.azurewebsites.net)
-	Server OS: Linux 2.4.X|2.6.X, Sony Ericsson embedded (onzeker)

### Review webserver meta files for information leakage
-	Geen sporen gevonden

### Applications on Webserver enumeration
-	Site is ook bereikbaar via https://ehbdefendersapp.azurewebsites.net
-	https://ehbdefendersblog.com:8443/login geeft een 522 response
-	Open poorten: 80, 443, 8080, 8443

### Webpage Content Information Leakage	
-	Wanneer een aangemelde gebruiker op de workspace link klikt komt een debug pagina tevoorschijn waar een deel van de source code te lezen is. 

### Application entry points
-	Entry points aanwezig op
https://ehbdefendersblog.com/register
https://ehbdefendersblog.com/login
- Emailveld controlleert op geldig emailadres formaat
- Emailveld controlleert op spaties
- Teveel login pogingen sluiten de gebruiker tijdelijk uit
- POST request parameters: email, password & token

### Application execution paths mapping
-	Geen sporen gevonden

### Application Framework
-	FRAMEWORK: Laravel / PHP 8.0.13

### Application architecture
-	Server: Azure
-	CDN: Cloudflare
-	Framework: Laravel
-	Database: (mogelijk) MySQL of  PostGreSQL 

### Conclusie/aanbevelingen
1.	**APP_DEBUG op false zodat de debug pagina niet meer zichtbaar is**
3.	**CSFR token kan eventueel als variabele worden meegeven, bijvoorbeeld als volgt: <input type="hidden" name="_token" value="{{ csrf_token() }}"/> in plaats van hard coded in de html te plaatsen.**
4.	**https://ehbdefendersapp.azurewebsites.net eventueel laten redirecten naar https://ehbdefendersblog.com**
5.	**Niet gebruikte poorten op de server te sluiten, of open laten mits gebruik van IP whitelisting.**

## SCA: SOFTWARE COMPOSITION ANALYSIS

### CVE 
Een overzicht van de CVE voor Laravel is beschikbaar op https://www.opencve.io/cve?vendor=laravel.
De laatste (CVE-2021-43617) dateert van 2021-11-18 en heeft een rating van 9.8. Deze vulnerability maakt de applicatie gevoelig voor het opladen van uitvoerbare php content.

### Conclusie/aanbevelingen
1.	Het framework steeds up to date houden met de laatst stabiele versie
2.	Opencve.io opvolgen om zich bewust te zijn van de laatste exploits en van welke vulnerabilities deze gebruik maken.
