import java.util.ArrayList;
import java.util.List;

public class Order {
    private int idOrder;
    /*TODO
    Make orderType
     */
    private INGREDIENT_TYPE orderType;
    private List<Meal>foodOrder;
    private List<Meal>drinkOrder;
    private MENU_EDITION menuEdition;
    private boolean isServed;
    private Invoice orderInvoice;
    Order(){
        this.idOrder = 0;
        this.orderType = INGREDIENT_TYPE.BOTH;
        this.foodOrder = new ArrayList<>();
        this.drinkOrder = new ArrayList<>();
        this.menuEdition = MENU_EDITION.CLASSIQUE;
        this.isServed = false;
        this.orderInvoice = new Invoice();
    }

    Order(int idOrder, INGREDIENT_TYPE orderType, List<Meal>foodOrder, List<Meal>drinkOrder, MENU_EDITION menuEdition){
        this.orderType = orderType;
        this.foodOrder = foodOrder;
        this.drinkOrder = drinkOrder;
        this.menuEdition = menuEdition;
        this.isServed = false;
        this.orderInvoice = new Invoice();
    }

    public void setDrinkOrder(List<Meal> drinkOrder) {
        this.drinkOrder = drinkOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public void setFoodOrder(List<Meal> foodOrder) {
        this.foodOrder = foodOrder;
    }

    public void setMenuEdition(MENU_EDITION menuEdition) {
        this.menuEdition = menuEdition;
    }

    public void setOrderType(INGREDIENT_TYPE orderType) {
        this.orderType = orderType;
    }

    public void setOrderInvoice(Invoice orderInvoice) {
        this.orderInvoice = orderInvoice;
    }

    public void setServed(boolean served) {
        isServed = served;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public Invoice getOrderInvoice() {
        return orderInvoice;
    }

    public boolean isServed() {
        return isServed;
    }

    public List<Meal> getDrinkOrder() {
        return drinkOrder;
    }

    public List<Meal> getFoodOrder() {
        return foodOrder;
    }

    public MENU_EDITION getMenuEdition() {
        return this.menuEdition;
    }

    public void makeInvoice(){
        this.orderInvoice.createInvoiceDetails(this.foodOrder, this.drinkOrder, this.menuEdition);
        this.orderInvoice.createInvoiceFile(this.idOrder);
    }
    public void printOneOrderType(List<Meal>order){
        System.out.print("Vous avez commandé: ");
        for (int i = 0; i < order.size(); i++) {
            if (i == order.size()-1){
                System.out.print(order.get(i).getName() + ".");
            }
            else {
                System.out.print(order.get(i).getName() + ", ");
            }
        }
        System.out.print("\n");
    }
    public boolean isOrderReady(){
        boolean isOrderReadyTemp = true;
        for(Meal drink: this.drinkOrder){
            if (!drink.isMealReady()) {
                isOrderReadyTemp = false;
                break;
            }
        }
        for(Meal food: this.foodOrder){
            if(!food.isMealReady()){
                isOrderReadyTemp = false;
                break;
            }
        }
        return isOrderReadyTemp;
    }
}
