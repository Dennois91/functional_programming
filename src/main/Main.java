package main;

import apps.MonitorUpdater;
import apps.SearchChild;
import repository.Repository;

public class Main {
    MonitorUpdater monitorUpdater = new MonitorUpdater();
    Repository r = monitorUpdater.r;
    Thread thread = new Thread(monitorUpdater);

    public Main() {
        thread.start();
        new SearchChild();
        thread.interrupt();
    }

    public static void main(String[] args) {
        new Main();
    }
}
