package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti maksukortti;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        maksukortti = new Maksukortti(1000);
    }
    
    @Test
    public void kassaLuotu() {
        assertTrue(kassapaate != null);
    }
    
    @Test
    public void luodussaKassassaOikeatArvot() {
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0 && kassapaate.maukkaitaLounaitaMyyty() == 0 && kassapaate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void syoMaukkaastiKasvattaaSaldoa() {
        kassapaate.syoMaukkaasti(500);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiKasvattaaSaldoa() {
        kassapaate.syoEdullisesti(500);
        assertEquals(100240, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiOikeaVaihtoraha() {
        assertEquals(100, kassapaate.syoMaukkaasti(500));
    }
    
    @Test
    public void syoEdullisestiOikeaVaihtoraha() {
        assertEquals(260, kassapaate.syoEdullisesti(500));
    }
    
    @Test
    public void syoEdullisestiMyydytLounaatKasvaa() {
        kassapaate.syoEdullisesti(500);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaitaMyydytLounaatKasvaa() {
        kassapaate.syoMaukkaasti(500);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void liianAlhainenMaksuEiMuutaMyytyjaMaukkaitaLounaita() {
        kassapaate.syoMaukkaasti(0);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void liianAlhainenMaksuEiMuutaMyytyjaEdullisiaLounaita() {
        kassapaate.syoMaukkaasti(0);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void liianAlhainenMaukasMaksuEiMuutaKassaa() {
        kassapaate.syoMaukkaasti(0);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void liianAlhainenEdullinenMaksuEiMuutaKassaa() {
        kassapaate.syoEdullisesti(0);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void liianAlhainenMaksuMaukkaalleLounaallePalautetaan() {
        assertEquals(100, kassapaate.syoMaukkaasti(100));
    }
    
    @Test
    public void liianAlhainenMaksuEdulliselleLounaallePalautetaan() {
        assertEquals(100, kassapaate.syoEdullisesti(100));
    }
    
    @Test
    public void trueJosKortillaTarpeeksiRahaaEdulliseenLounaaseen() {
        assertTrue(kassapaate.syoEdullisesti(maksukortti));
    }
    
    @Test
    public void trueJosKortillaTarpeeksiRahaaMaukkaastiLounaaseen() {
        assertTrue(kassapaate.syoMaukkaasti(maksukortti));
    }
    
    @Test
    public void kortillaMyydytMaukkaatLounaatKasvaa() {
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaMyydytEdullisetLounaatKasvaa() {
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
        @Test
    public void falseJosKortillaJossaLiianVahanArvoaMaukkaaseen() {
        kassapaate.syoEdullisesti(maksukortti);
        kassapaate.syoEdullisesti(maksukortti);
        kassapaate.syoEdullisesti(maksukortti);
        kassapaate.syoEdullisesti(maksukortti);
        assertTrue(!kassapaate.syoMaukkaasti(maksukortti) && kassapaate.maukkaitaLounaitaMyyty() == 0 && maksukortti.saldo() == 40);
    }
    
    @Test
    public void falseJosKortillaJossaLiianVahanArvoaEdulliseen() {
        kassapaate.syoMaukkaasti(maksukortti);
        kassapaate.syoMaukkaasti(maksukortti);
        assertTrue(!kassapaate.syoEdullisesti(maksukortti) && kassapaate.edullisiaLounaitaMyyty() == 0 && maksukortti.saldo() == 200);
    }
    
    @Test
    public void syoMaukkaastiKortillaEiMuutaKassaa() {
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiKortillaEiMuutaKassaa() {
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortinLatausMuuttaaKassaa() {
        kassapaate.lataaRahaaKortille(maksukortti, 1000);
        assertEquals(101000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortinLatausMuuttaaSaldoa() {
        kassapaate.lataaRahaaKortille(maksukortti, 1000);
        assertEquals(2000, maksukortti.saldo());
    }
    
    @Test
    public void korttiaEiVoiLadataNegatiivisellaLuvulla() {
        kassapaate.lataaRahaaKortille(maksukortti, -1000);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
}
