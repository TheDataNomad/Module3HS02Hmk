package space.harbour.java.hm8;

import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;


public class AtmDispenserTest extends TestCase {


    @Test
    public void test01() {
        Map<Integer, Integer> dispensedAmount = new HashMap<>();
        int amount = 65;

        AtmDispenser atmDispenser = new AtmDispenser();
        atmDispenser.c1.dispense(new Amount(amount), dispensedAmount);

        Assert.assertEquals(dispensedAmount.get(50), Integer.valueOf(1));
        Assert.assertEquals(dispensedAmount.get(5), Integer.valueOf(3));
        Assert.assertFalse(dispensedAmount.containsKey(20));
    }

    @Test
    public void test02() {
        Map<Integer, Integer> dispensedAmount = new HashMap<>();
        int amount = 65;

        int balanceBefore;
        int balanceAfter;

        AtmDispenser atmDispenser = new AtmDispenser();
        balanceBefore = atmDispenser.balance();
        atmDispenser.c1.dispense(new Amount(amount), dispensedAmount);
        balanceAfter = atmDispenser.balance();

        Assert.assertEquals(balanceBefore, 595);
        Assert.assertEquals(balanceAfter, 530);
    }
}