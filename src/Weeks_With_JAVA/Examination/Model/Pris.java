package Weeks_With_JAVA.Examination.Model;

public class Pris {
    public int id;
    public int pris;
    public Model model;
    public Märke märke;
    public String skapad;
    public String ändrad;

    public Pris(int id, int pris, Model model, Märke märke, String skapad, String ändrad) {
        this.id=id;
        this.pris=pris;
        this.märke=märke;
        this.model=model;
        this.skapad=skapad;
        this.ändrad=ändrad;
    }
}
