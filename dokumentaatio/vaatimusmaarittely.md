## Vaatimusmäärittely

Sovellus on klassinen miinaharava-peli. Pelin tarkoituksena on avata peliruudukon kaikki ruudut. Jotkut ruudut siältävät miinoja ja pelaaja häviää jos hän avaa miinoitetun ruudun. Ei-miinoitetut ruudut kertovat montako miinaa sijaitsee viereisessä ruudussa.

### Toiminnallisuus

Peli käyttää graaffista käyttöjärjestelmää. Pelissä on kolme eri sivua: valintasivu, pelisivu ja tuloksetsivu.

#### Valintasivu

* Valintasivulla pelaaja voi valita haluamansa kokoisen pelikentän 
    * Tämä tehdään klikkaamalla ruutua. Valittu pelialue näkyy ruudukossa muuta ruudukkoa vaaleampana 
* Tällä sivulla pelaaja voi myös valita itselleen haluamansa nimimerkin


#### Pelisivu

* Pelisivulla pelaaja voi pelata peliä klikkaamalla peliruutuja hiirellä 
* Hiiren vasen klikkaus avaa ruudun paljastaen ruudun arvon
    * Jos ruutu on miina pelaaja häviää pelin ja sivu vaihtuu tulossivuksi
    * Jos ruutu on tyhjä se paljastaa rekursiivisesti kaikki muut vierekkäiset tyhjät ruudut
    * Jos kaikki ei miinaa sisältävät ruudut ovat avattu pelaaja voittaa pelin ja sivu vaihtuu tulossivuksi 
* Hiiren oikea klikkaus merkkaa ruudun jolloin sitä voi klikata vain hiiren oikealla painikkeella
* Pelisivulla näkyy myös ajastin joka alkaa nollasta ja laskee ylöspäin sekunnin kymmenesosan tarkkuudella


## Tulossivu

* Tulossivulla näkyy jos pelaaja on voittanut pelin
    * 'VICTORY' jos pelaaja voittaa, 'GAME OVER' jos pelaaja häviää
* Tulossivulla on painike 'NEW GAME' joka asettaa sivun valintasivuksi
* Tulossivulla näkyy voittoon päättyneiden pelauskertojen tulokset jotka on asetettu järjestykseen pisteytyksen mukaan

### Ominaisuudet

Pelin ominaisuudet

#### Perusominaisuudet 
* Sovellus luo graaffisen esityksen ruudullisesta pelilaudasta 
* Pelilaudalle generoidaan tietty määrä miinoja sattumanvaraisesti
* Käyttäjä pelaa peliä klikkaamalla pelilaudan ruutuja 
* Käyttäjä voi merkitä ruutuja miinoiksi 
* Sovellus osaa laskea ja näyttää oikeat arvot klikatuille peliruuduille (eli vierekkäisten miinojen määrän) 
* Sovellus tunnistaa voittotilanteen kun kaikki miinat ovat löytyneet  
* Sovellus tunnistaa tappion jos pelaaja klikkaa miinaruutua 

#### Lisäominaisuudet
* Käyttöliittymä jossa käyttäjä aloittaa pelin ja määritellä: 
    * Eri kokoiset pelikentät mahdollisia
* Tietokanta johon tallennetaan pelauskerrat
    * Tallennetaan pelauskerran pisteytys joka riippuu pelikentän koosta ja pelauskerran ajasta 
    * Tallennetaan pelaajan antama nimimerkki
    * Pelauskerran aika