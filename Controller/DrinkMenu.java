import java.util.ArrayList;
import java.util.List;

public class DrinkMenu {
    private List<Meal> menu;
    DrinkMenu(List<Meal>menu){
        this.menu = menu;
    }
    DrinkMenu(){
        this.menu = new ArrayList<Meal>();
        this.menu.add(new Meal(0, "Limonade", new INGREDIENT_LIST[]{INGREDIENT_LIST.LIMONADE}, false, 1, MEAL_TYPE.OMNIVOROUS, 4));
        this.menu.add(new Meal(1, "Cidre doux", new INGREDIENT_LIST[]{INGREDIENT_LIST.CIDER}, false, 1, MEAL_TYPE.OMNIVOROUS, 5));
        this.menu.add(new Meal(2, "Bière sans alcool", new INGREDIENT_LIST[]{INGREDIENT_LIST.BEER}, false, 1, MEAL_TYPE.OMNIVOROUS, 5));
        this.menu.add(new Meal(3, "Jus de fruit", new INGREDIENT_LIST[]{INGREDIENT_LIST.JUICE}, false, 1, MEAL_TYPE.OMNIVOROUS, 1));
        this.menu.add(new Meal(4, "Verre d'eau", new INGREDIENT_LIST[]{INGREDIENT_LIST.WATER}, false, 1, MEAL_TYPE.OMNIVOROUS, 0));
    }
    public void printMenu(){
        int i = 1;
        for (Meal meal : this.menu) {
            System.out.print(i + ": " + meal.getName() + " ");
            for (INGREDIENT_LIST ingredient : meal.getRecipe()){
                System.out.print(ingredient.toString() + " ");
            }
            System.out.print("\n");
            i++;
        }
    }
}
