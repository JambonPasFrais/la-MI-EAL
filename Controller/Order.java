import java.util.ArrayList;
import java.util.List;

public class Order {
    private int idOrder;
    private INGREDIENT_TYPE orderType;
    private List<Meal>foodOrder;
    private List<Meal>drinkOrder;
    private boolean isItASpecialOne;
    Order(){
        this.idOrder = 0;
        this.orderType = INGREDIENT_TYPE.BOTH;
        this.foodOrder = new ArrayList<>();
        this.drinkOrder = new ArrayList<>();
        this.isItASpecialOne = false;
    }

    Order(int idOrder, INGREDIENT_TYPE orderType, List<Meal>foodOrder, List<Meal>drinkOrder, boolean isItASpecialOne){
        this.orderType = orderType;
        this.foodOrder = foodOrder;
        this.drinkOrder = drinkOrder;
        this.isItASpecialOne = isItASpecialOne;
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

    public void setItASpecialOne(boolean itASpecialOne) {
        isItASpecialOne = itASpecialOne;
    }

    public void setOrderType(INGREDIENT_TYPE orderType) {
        this.orderType = orderType;
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
}
