package space.harbour.java.hm9;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BankTest extends TestCase {
    public static int amount;
    public static ArrayList<AtmDispenser> atms;

    @Test
    public void test01() throws CloneNotSupportedException {
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
        atmDispenser.balance()
        atmDispenser2.dispenseMoney(120);

        atmDispenser2.addObserver(bankDepartment);
        atmDispenser.addObserver(bankDepartment);
    }

}