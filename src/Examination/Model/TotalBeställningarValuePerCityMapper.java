package Examination.Model;

public class TotalBeställningarValuePerCityMapper implements DataMapper{
    @Override
    public String mapKey(Beställning beställning) {
        return beställning.kundId.adress;
    }
}
