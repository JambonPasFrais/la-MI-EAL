import java.util.ArrayList;
import java.util.List;

public class Order {
    private int idOrder;
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
    public void setServed(boolean served) {
        isServed = served;
    }
    public int getIdOrder() {
        return idOrder;
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
