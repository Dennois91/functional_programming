SELECT kund.namn, model.model, märke.märke, färg.färg, storlek.storlek, beställning.skapad
FROM kund
         JOIN beställning ON kund.id = beställning.kundId
         JOIN kundvagn ON beställning.id = kundvagn.beställningId
         JOIN sko ON kundvagn.skoId = sko.id
         JOIN färg ON sko.färgId = färg.id
         JOIN storlek ON sko.storlekId = storlek.id
         JOIN model ON sko.modelId = model.id
         JOIN märke ON sko.märkeId = märke.id
         JOIN pris on sko.prisId = pris.id


WHERE färg.färg = 'svart'
  AND model.model = 'sandal'
  AND storlek.storlek = 38
  AND märke.märke = 'ecco';

SELECT Kategori.kategori, COUNT(skoKatMapp.skoId) as antal
FROM Kategori
         LEFT JOIN skoKatMapp ON Kategori.id = skoKatMapp.kategoriId
GROUP BY Kategori.kategori;

SELECT kund.namn, SUM(beställning.totalPris) as AllaKöp
From kund
         JOIN beställning on kund.id = beställning.kundId
where kund.id = beställning.kundId
group by kund.namn;

SELECT kund.adress, SUM(beställning.totalPris) as AllaKöpIOrt
From kund
         join beställning on kund.id = beställning.kundId
where beställning.totalPris >= 100
group by kund.adress;


SELECT märke.märke, model.model, storlek.storlek, SUM(kundvagn.antal) as antalSålda
FROM kundvagn
         JOIN sko ON kundvagn.skoId = sko.id
         JOIN model ON sko.modelId = model.id
         JOIN märke on sko.märkeId = märke.id
         JOIN storlek on sko.storlekId = storlek.id
GROUP BY kundvagn.skoId, model.model
ORDER BY antalSålda DESC
LIMIT 5;

SELECT MONTHNAME(skapad) as månad, YEAR(skapad) as år, SUM(totalPris) as totalFörsäljning
FROM beställning
GROUP BY YEAR(skapad), MONTHNAME(skapad)
ORDER BY totalFörsäljning DESC;

CALL AddToCart(1, 50, 19);

CALL deleteKund(5);

