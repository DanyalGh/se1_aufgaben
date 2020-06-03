

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person p = new Person("Frau Dr. Eva Müller, Hochschule Bonn-Rhein-Sieg, Fachbereich Informatik, Grantham-Allee 20, 53757 Sankt Augustin");
    Person p2 = new Person("Herr Prof. Dr. Johannes Krumpel,Hochschule Bonn-Rhein-Sieg,Fachbereich Informatik,Grantham-Allee 20,53757 Sankt Augustin");
    @Test
    @DisplayName("Test für Anrede")
    void test1(){
        assertEquals("Frau",p.anrede);
        assertEquals("Herr",p2.anrede);
    }

    @Test
    @DisplayName("Test für Titel")
    void test2(){
        assertEquals("Dr.",p.titel);
        assertEquals("Prof. Dr.",p2.titel);
    }

    @Test
    @DisplayName("Test für Vorname")
    void test3(){
        assertEquals("Eva",p.vorname);
        assertEquals("Johannes",p2.vorname);
    }

    @Test
    @DisplayName("Test für Nachname")
    void test4(){
        assertEquals("Müller",p.nachname);
        assertEquals("Krumpel",p2.nachname);
    }

    @Test
    @DisplayName("Test für Organisation")
    void test5(){
        assertEquals("Hochschule Bonn-Rhein-Sieg",p.organisation);
        assertEquals("Hochschule Bonn-Rhein-Sieg",p2.organisation);
    }

    @Test
    @DisplayName("Test für Abteilung")
    void test6(){
        assertEquals("Fachbereich Informatik",p.abteilung);
        assertEquals("Fachbereich Informatik",p2.abteilung);
    }

    @Test
    @DisplayName("Test für Straße")
    void test7(){
        assertEquals("Grantham-Allee",p.straße);
        assertEquals("Grantham-Allee",p2.straße);
    }

    @Test
    @DisplayName("Test für Hausnummer")
    void test8(){
        assertEquals("20",p.hausnummer);
        assertEquals("20",p2.hausnummer);
    }

    @Test
    @DisplayName("Test für PLZ")
    void test9(){
        assertEquals("53757",p.plz);
        assertEquals("53757",p2.plz);
    }

    @Test
    @DisplayName("Test für Stadt")
    void test10(){
        assertEquals("Sankt Augustin",p.stadt);
        assertEquals("Sankt Augustin",p2.stadt);

    }
}