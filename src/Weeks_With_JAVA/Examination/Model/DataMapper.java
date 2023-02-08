package Weeks_With_JAVA.Examination.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface DataMapper {
    String mapKey(Beställning beställning);

    /*
    Beställning sätts som nyckel för att använda i HashMap

    default metoden mapData tar in en lista med beställningar och ett ENUM och return en fylld hashMap
    En HashMap skapas upp
    För varje beställning i listan beräknas antalet ordar ELLER Summan av ordrar
    beroende på om vi får in Enumet ORDERS_PER_KUND
    */

    default Map<String, Integer> mapData(List<Beställning> beställningList, Output output) {
        Map<String, Integer> hashMap = new HashMap<>();
        beställningList.stream().
                forEach(beställning -> hashMap.
                        merge(mapKey(beställning),
                                output == Output.ORDERS_PER_KUND ? 1 : beställning.totalPris, Integer::sum)
                );
        return hashMap;
    }
}
