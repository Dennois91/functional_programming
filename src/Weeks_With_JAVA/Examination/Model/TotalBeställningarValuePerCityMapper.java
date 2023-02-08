package Weeks_With_JAVA.Examination.Model;

public class TotalBeställningarValuePerCityMapper implements DataMapper{
    @Override
    public String mapKey(Beställning beställning) {
        return beställning.kundId.adress;
    }
}
/*
Returns Adress från beställningen för att visa order värdet per stad
*/