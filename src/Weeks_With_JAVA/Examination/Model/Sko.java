package Weeks_With_JAVA.Examination.Model;

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

    public int getId() {
        return id;
    }

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
        return "Märke: " + märke.märke + " Model: " + model.model + " Färg: " + färg.färg + " Storlek: " + storlek.storlek + " Saldo: " + (lagerSaldo);
    }

    public String getSko() {
        return "Märke: " + märke.märke + " Model: " + model.model + " Färg: " + färg.färg + " Storlek: " + storlek.storlek;
    }

    public String getModel() {
        return this.model.model;
    }

    public String getFärg() {
        return this.färg.färg;
    }

    public String getMärke() {
        return this.märke.märke;
    }

    public String getStorlek() {
        return String.valueOf(this.storlek.storlek);
    }
}
