
# 1.	Evaluatie van business logica
### Als gewone gebruiker
In eerste instantie hebben we de verschillende user stories, beschreven in de Readme.md, nagekeken op 9/12/2021 vanuit het standpunt van een gewone gebruiker. Dit geeft ons direct een feeling met de website en is een eerste vorm van black box testing.
* Een lijst van beschikbare items opvragen -> OK
* Een lijst van beschikbare items filteren -> OK
* Beschikbare items reserveren -> dit proces werkt. Toch zijn er enkele aandachtspunten/aanbevelingen:
  * Om de goederen te reserveren, kan ik geen hoeveelheid hoger dan 5 selecteren.
  * We hebben een icoontje met een hartje naast het item. Ik zou verwachten dat ik dit gereedschap kan ‘liken’. Dit is niet het geval. Misschien best om het icoontje te verwijderen als je er toch geen functionaliteit aan koppelt.
  * Reservaties komen in het winkelmandje terecht en van daaruit kan ik de goederen ontlenen. Wanneer ik echter in mijn account kijk naar de items die ik net heb ontleend, vind ik daar niets terug (bevinding op 9/12/2021). Deze pagina moet dan ook worden opgefrist. 

### Als beheerder
We hebben een login gekregen met de rol van admin. Ook hier hebben we blackbox testing toegepast om de user stories te testen:
* rollen toekennen aan/afnemen van gebruikers -> ik vind geen pagina waar ik de gebruikers kan zien of waar ik de rollen kan aanpassen (bevinding op 11/12/2021). 
* een reservatie aanpassen/annuleren -> OK op 11/12/2021
* items toevoegen/wijzigen/verwijderen -> toevoegen en aanpassen lukken, maar verwijderen lijkt niet mogelijk. Ik vind geen knoppen om een delete-operatie uit te voeren (bevinding op 11/12/2021).
* categorieën toevoegen/categorieën wijzigen -> creatie lukt, maar de aangemaakt categorie zie ik niet verschijnen in de lijst van categorieën (bevinding op 11/12/2021). Wijzigen lukt.
* een magazijn toevoegen/wijzigen/verwijderen -> toevoegen en aanpassen lukken, maar verwijderen lijkt niet mogelijk. Ik vind geen knoppen om een delete-operatie uit te voeren (bevinding op 11/12/2021).
* magazijncontacten/verantwoordelijken toevoegen/wijzigen -> Ik vind geen knoppen om deze objecten te beheren (bevinding op 11/12/2021).

Aanbeveling: Om de admin-rol volledig tot zijn recht te laten komen, dienen nog enkele extra pagina’s toegevoegd te worden. Zeker het beheer van gebruikers lijkt hier de prioriteit.


# 2.	Evaluatie van sign-up, sign-in en account control
Wachtwoord- en toegangsbeheer (“identity management”) is overgelaten aan de AWS service “Amazon Cognito”. De setup van user sign-up, sign-in en authenticatie wordt daarmee vereenvoudigd, maar dankzij deze service wordt de lat ook direct hoog gelegd. 

## Account registreren
Op de web pagina kan ik me registreren als gewone gebruiker en dien ik een formulier in te vullen. Eén van de elementen is een wachtwoord. Een wachtwoord moet minstens 8 karakters hebben waarvan minstens een kleine letter, een hoofdletter, een speciaal karakter en een nummer. Aan dit criterium is dus voldaan. Als ik een paswoord wens te kiezen die mijn Google account of Apple account voorstelt, kan ik die eveneens aanvaarden en die is langer dan 64 karakters.

