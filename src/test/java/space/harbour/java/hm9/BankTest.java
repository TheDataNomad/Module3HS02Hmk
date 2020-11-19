package space.harbour.java.hm9;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class BankTest extends TestCase {
    private int amount;
    private ArrayList<AtmDispenser> atms;

    @Test
    public void testCloning() throws CloneNotSupportedException {
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
        Assert.assertEquals(atmDispenser.balance(), 0);

        atmDispenser2.dispenseMoney(120);
        Assert.assertEquals(atmDispenser2.balance(), 10);

        Assert.assertEquals(bankDepartment.getNumOfDispenser(), 3);
        Assert.assertEquals(bankDepartment.getTotalBalance(), 140);
    }

    @Test
    public void testObserver() throws CloneNotSupportedException {
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

        String message1 = atmDispenser.notifyBank();
        String message2 = atmDispenser2.notifyBank();

        Assert.assertTrue(message1.contains("Container 1: (50€) is empty."));
        Assert.assertTrue(message1.contains("Container 2: (20€) is empty."));
        Assert.assertTrue(message1.contains("Container 3: (10€) is empty."));
        Assert.assertTrue(message1.contains("Container 4: (5€) is empty."));

        Assert.assertTrue(message2.contains("Container 1: (50€) is empty."));
        Assert.assertTrue(message2.contains("Container 2: (20€) is empty."));
        Assert.assertTrue(message2.contains("Container 4: (5€) is empty."));

        Assert.assertFalse(message2.contains("Container 3: (10€) is empty."));

    }

}