package Examination.Model;

public class SkoKatMapp {
    public int id;
    public Sko sko;
    public Kategori kategori;

    public SkoKatMapp(int id, Sko sko, Kategori kategori) {
        this.id=id;
        this.sko=sko;
        this.kategori=kategori;
    }
}
