package Assignment_1_2a.main;

import Assignment_1_2a.apps.MonitorUpdater;
import Assignment_1_2a.apps.SearchChild;
import Assignment_1_2a.repository.Repository;

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
