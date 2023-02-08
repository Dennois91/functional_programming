package Weeks_With_JAVA.Examination.Model;

public class Kund {
    public int id;
    public String email;
    public String namn;
    public String password;
    public String adress;
    public String skapad;
    public String ändrad;

    public Kund(int id, String email, String namn, String adress, String password, String skapad, String ändrad) {
        this.id = id;
        this.email = email;
        this.namn=namn;
        this.password = password;
        this.adress=adress;
        this.skapad=skapad;
        this.ändrad=ändrad;
    }
}
