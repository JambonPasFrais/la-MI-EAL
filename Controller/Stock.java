import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Stock {
    private Map<INGREDIENT_LIST, Ingredient> stock;
    Stock(){
        this.stock = new HashMap<>();
    }
    Stock(Map<INGREDIENT_LIST, Ingredient>stock){
        this.stock = stock;
    }

    public  void createStock(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File("stock.txt")));
            String line = "";
            while ((line = reader.readLine())!= null){
                INGREDIENT_TYPE ingredient_type = INGREDIENT_TYPE.BOTH;
                String [] str = line.split(" ");
                Ingredient ingredient = new Ingredient(ingredient_type, INGREDIENT_LIST.valueOf(str[0]), Integer.parseInt(str[1]));
                ingredient.setTypeIngredient(ingredient.getIngredientTypeDependingOnIngredientList(INGREDIENT_LIST.valueOf(str[0])));
                this.stock.put(INGREDIENT_LIST.valueOf(str[0]), ingredient);
            }
            reader.close();
        }catch (Exception e){
            System.err.println("Error " + e.getMessage());
        }
    }

    public Map<INGREDIENT_LIST, Ingredient> getStock() {
        return stock;
    }

    public void setStock(Map<INGREDIENT_LIST, Ingredient> stock) {
        this.stock = stock;
    }

    public void useStockItem(INGREDIENT_LIST ingredient, int quantityUsed){
        if (quantityUsed >= 1 && this.stock.get(ingredient).getNbIngredientsLefts() >= 0){
            this.stock.get(ingredient).setNbIngredientsLefts(this.stock.get(ingredient).getNbIngredientsLefts() - quantityUsed);
        }
        else {
            System.err.println("Quantity Used is <=0");
        }
    }
    public void refillStockItem(INGREDIENT_LIST ingredient, int nbOfIngredientRefilled){
        if (nbOfIngredientRefilled >= 1){
            this.stock.get(ingredient).setNbIngredientsLefts(this.stock.get(ingredient).getNbIngredientsLefts() + nbOfIngredientRefilled);
        }
        else {
            System.err.println("Quantity is <=0");
        }
    }
    public void printStock(){
        this.stock.forEach((key, value) -> System.out.println(key.toString() + " " + value.getNbIngredientsLefts()));
    }
    public void updateStockForOneMeal(Meal meal){
        for (INGREDIENT_LIST ingredientUsed : meal.getRecipe()){
            this.useStockItem(ingredientUsed, 1);
        }
    }
    public boolean isMealCookable(Meal meal){
        Stock tempStock = new Stock();
        tempStock.setStock(this.stock);
        for (INGREDIENT_LIST ingredientUsable:meal.getRecipe()){
            if (tempStock.getStock().get(ingredientUsable).getNbIngredientsLefts() <= 0){
                return false;
            }
            else {
                tempStock.useStockItem(ingredientUsable, 1);
            }
        }
        return true;
    }
}
