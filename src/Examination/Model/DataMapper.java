package Examination.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface DataMapper {
    String mapKey(Beställning beställning);

    default Map<String, Integer> mapData(List<Beställning> beställningList, Output output) {
        if (output == Output.ORDERS_PER_KUND){
            Map<String, Integer> hashMap = new HashMap<>();
            beställningList.stream().forEach(beställning -> hashMap.merge(mapKey(beställning), 1, Integer::sum ));
            return hashMap;

        }else {
            Map<String, Integer> hashMap = new HashMap<>();
            beställningList.stream().forEach(beställning -> hashMap.merge(mapKey(beställning), beställning.totalPris, Integer::sum));
            return hashMap;
        }
    }
}

/*
hashMap
                        .merge(beställning.kundId.namn, beställning.totalPris, Integer::sum))

                         beställningList.stream()
                        .forEach(beställning -> hashMap
                                .merge(beställning.kundId.namn, beställning.totalPris, Integer::sum));
 */