Na sign-up wordt een pin code gevraagd ter bevestiging van de account. De pincode wordt onmiddellijk na registratie gestuurd naar mijn e-mailadres. Ook dat evaluatiecriterium kan afgevinkt worden. Na invoer van de pincode kreeg ik jammer genoeg een foutboodschap. Invoer van de pincode leidt me naar de web pagina van de user account die mijn inziens nog niet afgewerkt is. Zie screenshot hieronder van 9/12/2021:
![ErrorAccount](https://user-images.githubusercontent.com/61866984/145692186-d4bd2cae-ec20-4773-be91-6b3f2fa58690.png)
 
Klikken in het menu op Register brengt me op de home pagina en stel ik vast dat ik toch ben ingelogd: een beetje verwarrend, maar gelukkig niet blokkerend.
In het menu zie ik duidelijk dat ik ten allen tijd ben ingelogd. Mijn naam verschijnt in de rechterbovenhoek. (of op de smartphone bij het openen van het menu).
Ook afmelden loopt vlot en brengt me terug bij de startpagina.

## Inloggen met email en wachtwoord
Opnieuw inloggen werkt, maar ook daar botsen we op dezelfde foutboodschap als hierboven beschreven. Dit is niet blokkerend na klikken op de home pagina.
MFA of dubbele authenticatie is niet ingesteld. Eenvoudig inloggen met e-mail en paswoord lukt.

Aanbeveling: 
-	Amazon Cognito biedt ook sign-in via social identity providers zoals Apple, Google, Facebook en Amazon, OIDC of SAML. Van deze extra features wordt echter geen gebruik gemaakt. Misschien een gemiste kans?
-	MFA of dubbele authenticatie zou je kunnen instellen door bij elke login-poging de verificatiecode te laten sturen naar het e-mailadres van de gebruiker. Vandaag is dit enkel ingesteld bij registratie of opnieuw instellen van het wachtwoord. Andere optie: bij de registratie van een user hebben we een geldig mobiel telefoonnummer ingegeven. De verificatiecode zou kunnen verzonden worden per SMS naar dit mobiel nummer. Deze extra informatie wordt vandaag niet gebruikt in het kader van authenticatie. Andere MFA mogelijkheden worden beschreven in de documentatie van AWS: https://docs.aws.amazon.com/IAM/latest/UserGuide/id_credentials_mfa.html.

## Mijn wachtwoord opnieuw instellen
Als ik mijn paswoord ben vergeten, kan ik die opnieuw instellen door het ingeven van het betrokken e-mailadres. Ik ontvang een pincode en kan hiermee een nieuw paswoord instellen. Dat loopt vlot. 

Wanneer we dit proberen in te stellen met een eenvoudig paswoord zoals 123456789Aa! lukt dat ook. Zelfs dit eenvoudig paswoord is slechts 7 keer (< 300) gevonden in een data breach volgens Have I Been Pwned (https://haveibeenpwned.com/Passwords). Zie screenshot hieronder van het resultaat van de website van "Have I Been Pwned":
![Password7times](https://user-images.githubusercontent.com/61866984/145692354-ef57d848-615a-4143-beae-130ec17796db.png)
 
Als ik echter 10 keer snel na elkaar een verkeer paswoord invoer voor eenzelfde user en vervolgens het juiste, kan ik er nog steeds zonder enig probleem in. Inloggen wordt dus niet geblokkeerd door dit verdacht gedrag. De website is niet beschermd tegen brute force of credential stuffing attacks.

Aanbeveling: Een van de services die AWS ook aanbiedt is “AWS WAF” (https://aws.amazon.com/waf/features/bot-control/?did=ft_card&trk=ft_cardWAF). Dit is een web application firewall die je web app of API helpt te beschermen tegen websitemisbruik en bots die de beschikbaarheid en beveiliging kunnen compromitteren. De activatie van deze service zou direct leiden tot de mitigation van potentiële credential stuffing attacks. Standaard is dit bij AWS WAF ingesteld als volgt: “multiple requests in a 5-minute period to an application’s login page is suspicious and indicates a potential brute force or credential-stuffing attack against the application”. Andere threats zoals SQL injection of cross-site scripting worden hiermee ook aangepakt.

Op basis van een black box test kan ik niet uitmaken of paswoorden in plaintext worden opgeslagen of opgeslagen worden na toepassing van een hash algoritme zoals Argon2 of bcrypt. Na white box analyse blijkt dat paswoorden niet worden gehasht door de code geschreven door het team “Low Expectations”. Wachtwoordbeheer wordt gedaan door AWS Cognito. Spijtig genoeg (of gelukkig) deelt AWS Cognito niet welke algoritmes zij toepassen bij de bewaring van paswoorden.

# 3.	Evaluatie van beveiliging tegen typische web vulnerabilities

## Automated DAST scan by “Crashtest Security”
Naast de manuele analyses, hebben we ook een automatische DAST scan uitgevoerd met de hulp van “Crashtest Security” (https://crashtest.cloud). Een uitgebreid verslag vind je in bijlage. Zoals we ook verder zullen zien, stellen ook zij vast dat de hoogste prioriteit het aanpakken is van de HTTP header options. De ontbrekende header options in de webserver configuratie krijgen de hoogste CVSS score.

## Cookies
Er worden session cookies gebruikt tussen browser en web app. Hieronder vind je een voorbeeld: 
![Cookies_SameSite_peahi be_20211211](https://user-images.githubusercontent.com/61866984/145693236-f31924af-3117-4a76-9783-801f4110d2cb.png)

Alle gebruikte cookies hebben voor het SameSite attribuut de waarden “Lax” en zelfs “Strict”. Nooit hebben ze de waarden “None”. SameSite weerhoudt de browser ervan om de cookie bij een cross-site request mee te sturen. Alle formulieren bevatten een CSRF-token dat enkel server-side wordt gecontroleerd. Het hoofddoel is om het risico op cross-origin informatielekken te beperken. Het biedt ook een mate van bescherming tegen aanvallen op basis van cross-site request forgery (CSRF).

De cookie mbt login gegevens worden gewrapt in een JWT. Deze cookie heeft de SameSite attribuutwaarde "Strict".

## Header Options : X-Frame-Options, X-XSS-Protection en X-Content-Type-Options, Content-Security-Policy, Referrer-Policy
* De X-Frame-Options header is niet ingesteld voor de URL https://peahi.be. Als deze header niet goed is geconfigureerd, kan de web app ingebed worden als een frame in andere websites en kwetsbaar worden voor clickjacking attacks.
* Ook de Content-Security-Policy header is niet ingesteld. Deze instelling geeft de browser aan welke domains whitelisted zijn om verdere resources zoals scripts, images of stylesheets te downloaden. Dit beschermt je tegen Cross-Site-Scripting attacks.
* De X-Content-Type-Options header is eveneens niet ingesteld. Deze header option zorgt ervoor dat de browser geen MIME-types probeert te detecteren in downloads (MIME sniffing). Het beschermt je dus tegen aanvallen waarbij malicious files aangeboden worden met een niet-verdachte MIME-type.
* De Referrer-Policy header is ook niet ingesteld. Deze header option definieert hoeveel informatie over de “referrer” wordt verzonden wanneer de gebruiker op een link klikt. Een verkeerde configuratie van de Referrer-Policy header of het ontbreken ervan kan gevoelige informatie lekken naar de andere website die wordt bezocht door te klikken op deze link.

Aanbeveling: We zien dat jullie werken met een IIS web server geconfigureerd met C# code. Jullie kunnen de configuratie updaten met de volgende header options en overeenkomstige waarden:
* Strict-Transport-Security in te stellen op "max-age=31536000"
* X-Frame-Options -> "deny"
* X-XSS-Protection -> "1; mode=block"
* X-Content-Type-Options -> "nosniff"
* Content-Security-Policy -> "default-src 'self'"
* Referrer-Policy -> "strict-origin-when-cross-origin"

Voeg daarvoor de volgende sample C# code toe om een custom HTTP header goed in te stellen: 
```csharp
using System;
using System.Text;
using Microsoft.Web.Administration;

internal static class Sample
{
   private static void Main()
   {
      using (ServerManager serverManager = new ServerManager())
      {
         Configuration config = serverManager.GetWebConfiguration("Default Web Site");
         ConfigurationSection httpProtocolSection = config.GetSection("system.webServer/httpProtocol");
         ConfigurationElementCollection customHeadersCollection = httpProtocolSection.GetCollection("customHeaders");
         ConfigurationElement addElement = customHeadersCollection.CreateElement("add");
         addElement["name"] = @"Strict-Transport-Security";
         addElement["value"] = @"max-age=31536000";
         customHeadersCollection.Add(addElement);
         ConfigurationElement addElement = customHeadersCollection.CreateElement("add");
         addElement["name"] = @"X-Frame-Options";
         addElement["value"] = @"deny";
         customHeadersCollection.Add(addElement);
         ConfigurationElement addElement = customHeadersCollection.CreateElement("add");
         addElement["name"] = @"X-XSS-Protection";
         addElement["value"] = @"1; mode=block";
         customHeadersCollection.Add(addElement);
         ConfigurationElement addElement = customHeadersCollection.CreateElement("add");
         addElement["name"] = @"X-Content-Type-Options";
         addElement["value"] = @"nosniff";
         customHeadersCollection.Add(addElement);
         ConfigurationElement addElement = customHeadersCollection.CreateElement("add");
         addElement["name"] = @"Content-Security-Policy";
         addElement["value"] = @"default-src 'self'";
         customHeadersCollection.Add(addElement);
         serverManager.CommitChanges();
         ConfigurationElement addElement = customHeadersCollection.CreateElement("add");
         addElement["name"] = @"Referrer-Policy";
         addElement["value"] = @"strict-origin-when-cross-origin";
         customHeadersCollection.Add(addElement);
      }
   }
}
```
Meer informatie vinden jullie op https://docs.microsoft.com/en-us/iis/configuration/system.webserver/httpprotocol/customheaders/


# 4.	Evaluatie van HTTPS

Alle publiek bereikbare onderdelen zijn enkel over HTTPS beschikbaar. Een voobeeld voor de home pagina:
-	https://peahi.be/. 
-	https://peahi.be/lib/jquery/dist/jquery.min.js
-	https://peahi.be/lib/bootstrap/dist/js/bootstrap.bundle.min.js
-	https://peahi.be/bundles/js/assets.min.js
-	https://peahi.be/js/site.js?v=aBaYCj82vx2xDGXtnaLmiYDS26hexB7SYsIGs3ox5dY
-	Gebruikte link naar Mobiris Free Site Maker: https://mobirise.in/
-	Gebruikte js-file: https://kit.fontawesome.com/31f5f08539.js

## Opengestelde poorten
Poorten 80 en 443 zijn opengesteld. 
Aanbeveling: Aangezien alles over https gaat, is het openen van poort 443 voldoende. Sluit gerust poort 80.

## Controle op de geldigheid van X.509 digitale certificaten 

-	Controle van SSL Certificate revocation met CRL (Certificate Revocation Lists)
![CRLDistributionPoints_peahi be_20211211](https://user-images.githubusercontent.com/61866984/145693213-0c17218f-fe8e-4a26-b26c-4b3f7b256df2.png)

-	Controle van SSL Certificate revocation met OCSP (Online Certificate Status Protocol)
![OCSP_peahi be_20211211](https://user-images.githubusercontent.com/61866984/145693220-1b485cbf-ee76-4642-8901-2f8a0d2b353f.png)

- OCSP_stapling is niet beschikbaar op web server IIS versie 10.0.
Aanbeveling: je kan OCSP stapling activeren op IIS door de stappen te volgen beschreven in volgende documentatie: https://docs.microsoft.com/en-us/windows-server/security/tls/tls-registry-settings

## Analyse van de configuratie van de SSL web server met domein peahi.be op het publieke internet
In de beschreven architectuur van “Low Expectations” zien we 2 Amazon EC2 voor de website en 2 Amazon EC2 voor de API. Een test naar de score van het domein peahi.be bij SSL Labs geeft de 4 gebruikte containers een A+ score (https://www.ssllabs.com/ssltest/analyze.html?d=peahi.be&latest). Ziehier het resultaat:
![SSLLabs_20211211_peahi_be_A+](https://user-images.githubusercontent.com/61866984/145693589-1efd966a-dc25-4cac-936a-2a73871e7dde.png)

Op elke Amazon EC2 is HTTP Strict Transport Security (HSTS) geconfigureerd. Dit beschermt tegen “man-in-the-middle attacks” zoals “protocol downgrade attacks” en “cookie hijacking”. Het laat webservers toe om af te dwingen dat web browsers alleen kunnen communiceren met HTTPS connecties. Dat biedt net die belangrijke Transport Layer Security (TLS/SSL) in tegenstelling tot HTTP.

## Webserver on Microsoft-IIS 10.0
De webserver draait op Microsoft-IIS 10.0. De webserver geeft informatie bloot over zichzelf. Dit laat aanvallers toe om te zoeken naar mogelijkheden specifiek voor dit type webserver van deze versie.
Aanbeveling: De IIS server zal zijn versie laten zien in HTTP responses. Om dit niet meer te laten zien kan je het volgende doen: 
-	Activeer “Metabase Compatibility” (https://docs.microsoft.com/en-us/iis/manage/managing-your-configuration-settings/metabase-compatibility-with-iis-7-and-above). 
-	Installeer UrlScan.
-	Open vervolgens het UrlScan.ini bestand met notepad. Het bestand zal je in principe vinden in de directory %windir%\system32\inetsrv\UrlScan.
-	Zoek daar naar de key RemoveServerHeader die by default op 0 staat. Verander dit naar 1 om zo de Server header te verwijderen uit de HTTP response.

## HSTS preload list status
Verficatie van het domein op de HSTS preload list via (https://hstspreload.org/?domain=peahi.be) geeft aan dat het domein de status “pending submission” heeft en dus wacht op toevoeging.\
![HSTS_PreloadList_peahi_be_20211211](https://user-images.githubusercontent.com/61866984/145693595-5fca7009-6980-4799-a95f-1d30cdeed9a3.png)

## Certificate Authority Authorization DNS Resource Records
Via Entrust (https://www.entrust.com/resources/certificate-solutions/tools/caa-lookup) komen we te weten dat er een CAA record uitgegeven door Amazon van het type SSL.
![Entrust_CAARecord_peahi_be_20211211](https://user-images.githubusercontent.com/61866984/145693606-86e1c7bc-8d76-4656-bee4-88ba556047bb.png)
 
# 5. Conclusie
* Aan de web app zelf dient nog wat gewerkt te worden teneinde al de user stories waarvan sprake in de acceptatiecriteria te kunnen waarmaken. Vooral het kunnen beheren van de gebrukers en hun rollen door de admin is een belangrjk gemis.
* Security-gewijs zet ik de reeds vermelde aanbevelingen nog een op een rijtje in volgorde van belangrijkheid:
  1. Instellen van de header options op de IIS web server: X-Frame-Options, X-XSS-Protection en X-Content-Type-Options, Content-Security-Policy en Referrer-Policy. Deze eenvoudige ingreep beschermt onmiddellijk tegen een reeks threats zoals clickjacking attacks, Cross-Site-Scripting attacks, MIME sniffing en data leaks.
  2. Activatie van de service AWS WAF om brute force of credential stuffing attacks tegen te gaan.
  3. MFA of dubbele authenticatie activeren binnen de reeds gebruikte service Cognito van AWS. Eventueel kan zelfs gekozen worden voor sign-in via social identity providers zoals Apple, Google, Facebook en Amazon, OIDC of SAML.	
  4. Type web server en versie afschermen om het risico op gerichte attacks te verkleinen.
  5. OCSP stapling activeren op IIS om nog veiliger te werken bij de TLS handshake.
