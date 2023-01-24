package Assignment_1_2a_b.apps;

import Assignment_1_2a_b.repository.Repository;

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
