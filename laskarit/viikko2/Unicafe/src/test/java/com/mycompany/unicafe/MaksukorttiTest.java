package com.mycompany.unicafe;



import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void konsturktoriAntaaOikeanSaldon() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void kortilleVoiLataaRahaa() {
        kortti.lataaRahaa(500);
        assertEquals("saldo: 15.0", kortti.toString());
    }
    
    @Test 
    public void saldoaEiSaaNegatiiviseksi() {
        kortti.otaRahaa(2000);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void trueJosRahaRiitti() {
        assertTrue(kortti.otaRahaa(1000));
    }
    
    @Test
    public void falseJosRahaEiRiittanyt() {
        assertTrue(!kortti.otaRahaa(2000));
    }
    
    
    @Test
    public void varmistaSaldo() {
        assertEquals(1000, kortti.saldo());
    }
}
