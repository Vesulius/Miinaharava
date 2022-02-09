# Miinaharava

Klassinen miinaharava peli. Tämä projekti on *Helsingin yliopiston* kurssin *ohjelmistotekniikka (_TKT20002_)* harjoitustyö.

## Demovideo

![demo gif](https://github.com/Vesulius/Miinaharava/blob/master/dokumentaatio/kuvat/minesweepper.gif)


## Releaset

[Viimeinen palautus](https://github.com/Vesulius/ot-harjoitustyo/releases/tag/v3)

#### Dokumentaatio
[Vaatimusmäärittely](https://github.com/Vesulius/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/Vesulius/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/Vesulius/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Käyttöohje](https://github.com/Vesulius/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Testausdokumentti](https://github.com/Vesulius/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)


## Komentorivikomennot
Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
``` 

java checkstyle raportti luodaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
``` 

Suoritettavan jar-tiedoston tuottaminen

```
mvn package
``` 
