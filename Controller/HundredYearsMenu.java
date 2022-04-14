import java.util.List;

public class HundredYearsMenu extends Menu {
    private int maximumFoodQuantity;
    private int maximumDrinkQuantity;
    HundredYearsMenu(){
        super();
        this.setMenuPrice(100);
        this.maximumFoodQuantity = 7;
        this.maximumDrinkQuantity = 7;
    }
    HundredYearsMenu(List<Meal> foodMenu, List<Meal>drinkMenu, boolean isAvailable){
        super(foodMenu, drinkMenu, isAvailable, 100);
        this.maximumFoodQuantity = 7;
        this.maximumDrinkQuantity = 7;
    }
}
