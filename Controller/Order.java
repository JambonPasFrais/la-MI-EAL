import java.util.List;

public class Order {
    private int idOrder;
    private INGREDIENT_TYPE orderType;
    private List<Meal>foodOrder;
    private List<Meal>drinkOrder;
    private boolean isItASpecialOne;
    Order(int idOrder, INGREDIENT_TYPE orderType, List<Meal>foodOrder, List<Meal>drinkOrder, boolean isItASpecialOne){
        this.orderType = orderType;
        this.foodOrder = foodOrder;
        this.drinkOrder = drinkOrder;
        this.isItASpecialOne = isItASpecialOne;
    }
}
