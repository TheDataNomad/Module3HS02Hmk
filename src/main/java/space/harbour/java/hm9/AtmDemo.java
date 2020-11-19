package space.harbour.java.hm9;

import java.util.ArrayList;

public class AtmDemo {
    public static int amount;
    public static ArrayList<AtmDispenser> atms;


    public static void main(String[] args) throws CloneNotSupportedException {
        EuroDispenser c1 = new EuroDispenser(50, 2);
        EuroDispenser c2 = new EuroDispenser(20, 1);
        c1.setNextChain(c2);
        EuroDispenser c3 = new EuroDispenser(10, 1);
        c2.setNextChain(c3);
        EuroDispenser c4 = new EuroDispenser(5, 0);
        c3.setNextChain(c4);
        AtmDispenser baseDispenser = new AtmDispenser(c1);

        Bank bankDepartment = new Bank();
        bankDepartment.baseDispenser = baseDispenser;

        AtmDispenser atmDispenser = bankDepartment.getNewDispenser();
        AtmDispenser atmDispenser2 = bankDepartment.getNewDispenser();
        AtmDispenser atmDispenser3 = bankDepartment.getNewDispenser();

        atmDispenser.dispenseMoney(130);
        atmDispenser2.dispenseMoney(120);

        atmDispenser2.addObserver(bankDepartment);
        atmDispenser.addObserver(bankDepartment);
        bankDepartment.update(atmDispenser2);
        bankDepartment.update(atmDispenser);

        System.out.println("\n[After] Money in the bank ATMs: "
                + bankDepartment.getTotalBalance() + "€");
        System.out.println("Number of ATMs: " + bankDepartment.getNumOfDispenser());
        //atmDispenser.notifyBank();
    }
}
