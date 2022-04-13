import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stock {
    private Map<INGREDIENT_LIST, Ingredient> stock = new HashMap<INGREDIENT_LIST, Ingredient>();
    private INGREDIENT_LIST [] allIngredientName = {INGREDIENT_LIST.SALAD, INGREDIENT_LIST.TOMATO, INGREDIENT_LIST.ONION, INGREDIENT_LIST.MUSHROOM, INGREDIENT_LIST.BURGER_BREAD, INGREDIENT_LIST.STEAK, INGREDIENT_LIST.PIZZA_DOUGH, INGREDIENT_LIST.CHEESE, INGREDIENT_LIST.SAUSAGE, INGREDIENT_LIST.RICE, INGREDIENT_LIST.CHICKEN, INGREDIENT_LIST.PANCAKE, INGREDIENT_LIST.LIMONADE, INGREDIENT_LIST.CIDER, INGREDIENT_LIST.BEER, INGREDIENT_LIST.JUICE, INGREDIENT_LIST.WATER};
    Stock(){
        for (int i = 0; i < allIngredientName.length; i++){
            Ingredient ingredient;
            if (this.allIngredientName[i] == INGREDIENT_LIST.LIMONADE || this.allIngredientName[i] == INGREDIENT_LIST.CIDER || this.allIngredientName[i] == INGREDIENT_LIST.BEER || this.allIngredientName[i] == INGREDIENT_LIST.JUICE || this.allIngredientName[i] == INGREDIENT_LIST.WATER){
                ingredient = new Ingredient(INGREDIENT_TYPE.DRINK, this.allIngredientName[i], -1);
            }
            else {
                ingredient = new Ingredient(INGREDIENT_TYPE.FOOD, this.allIngredientName[i], 5);
            }
            this.stock.put(this.allIngredientName[i], ingredient);
        }
    }
    Stock(Map<INGREDIENT_LIST, Ingredient>stock){
        this.stock = stock;
    }
    //Methodes
    public void deleteStockItem(INGREDIENT_LIST aliment){
        this.stock.get(aliment).setNbIngredientsLefts(this.stock.get(aliment).getNbIngredientsLefts() - 1);
    }
    public void addStockItem(INGREDIENT_LIST aliment){
        this.stock.get(aliment).setNbIngredientsLefts(this.stock.get(aliment).getNbIngredientsLefts() + 1);
    }
    public void printStock(){
        for (int i = 0; i < stock.size(); i++){
            System.out.println(this.stock.get(this.allIngredientName[i]).getNameIngredient() + " " + this.stock.get(this.allIngredientName[i]).getNbIngredientsLefts());
        }
    }
}
