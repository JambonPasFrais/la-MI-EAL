import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockManager {
    private Stock dailyStock;
    private ShoppingList dailyShoppingList;
    StockManager(){
        this.dailyStock = new Stock();
        this.dailyShoppingList = new ShoppingList();
    }
    public Stock getDailyStock() {
        return dailyStock;
    }
    public ShoppingList getDailyShoppingList() {
        return dailyShoppingList;
    }
    public List<Meal> manageStocks (Order order){
        List<Meal> mealOutOfStocks = new ArrayList<>();
        for (Meal foodMeal : order.getFoodOrder()){
            if (this.isCookableConsideringStock(foodMeal)){
                for (INGREDIENT_LIST ingredientUsed: foodMeal.getRecipe()){
                    this.dailyStock.useStockItem(ingredientUsed, 1);
                }
            }
            else {
                mealOutOfStocks.add(foodMeal);
            }
        }
        return mealOutOfStocks;
    }
    public boolean isCookableConsideringStock(Meal anyTypeMeal){
        //Create a tempStock to manipulate
        Map<INGREDIENT_LIST, Integer> tempStock = new HashMap<>();
        for (INGREDIENT_LIST ingredient:anyTypeMeal.getRecipe()){
            tempStock.put(ingredient, this.getDailyStock().getStock().get(ingredient).getNbIngredientsLefts());
        }
        //Check if we have enough ingredient for a meal
        for (INGREDIENT_LIST ingredientNeeded : anyTypeMeal.getRecipe()){
            if (tempStock.get(ingredientNeeded) == 0){
                return false;//Dont have enough
            }

            else {
                tempStock.replace(ingredientNeeded, tempStock.get(ingredientNeeded) - 1);
            }
        }
        return true;//Have enough
    }
}
