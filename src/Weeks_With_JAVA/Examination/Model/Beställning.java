package Weeks_With_JAVA.Examination.Model;

public class Beställning {
    public int id;
    public Kund kundId;
    public int totalPris;
    public String skapad;
    public String ändrad;

    public Beställning(int id, Kund kundId, int totalPris, String skapad, String ändrad) {
        this.id = id;
        this.kundId = kundId;
        this.totalPris = totalPris;
        this.skapad = skapad;
        this.ändrad = ändrad;
    }
}
