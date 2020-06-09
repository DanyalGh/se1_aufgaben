import java.util.List;

public class CashUni {
    List<Kurs> kurse;
    List<Student> studenten;

    public CashUni(Kurs[] kurse, Student[] studenten) {

    }

    public int sumKurse() {
        return kurse.stream().map(k -> k.gebuehr).reduce(0, (a, b) -> a + b);
    }

    public int sumStudenten() {
        return studenten.stream().filter(s -> s.istBerufstaetig).map(s -> s.kurse).map(k -> k.stream().map(l -> l.gebuehr).reduce(0, (a, b) -> a + b)).reduce(0, (a, b) -> a + b);
    }

    public double avg() {
        return kurse.stream().filter(k -> k.ECTS > 5).map(l -> l.gebuehr).reduce(0, (a, b) -> a + b) / kurse.stream().filter(k -> k.ECTS > 5).toArray().length;
    }
}
