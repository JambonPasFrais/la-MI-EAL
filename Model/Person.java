public class Person {
    private String firstName;
    private String lastName;
    private int age;

    Person(){
        this.firstName = "Armand";
        this.lastName = "Deff";
        this.age = 21;
    }
    //Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    //Getters
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }
    public String getFirstName() {
        return firstName;
    }
}
