## Sovellusarkkitehtuuri

Sovelluksen rakenne on kolmitasoinen - graaffinen käyttöliittymä, sovelluslogiikka ja tallennus.

#### Pakkausrakenne

Sovelluksen muodostavat kolme pakkausta: ui, logic ja data.

![Pakkausrakenne](/dokumentaatio/kuvat/rakenne.png "Pakkausrakenne")

Pakkaus ui sisältää graafisen käyttöliittymän komponentit jotka on toteutettu Javafx:llä. Pakkaus logic sisältää sovelluslogiikkaa: pelilaudan generointi, ajastin sekä tietokannan kanssa kommunikointi. Pakkaus data puolestaan sisältää tietokannan.

#### Käyttöliittymä 

Sovelluksen käyttöliittymä koostuu kolmesta osasta: pelin valintanäkymä, pelausnäkymä ja tulosnäkymä.

Jokainen näistä näkymistä on oma luokkansa jolla on muuttujana Scene olio jonka AppUi asetta stageen.

#### Sovelluslogiikka ja luokkarakenne

Sovelluksessa on päältäosin eroteltu käyttöliittymä sovelluslogiikasta. Luokka AppUi toimii välittäjänä näiden välillä. 

* Esimerkiksi kun sovellus aloittaa uuden pelin niin AppUi pyytää logic pakkauksessa sijaitsevalta BoardGenerator luokalta uutta generoitua pelilautaa.

Lisäksi käyttöliittymä on erotettu pysyväistallennuksesta. Luokka AppService taas puolestaan toimii välittäjänä näiden välillä.

* Esimerkiksi kun sovellus lopettaa pelin AppUi pyytää AppServiceltä listaa tallennetuista pelikerroista joka puolestaan pyytä tämän RunRegisteriltä. 

Sovelluksen täydellinen pakkaus- ja luokkakaavio:

![Luokka/pakkauskaavio](/dokumentaatio/kuvat/luokka_pakkauskaavio.png "Luokka/pakkauskaavio")

#### Pelikertojen pysyväistallennus

Sovellus noudattaa Data Acceess Object-mallia eli tallennusmetodia voi vaihtaa toiseen toteutukseen sillä se on erotetu muusta sovelluksesta. 

Sovelluksella pysyväistallennus on tehty SQLlite-tietokantana. Tietokantaan luodaan taulu Runs pelikertojen (koodissa run) tallennukseen. Tähän tauluun tallennetaan pelaajan antama käyttäjänimi, pelikerran pisteytys sekä  Database ja RunRegister ovat ainoat luokat jotka suoraan muokkaavat tietokantaa. 

* Database on vastuussa tietokannan muodostamisesta ja yhteydestä siihen. 

* RunRegister on vastuussa uusien pelikertojen tallennuksesta sekä niiden hakemisesta tietokannasta.

Taulu Runs luodaan serutaavasti:

<pre>
"CREATE TABLE Runs (id INTEGER PRIMARY KEY, username TEXT NOT NULL, score INTEGER, time DECIMAL (5, 2))"
</pre>


#### Päätoiminnallisuus

Sovelluksen sekvenssikaavio kun pelaaja klikkaa Tile luokan ruutua voittaen pelin:

![voitto_sekvenssikaavio](/dokumentaatio/kuvat/voitto_sekvenssikaavio.png "voitto_sekvenssikaavio")

1. Kun pelaaja klikkaa Tile luokan ruutua niin tämä kutsuu AppUin metodia checkVictory joka kutsuu AppServicen metodia checkVictoryCount 
    * Jos ruutu on viimeinen ei miinaa sisältävä ruutu pelaaja on voittanut pelin jolloin checkVictoryCount palauttaa arvon true
1. AppUi toteaa pelin voitetuksi ja kutsuu AppServicen tallentamaan pelinkerran
1. AppUi kutsuu RunRegister metodia addRun joka tallentaa pelinkerran tietokantaan
1. AppUi lopettaa ajastimen Timer kutsulla endTimer 
1. AppUi pyytää AppServeciltä listaa edellisistä pelikerroista
1. AppService pyytää RunRegisteriltä listaa 
1. RunRegister hakee edelliset pelikerrat tietokannasta ja muokkaa niistä listan jonka palauttaa AppServicelle
1. AppService palauttaa listan AppUille
2. AppUi antaa konstruktorissa listan ResultScreenille ja pyytää näkymää
1. ResultScreen luo näkymän saadun listan perusteella ja palauttaa sen AppUille
1. AppUi asettaa näkymän Stageen jolloin pelaaja näkee tämän


Sovelluksen sekvenssikaavio kun pelaaja aloittaa uuden pelin:

![uusi_peli_sekvenssikaavio](/dokumentaatio/kuvat/uusi_peli_sekvenssikaavio.png "uusi_peli_sekvenssikaavio")

1. SelectScreen kutsuu AppUitä ja samalla antaa tiedot uuden pelilaudan koosta
1. AppUi kertoo AppServicelle uuden pelin käynnistämisestä sen metodilla newGame
1. AppUi kutsuu BoardGeneratorin metodia generateBoard ja antaa sille parametrina pelilaudan koon
1. GenerateBoard luo GiridPanen johon se sijoittaa Tile-olioita pelilaudan muotoon
    * Lisäksi se muodostaa Tile-olioista verkon 
    * Se merkkaa osan Tile-olioista miinoiksi pelilaudan koon mukaan
    * Se asettaa ei-miina Tile olioiden arvon oikeaksi
1. GenerateBoard palauttaa luomansa GridPanen AppUille
1. AppUi pyytää näkymää BoardScreeniltä ja samalla antaa konstuktorina GridPanen BoardScreenille joka luo siitä näkymän
1. BoardGenerator palauttaa näkymän AppUille
1. AppUi aloittaa ajastimen kutsumalla Timer luokan metodia startTimer
1. AppUi asettaa BoardGeneratorilta saadun näkymän Stageen jolloin pelaajan näkee sen