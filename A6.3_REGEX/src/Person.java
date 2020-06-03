import java.util.regex.Pattern;

public class Person {
    public String anrede;
    public String titel;
    public String vorname;
    public String nachname;
    public String organisation = "";
    public String abteilung;
    public String straße;
    public String hausnummer;
    public String plz;
    public String stadt = "";
    public String[] segments;
    public String[] s2;
    public String[] s3;
    public String[] s4;
    public Person(String input) {
        segments = input.split(Pattern.quote(","));
        s2 = segments[0].split(Pattern.quote(" "));
        anrede = s2[0];
        nachname = s2[s2.length - 1];
        vorname = s2[s2.length - 2];
        titel = s2[1];
        for (int i = 2; i < s2.length - 2; i++) {
            titel = titel + " " + s2[i];
        }
        organisation = cut(segments[1]);
        abteilung = cut(segments[2]);
        segments[3] = cut(segments[3]);
        s3 = segments[3].split(" ");
        straße = s3[0];
        hausnummer = s3[1];
        segments[4] = cut(segments[4]);
        s4 = segments[4].split(" ");
        plz = s4[0];
        stadt = s4[1];
        for(int i=2; i< s4.length; i++){
            stadt = stadt + " " + s4[i];
        }
    }


    private String cut(String s) {
        String retVal = "";
        if (Pattern.matches(" (\\w|\\W)*", s)) {
            retVal = s.substring(1, s.length());
        } else {
            retVal = s;
        }
        return retVal;
    }
}