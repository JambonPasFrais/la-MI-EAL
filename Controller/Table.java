import java.util.List;

public class Table {
    private TABLE_STATUS tableStatus;
    private boolean isStatusOk;//Signifie "ils ont besoin qu'on fasse qqc par rapport à leur status
    private int id;
    private Waiter waiterAssigned;
    private Order tableOrder;
    private List<Person> tableClients;

    public TABLE_STATUS getTableStatus() {
        return tableStatus;
    }

    public int getId() {
        return id;
    }

    public void setTableOrder(Order tableOrder) {
        this.tableOrder = tableOrder;
    }

    public void setTableStatus(TABLE_STATUS tableStatus) {
        this.tableStatus = tableStatus;
    }

    public void setStatusOk(boolean statusOk) {
        isStatusOk = statusOk;
    }

    public boolean isStatusOk() {
        return isStatusOk;
    }
}
