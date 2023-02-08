package Weeks_With_JAVA.Examination.Model;

public class TotalBeställningValuePerKundMapper implements DataMapper{
    @Override
    public String mapKey(Beställning beställning) {
        return beställning.kundId.namn;
    }
}
/*
Returns kund namn för att visa namn på kund med respektive ordervärde
*/
