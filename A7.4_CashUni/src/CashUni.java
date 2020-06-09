import java.util.List;

public class CashUni {
    List<Kurs> kurse;
    List<Student> studenten;

    public CashUni(Kurs[] kurse, Student[] studenten) {

    }

    public int sumKurse() {
        int tmp = 0;

        for (Kurs kurs : kurse) {
            tmp += kurs.gebuehr;
        }

        return tmp;
    }

    public int sumStudenten() {
        int tmp = 0;

        for (Student student : studenten.stream().filter(s -> s.istBerufstaetig).toArray(Student[]::new)){
            for (Kurs kurs : student.kurse) {
                tmp += kurs.gebuehr;
            }
        }

        return tmp;
    }

    public double avg() {
        int tmp = 0, i = 0;

        for (Kurs kurs : kurse.stream().filter(k -> k.ECTS > 5).toArray(Kurs[]::new)) {
            tmp += kurs.gebuehr;
            i++;
        }

        return tmp / i;
    }
}
