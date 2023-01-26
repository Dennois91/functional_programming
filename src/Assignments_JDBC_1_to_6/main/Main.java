package Assignments_JDBC_1_to_6.main;

import Assignments_JDBC_1_to_6.apps.MonitorUpdater;
import Assignments_JDBC_1_to_6.apps.Manager;
import Assignments_JDBC_1_to_6.repository.Repository;

public class Main {
    MonitorUpdater monitorUpdater = new MonitorUpdater();
    Repository r = monitorUpdater.r;
    Thread thread = new Thread(monitorUpdater);

    public Main() {
        thread.start();
        // new Manager().getChildByName();
        // new Manager().changeElfNameByName();
        // new Manager().deleteElfByName();
        // new Manager().addNewGift();
        // new Manager().addGiftTroughSP();
        new Manager().addNewManufacturingElfSP();
        thread.interrupt();
    }

    public static void main(String[] args) {
        new Main();
    }
}
/*
Uppgift 3a – Nissarnas namnbytesprogram
En grej som nissarna har för sig är att de gillar att byta namn. Gör ett javaprogram där du läser in en
nisses nuvarande namn och det namn nissen vill byta till. Uppdatera nissens namn i databasen.
Uppgift 3b – Nisse-avskedning med omedelbar verkan.
Tomten har ju, som bekant, problem med nissarnas lojalitet till sin arbetsplats. Gör ett javaprogram
som läser in ett namn och om det finns en nisse med det namnet i databasen, avskeda hen genom att ta
bort nissen ur databasen.
Uppgift 3c – Lägg in present.
Skriv ett javaprogram som lägger in en present i databasen.
 */
