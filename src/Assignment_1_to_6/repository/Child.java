package Assignment_1_to_6.repository;

public class Child {
   private int id;
   private String name;
   private String adress;
   private int countryId;
   private boolean niceOrNot;

    public boolean isNiceOrNot() {
        return niceOrNot;
    }

    public void setNiceOrNot(boolean niceOrNot) {
        this.niceOrNot = niceOrNot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getAccountableIntelligenceElfId() {
        return accountableIntelligenceElfId;
    }

    public void setAccountableIntelligenceElfId(int accountableIntelligenceElfId) {
        this.accountableIntelligenceElfId = accountableIntelligenceElfId;
    }

    int accountableIntelligenceElfId;
    public Child() {

    }
}
