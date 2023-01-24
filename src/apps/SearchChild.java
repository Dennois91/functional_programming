package apps;

import repository.Repository;

import javax.swing.*;

public class SearchChild {
    MonitorUpdater monitorUpdater = new MonitorUpdater();
    Repository r = monitorUpdater.r;

    public SearchChild() {
        while (true) {
            String input = JOptionPane.showInputDialog(null, "Search a child name");
            if (input == null) {
                break;
            }
            System.out.println(r.getChildNameAndNice(input));
        }
    }
}
