package space.harbour.java.hm8;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AtmDispenser {
    public DispenseChain c1;
    public static Map<Integer, Integer> dispensedAmount = new HashMap<>();
    public static int amount = 0;

    public AtmDispenser() {
        this.c1 = new EuroDispenser(50, 10);
        DispenseChain c2 = new EuroDispenser(20, 0);
        c1.setNextChain(c2);

        DispenseChain c3 = new EuroDispenser(5, 4);
        c2.setNextChain(c3);

        DispenseChain c4 = new EuroDispenser(5, 15);
        c3.setNextChain(c4);
    }

    public int balance() {
        EuroDispenser currentContainer = (EuroDispenser) c1;
        int balanceAccount = currentContainer.getMoneyNote() * currentContainer.getCount();
        while (currentContainer.hasNext()) {
            currentContainer = (EuroDispenser) currentContainer.next();
            balanceAccount += currentContainer.getMoneyNote() * currentContainer.getCount();
        }
        return balanceAccount;
    }

    public static void main(String[] args) {
        amount = 65;

        if (amount % 5 != 0) {
            System.out.println("Amount should be in multiple of 5s.");
            return;
        }
        System.out.println("You want " + amount);

        AtmDispenser atmDispenser = new AtmDispenser();
        System.out.println("Current balance is: " + atmDispenser.balance());
        atmDispenser.c1.dispense(new Amount(amount), dispensedAmount);
        System.out.println("New balance is: " + atmDispenser.balance());
    }
}
