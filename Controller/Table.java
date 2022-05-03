import java.util.List;

public class Table {
    private int id;
    private Employee employeeAssigned;
    private Order tableOrder;
    private TABLE_STATUS tableStatus;
    private int nbSitAvailable;
    Table(int id, int nbSitAvailable){
        this.id = id;
        this.employeeAssigned = new Employee();
        this.tableOrder = new Order();
        this.tableStatus = TABLE_STATUS.FREE;
        this.nbSitAvailable = nbSitAvailable;
    }

    public TABLE_STATUS getTableStatus() {
        return tableStatus;
    }

    public Employee getEmployeeAssigned() {
        return employeeAssigned;
    }

    public int getId() {
        return id;
    }

    public Order getTableOrder() {
        return tableOrder;
    }

    public int getNbSitAvailable() {
        return nbSitAvailable;
    }

    public void setTableStatus(TABLE_STATUS tableStatus) {
        this.tableStatus = tableStatus;
    }
}
