package Assignment_1_2a_b.main;

import Assignment_1_2a_b.apps.MonitorUpdater;
import Assignment_1_2a_b.apps.SearchChild;
import Assignment_1_2a_b.repository.Repository;

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
