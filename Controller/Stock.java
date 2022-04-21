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

    public Map<INGREDIENT_LIST, Ingredient> getStock() {
        return stock;
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
        }    }
    public void printStock(){
        this.stock.forEach((key, value) -> System.out.println(key.toString() + " " + value.getNbIngredientsLefts()));
    }
}
