package Weeks_With_JAVA.Examination.Model;

public class Kundvagn {
    public int id;
    public int antal;

    public Sko getSko() {
        return sko;
    }

    public int getAntal() {
        return antal;
    }

    public Sko sko;
    public Beställning beställning;

    public Kundvagn(int id, int antal, Sko sko, Beställning beställning) {
        this.id=id;
        this.antal=antal;
        this.sko=sko;
        this.beställning=beställning;
    }
}
