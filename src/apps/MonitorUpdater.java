package apps;

import repository.Child;
import repository.Elf;
import repository.Repository;

import java.util.List;

public class MonitorUpdater implements Runnable {
    private double children = 0;
    private int elves = 0;
    private double nice = 0;
    public Repository r = new Repository();

    public double getChildren() {
        return children;
    }

    public void setChildren(double children) {
        this.children = children;
    }

    public int getElves() {
        return elves;
    }

    public void setElves(int elves) {
        this.elves = elves;
    }

    public double getNice() {
        return nice;
    }

    public void setNice(double nice) {
        this.nice = nice;
    }


    @Override
    public void run() {
        while (true) {
            resetVars();
            List<Child> childrenList = r.getChildrenList();
            for (Child child : childrenList) {
                children += 1;
                if (child.isNiceOrNot()) {
                    nice += 1;
                }
            }
            List<Elf> elvesList = r.getElvesList();
            for (Elf elf : elvesList) {
                elves += 1;
                System.out.println(elf.getName() + " " + "Nisse: " + elves);
            }
            printStrings();
            sleep();
        }
    }

    public void printStrings(){
        System.out.println("\nNumber of elves: " + elves);
        System.out.println("Number of children: " + (int) children);
        System.out.println((int) nice + " children are nice of " + (int) children);
        System.out.println((int) (nice / children * 100) + "% of children are nice\n");
    }

    public void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetVars() {
        children = 0;
        elves = 0;
        nice = 0;
    }
}

