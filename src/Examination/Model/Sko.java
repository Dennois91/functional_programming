package Examination.Model;

public class Sko {
    public int id;
    public Märke märke;
    public Model model;
    public Storlek storlek;
    public Färg färg;
    public Pris pris;
    public int lagerSaldo;
    public String skapad;
    public String ändrad;

    public Sko(int id, Märke märke, Model model, Storlek storlek, Färg färg, Pris pris, int lagerSaldo, String skapad, String ändrad) {
        this.id = id;
        this.märke = märke;
        this.model = model;
        this.storlek = storlek;
        this.färg = färg;
        this.pris = pris;
        this.lagerSaldo = lagerSaldo;
        this.skapad = skapad;
        this.ändrad = ändrad;
    }

    public String getInventory() {
        return märke.märke + " " + model.model + " " + färg.färg + " Storlek: " + storlek.storlek + " Saldo: " + (lagerSaldo);


    }
}
