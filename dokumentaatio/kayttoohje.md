## Käyttöohje

Lataa sovelluksen viimeisin release linkistä [Releases](https://github.com/Vesulius/ot-harjoitustyo/releases).

#### Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla

```
java -jar 'viimeisen_releasen_nimi'.jar
```

Uusi peli valitaan ruudukosta klikkaamalla. Samalla valitaan kuinka suuren pelin haluaa pelata. Pelilaudan koon määrittää vaaleat ruudut jotka näkyvät kun hiirtä liikuttaa ruudukon yllä. Pelaaja voi myös halutessaan kirjoittaa oman pelaajanimen joka pelaajan voitettua tallennetaan tietokantaan. 

Pelin tarkoituksena on avata peliruudukon kaikki ruudut. Jotkut ruudut siältävät miinoja ja pelaaja häviää jos hän avaa miinoitetun ruudun. Ei-miinoitetut ruudut kertovat montako miinaa sijaitsee viereisessä ruudussa. Tyhjän ruudun avautuessa avautuu myös kaikki sen viereiset ruudut.

Peliruutu avataan hiiren vasemmasta painikkeesta. 

Peliruudun voi merkitä miinaksi hiiren oikeasta painikkeesta jolloin sitä voi klikata vain hiiren oikealla painikkeella.

Pelaajan hävittyä tai voitettua pelin hän voi valita uudestaan valitsemisruutuun painikkeesta *NEW GAME*. 