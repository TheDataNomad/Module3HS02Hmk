package space.harbour.java.hm8;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EuroDispenser extends DispenseChain implements Iterator {

    private DispenseChain nextChain;
    private Integer moneyNote;
    private Integer count;
    private Integer numOfBills;

    public EuroDispenser(Integer moneyNote, Integer count) {
        this.moneyNote = moneyNote;
        this.count = count;
    }

    @Override
    public boolean hasNext() {
        return nextChain != null;
    }

    @Override
    public Object next() {
        return nextChain;
    }

    public Integer getMoneyNote() {
        return moneyNote;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public void dispense(Amount amt, Map<Integer, Integer> dispensedAmount) {
        int remainder = amt.getAmount();

        if (remainder >= moneyNote && remainder > 0 && count > 0) {

            numOfBills = Math.min(count, amt.getAmount() / moneyNote);
            count -= numOfBills;
            remainder = amt.getAmount() - (moneyNote * numOfBills);
            if (dispensedAmount.containsKey(moneyNote)) {
                dispensedAmount.put(moneyNote, dispensedAmount.get(moneyNote) + numOfBills);
            } else {
                dispensedAmount.put(moneyNote, numOfBills);
            }

        }
        if (remainder == 0) {
            System.out.println("Now Go Away!");
            dispensedAmount.forEach((note, num) ->
                    System.out.println("Dispensing " + num + " " + note + "€ notes"));
        } else if (nextChain != null) {
            nextChain.dispense(new Amount(remainder), dispensedAmount);
        } else {
            System.out.println("You don't have enough");
        }

    }


}
