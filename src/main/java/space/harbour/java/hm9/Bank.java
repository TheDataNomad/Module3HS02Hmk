package space.harbour.java.hm9;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Bank implements AtmObserver, Cloneable {
    ArrayList<AtmDispenser> atms;
    AtmDispenser baseDispenser;

    public Bank() {
        this.atms = new ArrayList<>();
    }

    public AtmDispenser getNewDispenser() throws CloneNotSupportedException {
        AtmDispenser newDispenser = baseDispenser.clone();
        atms.add(newDispenser);
        return newDispenser;
    }

    public AtmDispenser getDispenser(int index) {
        return atms.get(index);
    }

    public int getNumOfDispenser() {
        return atms.size();
    }

    public int getTotalBalance() {
        int total = atms.stream().mapToInt(AtmDispenser::balance).sum();
        return total;
    }

    @Override
    public void update(AtmDispenser atmDispenser) {
        System.out.println("ATM [" + atmDispenser.hashCode()
                + "] Status: " + atmDispenser.notifyBank() );

    }
}
