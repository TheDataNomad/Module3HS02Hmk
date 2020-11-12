package space.harbour.java.hm3;


public class HashMapExample {

    public static void main(String[] args) {

        MyHashMap<String, String> brothers = new MyHashMap<>();

        brothers.put("Ahmed", "8");
        brothers.put("Mohammed", "1");
        brothers.put("Nasser", "6");
        brothers.put("Khalil", "2");
        brothers.put("John", "4");

        System.out.println("Number of brothers: " + brothers.size());
        System.out.println("Is Hamood a brother: " + brothers.containsKey("Hamood"));
        System.out.println("Is Ahmed a brother: " + brothers.containsKey("Ahmed"));
        System.out.println("Remove John: value of John =" + brothers.remove("John"));
        System.out.println("Size after removing John: " + brothers.size());
        System.out.println("Ahmed's order: " + brothers.get("Ahmed"));
        System.out.println("Is there order 7: " + brothers.containsValue("7"));
        System.out.println("Is there order 2: " + brothers.containsValue("2"));

        MyHashMap<String, String> newBrothers = new MyHashMap<>();

        newBrothers.putAll(brothers);

        //for (Map.Entry<String, String> pair : brothers.entrySet()) {
        //    System.out.println(pair.getKey() + ": " + pair.getValue());
        //}

    }
}
