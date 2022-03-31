public class EmployeePrinter {
    Employee employee;
    public EmployeePrinter(Employee employee){
        this.employee = employee;
    }
    public void print(){
        this.employee.getPersonPrinter().print();
        System.out.println("\n");
    }
}
