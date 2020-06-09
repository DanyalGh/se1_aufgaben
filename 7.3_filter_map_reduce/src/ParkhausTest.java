
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ParkhausTest {
    Parkhaus p = new Parkhaus();
    Auto a1 = new Auto();
    Auto a2 = new Auto();
    Auto a3 = new Auto();
    Kunde k1 = new Kunde();
    Kunde k2 = new Kunde();
    List<Auto> autos;
    Stream<Auto> autoStream;

    @BeforeEach
    void setUp(){
        k1.istFirma = true;
        k2.istFirma = false;

        a1.hatTurbo = true;
        a1.k = k1;
        a1.parkdauer = 10;

        a2.hatTurbo = false;
        a2.k = k1;
        a2.parkdauer = 44444;

        a3.hatTurbo = true;
        a3.k = k1;
        a3.parkdauer = 2445;

        autos = Arrays.asList(a1,a2,a3);
    }

    @Test
    @DisplayName("Erster Test")
    void test1(){
        Integer priceSum = autos.stream().filter( a -> {return (a.hatTurbo && a.k.istFirma() == true);}).map( a -> a.parkdauer * Parkhaus.gebühr()).reduce(0, (a, b) -> a + b);
        assertEquals(175,priceSum);
    }

    @Test
    @DisplayName("Zweiter Test")
    void test2(){
        Stream<Auto> autos2 = autos.stream().filter( a -> a.k.istFirma).filter( a -> a.parkdauer > 1440);
        assertEquals(a3,autos2);
    }
}