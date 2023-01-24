package apps;

import repository.Child;
import repository.Elf;
import repository.Repository;

import java.util.List;

public class Monitor implements Runnable {
    Repository r = new Repository();
    private List<Child> childrenList;
    private List<Elf> elvesList;
    private double children = 0;
    private int elves = 0;
    private double nice = 0;

    @Override
    public void run() {
        while (true) {
            resetVars();
            childrenList = r.getChildrenList();
            for (Child child : childrenList) {
                children += 1;
                if (child.isNiceOrNot()) {
                    nice += 1;
                }
            }
            elvesList = r.getElvesList();
            for (Elf elf : elvesList) {
                elves += 1;
                System.out.println(elf.getName() + " " + "Nisse: " + elves);
            }
            System.out.println("\nNumber of elves: " + elves);
            System.out.println("Number of children: " + (int) children);
            System.out.println((int) nice + " children are nice of " + (int) children);
            System.out.println((int) (nice / children * 100) + "% of children are nice\n");
            sleep();
        }
    }

    public void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void resetVars(){
        children = 0;
        elves = 0;
        nice = 0;
    }
}

