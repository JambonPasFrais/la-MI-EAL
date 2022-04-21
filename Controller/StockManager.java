import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockManager {
    private Stock dailyStock;
    private Map<INGREDIENT_LIST, Integer>shoppingList;
    StockManager(){
        this.dailyStock = new Stock();
        this.shoppingList = new HashMap<>();
    }

    public void setDailyStock(Stock dailyStock) {
        this.dailyStock = dailyStock;
    }

    public Stock getDailyStock() {
        return dailyStock;
    }

    /*TODO
    returns the list of meals you have to change
     */
    public List<Meal> manageStocks (Order order){
        List<Meal> mealOutOfStocks = new ArrayList<>();
        for (Meal foodMeal : order.getFoodOrder()){
            if (this.isCookable(foodMeal)){
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
    public boolean isCookable(Meal anyTypeMeal){
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
    public void useIngredients(Meal anyTypeMeal){

    }
    public void generateShoppingList(){
        this.shoppingList = new HashMap<>();
        this.dailyStock.getStock().forEach((key, value) -> {
            if (key != INGREDIENT_LIST.BEER && key != INGREDIENT_LIST.LIMONADE && key != INGREDIENT_LIST.WATER && key != INGREDIENT_LIST.JUICE && key != INGREDIENT_LIST.CIDER){
                this.shoppingList.put(key, 150 - value.getNbIngredientsLefts());
            }
        });
    }
    public void reconstituateDailyStock(){
        this.dailyStock.getStock().forEach((key, value) -> {
            if (this.shoppingList.containsKey(key)){
                this.dailyStock.getStock().get(key).setNbIngredientsLefts(this.shoppingList.get(key) + this.dailyStock.getStock().get(key).getNbIngredientsLefts());
            }
        });
    }
    public void printShoppingList(){
        this.shoppingList.forEach((key, value)->{
            System.out.println(key.toString() + " " + this.shoppingList.get(key));
        });
    }
}
