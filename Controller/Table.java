import java.util.ArrayList;

public class Table {
    private int idTable;
    private int nbCustomersMax;
    private int nbCustomers; // si nbCustomers = 0, la table n'est pas occupée
    private ArrayList<Order> ordersList;
    private boolean isDoneEating;
    private int bill;
    private int howManyPersonPay;


}
