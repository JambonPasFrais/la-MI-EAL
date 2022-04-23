import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Waiter extends Employee{
    private Map<Integer,Table> tableAssigned;
    Waiter(){
        this.tableAssigned = new HashMap<>();
    }
    public void assignTableOrder(Table table, Order order){
        if (table.getTableStatus() == TABLE_STATUS.ORDERING && !table.isStatusOk()){
            this.tableAssigned.get(table.getId()).setTableOrder(order);
            this.tableAssigned.get(table.getId()).setStatusOk(true);
        }
    }

    public Map<Integer, Table> getTableAssigned() {
        return tableAssigned;
    }

    public void setTableAssigned(Map<Integer, Table> tableAssigned) {
        this.tableAssigned = tableAssigned;
    }
}
