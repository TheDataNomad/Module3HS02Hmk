package space.harbour.java.hm9;

import java.util.Map;

public abstract class DispenseChain {

    public abstract void setNextChain(DispenseChain nextChain);

    public abstract void dispense(Amount amt, Map<Integer, Integer> dispensedAmount);
}
