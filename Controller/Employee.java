import java.util.ArrayList;
import java.util.List;

public class Employee extends Person{
    private EmployeeStatus employeeStatus;
    private JOB_TYPE job;
    private Contract contract;
    private List<Integer> dayWorked;

    Employee(){
        super();
        this.employeeStatus = new EmployeeStatus();
        this.job = JOB_TYPE.WAITER;
        this.contract = new Contract();
        this.dayWorked = new ArrayList<>();
    }
    Employee(String firstName, String lastName, int age, EmployeeStatus employeeStatus, JOB_TYPE job, Contract contract){
        super(firstName, lastName, age);
        this.employeeStatus = employeeStatus;
        this.job = job;
        this.contract = contract;
        this.dayWorked = new ArrayList<>();
    }
    //G&S

    public void setJob(JOB_TYPE job) {
        this.job = job;
    }

    public JOB_TYPE getJob() {
        return job;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<Integer> getDayWorked() {
        return dayWorked;
    }

    public void setDayWorked(List<Integer> dayWorked) {
        this.dayWorked = dayWorked;
    }
}
