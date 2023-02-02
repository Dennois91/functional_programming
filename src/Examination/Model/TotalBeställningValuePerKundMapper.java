package Examination.Model;

public class TotalBeställningValuePerKundMapper implements DataMapper{
    @Override
    public String mapKey(Beställning beställning) {
        return beställning.kundId.namn;
    }
}
