package space.harbour.java.hm5;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MyHashMapTest extends TestCase {
    private MyHashMap<String, String> brothers;
    private MyHashMap<String, String> test;
    private MyHashMap<String, String> testCopy;

    @Before
    public void setUp() {
        MyHashMap<String, String> brothers;
        MyHashMap<String, String> test;
        MyHashMap<String, String> testCopy;
        this.brothers = new MyHashMap<>();
        this.test = new MyHashMap<>();
        this.testCopy = new MyHashMap<>();
    }

    //check if the hashmap is initialized correctly and it is empty
    @Test
    public void test00() {
        Assert.assertTrue(test.isEmpty());
    }

    //Test if you can add a pair and it's the correct pair and the size of map is correct
    @Test
    public void test01() {
        brothers.put("Ahmed", "8");
        assertEquals(brothers.size(), 1);
        Assert.assertTrue(brothers.containsKey("Ahmed"));
        Assert.assertEquals(brothers.get("Ahmed"), "8");
    }

    //Remove method test actually works
    @Test
    public void test02() {
        brothers.put("Ahmed", "8");
        brothers.put("Mohammed", "1");
        brothers.put("Nasser", "6");
        brothers.put("Khalil", "2");
        brothers.put("John", "4");
        brothers.remove("John");
        Assert.assertEquals(brothers.size(), 4);
        Assert.assertFalse(brothers.containsKey("John"));
    }

    // Test that removes returns null when removing a pair that doesn't exist
    @Test
    public void test03() {
        brothers.put("Ahmed", "8");
        brothers.put("Mohammed", "1");
        brothers.put("Nasser", "6");
        brothers.put("Khalil", "2");
        Assert.assertNull(brothers.remove("John"));
    }

    //containKey test return true if key exists and false if it doesn't
    @Test
    public void test04() {
        brothers.put("Ahmed", "8");
        brothers.put("Mohammed", "1");
        brothers.put("Nasser", "6");
        brothers.put("Khalil", "2");
        Assert.assertFalse(brothers.containsKey("Hamood"));
        Assert.assertTrue(brothers.containsKey("Ahmed"));
    }

    //containValue test return true if value exists and false if it doesn't
    @Test
    public void test05() {
        brothers.put("Ahmed", "8");
        brothers.put("Mohammed", "1");
        brothers.put("Nasser", "6");
        brothers.put("Khalil", "2");
        Assert.assertFalse(brothers.containsValue("7"));
        Assert.assertTrue(brothers.containsValue("2"));
    }

    //check  the get function returns correct value and if get(key) doesn't exist
    //it returns null and assert that is not null if the key exists
    @Test
    public void test06() {
        brothers.put("Ahmed", "8");
        brothers.put("Mohammed", "1");
        brothers.put("Nasser", "6");
        brothers.put("Khalil", "2");
        Assert.assertNull(brothers.get("Hamood"));
        Assert.assertNotNull(brothers.get("Ahmed"));
        Assert.assertEquals(brothers.get("Mohammed"), "1");
        Assert.assertEquals(brothers.get("Khalil"), "2");
        Assert.assertEquals(brothers.get("Nasser"), "6");
        Assert.assertEquals(brothers.get("Ahmed"), "8");
    }

    //Test the clear function that it size returns 0
    @Test
    public void test07() {
        brothers.put("Ahmed", "8");
        brothers.put("Mohammed", "1");
        brothers.put("Nasser", "6");
        brothers.put("Khalil", "2");
        brothers.clear();
        Assert.assertEquals(brothers.size(), 0);
    }

    //Test that when you put the same key but different value
    @Test
    public void test08() {
        brothers.put("Khalil", "2");
        Assert.assertEquals(brothers.get("Khalil"), "2");
        Assert.assertEquals(brothers.size(), 1);
        brothers.put("Khalil", "3");
        Assert.assertEquals(brothers.get("Khalil"), "3");
        Assert.assertEquals(brothers.size(), 1);
    }

    //Test add more than 16 puts and see if that add them to Hashmap without collisions
    @Test
    public void test09() {
        test.put("1", "1");
        test.put("2", "2");
        test.put("3", "2");
        test.put("4", "2");
        test.put("5", "2");
        test.put("6", "2");
        test.put("7", "2");
        test.put("8", "2");
        test.put("9", "2");
        test.put("10", "2");
        test.put("11", "2");
        test.put("12", "2");
        test.put("13", "2");
        test.put("14", "2");
        test.put("15", "2");
        test.put("16", "2");
        test.put("17", "2");
        test.put("18", "2");
        test.put("19", "2");
        test.put("20", "2");
        Assert.assertEquals(test.size(), 20);
    }

    //Test putAll and check for the values if they exist there or not
    @Test
    public void test10() {
        test.put("1", "1");
        test.put("2", "2");
        test.put("3", "2");
        test.put("4", "2");

        testCopy.putAll(test);
        Assert.assertEquals(testCopy.size(), test.size());
        Assert.assertTrue(testCopy.containsKey("1"));
        Assert.assertTrue(testCopy.containsKey("2"));
        Assert.assertTrue(testCopy.containsKey("3"));
        Assert.assertTrue(testCopy.containsKey("4"));
        Assert.assertFalse(testCopy.containsKey("5"));
        Assert.assertFalse(test.containsKey("5"));

    }


    //Test putAll where the second hashmap is not empty
    @Test
    public void test11() {
        test.put("1", "1");
        test.put("2", "2");
        test.put("3", "2");
        test.put("4", "2");

        testCopy.put("5", "5");

        testCopy.putAll(test);
        Assert.assertNotEquals(testCopy.size(), test.size());
        Assert.assertFalse(test.containsKey("5"));
        Assert.assertTrue(testCopy.containsKey("5"));
    }

    //Test put and remove couple of times.
    @Test
    public void test12() {
        test.put("1", "1");
        test.remove("1");
        test.put("1", "1");
        test.remove("1");
        test.put("1", "1");
        test.remove("1");
        test.put("1", "1");
        test.remove("1");

        Assert.assertEquals(test.size(), 0);
        Assert.assertFalse(test.containsKey("1"));
    }

    //Test if there are pairs with the same value wouldn't return True and then remove each pair
    @Test
    public void test13() {
        test.put("1", "1");
        test.put("2", "1");
        test.put("3", "1");
        Assert.assertTrue(test.containsValue("1"));
        test.remove("1");
        Assert.assertTrue(test.containsValue("1"));
        test.remove("2");
        Assert.assertTrue(test.containsValue("1"));
        test.remove("3");
        Assert.assertFalse(test.containsValue("1"));
    }

    //Test if you can put all an empty hashmap
    @Test
    public void test14() {
        test.put("1", "1");
        test.clear();
        testCopy.putAll(test);

        Assert.assertEquals(test.size(), 0);
        Assert.assertEquals(testCopy.size(), 0);
    }

    //Test if you clear the copy map that you don't clear the original map
    @Test
    public void test15() {
        test.put("1", "1");

        testCopy.putAll(test);
        testCopy.clear();
        Assert.assertEquals(test.size(), 1);
        Assert.assertEquals(testCopy.size(), 0);
    }

    // Check that we can add an empty key and value
    @Test
    public void test16() {
        test.put("", "");
        Assert.assertTrue(test.containsValue(""));
        Assert.assertTrue(test.containsKey(""));
    }

    // Check that we can use Integer as values
    @Test
    public void test17() {
        MyHashMap<String, Integer> testHashMap = new MyHashMap<>();
        testHashMap.put("Ahmed", 22);

        Assert.assertTrue(testHashMap.containsValue(22));
    }

    // Check that we can use Double
    @Test
    public void test18() {
        MyHashMap<String, Double> testHashMap = new MyHashMap<>();
        testHashMap.put("Ahmed", 22.0);

        Assert.assertTrue(testHashMap.containsValue(22.0));
    }

    // Check if float works
    @Test
    public void test19() {
        MyHashMap<String, Float> testHashMap = new MyHashMap<>();
        testHashMap.put("Ahmed", 22.0f);

        Assert.assertTrue(testHashMap.containsValue(22.0f));
    }

    // Check if we can have a null value for a key
    @Test
    public void test20() {
        MyHashMap<String, Float> testHashMap = new MyHashMap<>();
        testHashMap.put("Ahmed", null);

        Assert.assertNull(testHashMap.get("Ahmed"));
    }

    //@Test (expected = NullPointerException.class)
    //public void test21() {
    //    MyHashMap<Float, Float> testHashMap = new MyHashMap<>();
    //    testHashMap.put(null, null);
    //}




}