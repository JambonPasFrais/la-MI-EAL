public class Employee extends Person{
    private final PersonPrinter personPrinter;
    private EmployeeStatus employeeStatus;
    private String job;
    private Contract contract;

    Employee(String firstName, String lastName, int age, PersonPrinter personPrinter, EmployeeStatus employeeStatus, String job, Contract contract){
        super(firstName, lastName, age);
        this. personPrinter = personPrinter;
        this.employeeStatus = employeeStatus;
        this.job = job;
        this.contract = contract;
    }
    //G&S
    public PersonPrinter getPersonPrinter(){
        return this.personPrinter;
    }
}
