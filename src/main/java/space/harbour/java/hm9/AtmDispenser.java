package space.harbour.java.hm9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtmDispenser implements Cloneable, ContainerObservable {
    public EuroDispenser c1;
    public static Map<Integer, Integer> dispensedAmount;
    public static int amount = 0;
    public static List<Bank> atmObservers;


    public AtmDispenser(EuroDispenser c1) {
        this.c1 = c1;
        this.dispensedAmount = new HashMap<>();
        this.atmObservers = new ArrayList<Bank>();
    }

    public void dispenseMoney(Integer amount) {
        System.out.print("\nATM (" + this.hashCode() + "): \n");
        System.out.println("You want " + amount);
        if (balance() >= amount) {
            System.out.println("Current balance is: " + balance());
            c1.dispense(new Amount(amount), dispensedAmount);
            Map<Integer, Integer> dispensedNotes = dispensedAmount;
            System.out.println(dispensedNotes);
            dispensedAmount.clear();
            System.out.println("New balance is: " + balance());

        } else {
            System.out.println("Your balance is: " + balance());
            System.out.println("You don't have enough");
        }
    }

    @Override
    protected AtmDispenser clone() {
        EuroDispenser clonedChain = c1.clone();
        return new AtmDispenser(clonedChain);
    }

    public int balance() {
        EuroDispenser currentContainer = c1;
        int balanceAccount = currentContainer.getMoneyNote() * currentContainer.getCount();
        while (currentContainer.hasNext()) {
            currentContainer = (EuroDispenser) currentContainer.next();
            balanceAccount += currentContainer.getMoneyNote() * currentContainer.getCount();
        }
        return balanceAccount;
    }

    public String notifyBank() {
        EuroDispenser currentContainer = c1;
        int numofNotes = currentContainer.getCount();
        int containerNumber = 1;
        StringBuilder message = new StringBuilder();
        if (numofNotes == 0) {
            message.append("\nContainer ")
                    .append(containerNumber).append(": (")
                    .append(currentContainer.getMoneyNote())
                    .append("€) is empty.\n");
        }

        while (currentContainer.hasNext()) {
            currentContainer = (EuroDispenser) currentContainer.next();
            containerNumber++;
            numofNotes = currentContainer.getCount();
            if (numofNotes == 0) {
                message.append("Container ")
                        .append(containerNumber).append(": (")
                        .append(currentContainer.getMoneyNote())
                        .append("€) is empty.\n");
            }
        }
        return message.toString();
    }

    @Override
    public void addObserver(AtmObserver observer) {
        atmObservers.add((Bank) observer);
    }

    @Override
    public void removeObserver(AtmObserver observer) {
        atmObservers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Bank department : atmObservers) {
            department.update(this);
        }
    }

}
