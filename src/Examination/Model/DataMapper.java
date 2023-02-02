package Examination.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface DataMapper {
    String mapKey(Beställning beställning);

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
