package Assignment_1_2_3abc.apps;

import Assignment_1_2_3abc.repository.Repository;

import javax.swing.*;

public class Manager {
    MonitorUpdater monitorUpdater = new MonitorUpdater();
    Repository r = monitorUpdater.r;

    public Manager() {

    }

    public void getChildByName() {
        while (true) {
            String input = JOptionPane.showInputDialog(null, "Search a child name");
            if (input == null) {
                break;
            }
            System.out.println(r.getChildNameAndNice(input));
        }
    }

    public void changeElfNameByName() {
        while (true) {
            String name = JOptionPane.showInputDialog(null, "Type name of elf who's name to change name");
            if (name == null) {
                break;
            }
            String newName = JOptionPane.showInputDialog(null, "Type new name of " + name);
            if (newName == null) {
                break;

            }

            r.changeElfNameByName(name, newName);

        }
    }

    public void deleteElfByName() {
        while (true) {
            String name = JOptionPane.showInputDialog(null, "Type name of elf to fire that elf");
            if (name == null) {
                break;
            }
            r.deleteElfByName(name);
        }
    }

    public void addNewGift() {
        while (true) {
            String name = JOptionPane.showInputDialog(null, "Type name of gift to add a gift");
            if (name == null) {
                break;
            }
            r.addNewGift(name);
        }
    }
}
