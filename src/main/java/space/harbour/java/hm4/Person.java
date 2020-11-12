package space.harbour.java.hm4;


import javax.json.Json;
import javax.json.JsonObject;

public class Person implements Jsonable {
    private String name;
    private int age;
    private String gender;
    private Boolean hasKids;
    private String phone;

    public Person(String perName, int perAge, String perGender, Boolean kids, String perPhone) {
        this.name = perName;
        this.age = perAge;
        this.gender = perGender;
        this.hasKids = kids;
        this.phone = perPhone;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject json = Json.createObjectBuilder()
                .add("name", name)
                .add("age", age)
                .add("gender", gender)
                .add("hasKids", hasKids)
                .add("phone", phone)
                .build();

        return json;
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    public static void main(String[] args) {
        Person p = new Person("Ahmed", 31, "Male", false, "123456789");
        System.out.println(p.toJsonString());
    }
}
