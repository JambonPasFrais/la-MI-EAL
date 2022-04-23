public class Person {
    private String firstName;
    private String lastName;
    private int age;

    Person(){
        this.firstName = "Armand";
        this.lastName = "Deff";
        this.age = 21;
    }
    Person(String firstName, String lastName, int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    //Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setAge(int age) {
        this.age = age;
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

    public void printInfo(){
        System.out.println(this.lastName + " " + this.firstName + ", " + this.age + " ans");
    }
}
