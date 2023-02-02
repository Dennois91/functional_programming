package Examination.Model;

public class TotalBeställningarMadeByKundMapper implements DataMapper{
    @Override
    public String mapKey(Beställning beställning) {
        return beställning.kundId.namn;
    }
}
