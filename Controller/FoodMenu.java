import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FoodMenu {
    private List<Meal> menu;
    FoodMenu (){
        this.menu = new ArrayList<Meal>();
        this.menu.add(new Meal(0, "Salade César", new INGREDIENT_LIST[]{INGREDIENT_LIST.SALAD, INGREDIENT_LIST.TOMATO}, false, 10, MEAL_TYPE.VEGAN, 9));
        this.menu.add(new Meal(1, "Salade verte", new INGREDIENT_LIST[]{INGREDIENT_LIST.SALAD}, false, 5, MEAL_TYPE.VEGAN, 9));
        this.menu.add(new Meal(2, "Soupe d'oignon", new INGREDIENT_LIST[]{INGREDIENT_LIST.ONION, INGREDIENT_LIST.ONION, INGREDIENT_LIST.ONION}, false, 15, MEAL_TYPE.VEGAN, 8));
        this.menu.add(new Meal(3, "Soupe de tomate", new INGREDIENT_LIST[]{INGREDIENT_LIST.TOMATO, INGREDIENT_LIST.TOMATO, INGREDIENT_LIST.TOMATO}, false, 15, MEAL_TYPE.VEGAN, 8));
        this.menu.add(new Meal(4, "Soupe de champignon", new INGREDIENT_LIST[]{INGREDIENT_LIST.MUSHROOM, INGREDIENT_LIST.MUSHROOM, INGREDIENT_LIST.MUSHROOM}, false, 15, MEAL_TYPE.VEGAN, 8));
        this.menu.add(new Meal(5, "Burger complet", new INGREDIENT_LIST[]{INGREDIENT_LIST.BURGER_BREAD, INGREDIENT_LIST.SALAD, INGREDIENT_LIST.TOMATO, INGREDIENT_LIST.STEAK}, false, 20, MEAL_TYPE.OMNIVOROUS, 15));
        this.menu.add(new Meal(6, "Burger vert", new INGREDIENT_LIST[]{INGREDIENT_LIST.BURGER_BREAD, INGREDIENT_LIST.SALAD, INGREDIENT_LIST.STEAK}, false, 19, MEAL_TYPE.OMNIVOROUS, 15));
        this.menu.add(new Meal(7, "Burger simple", new INGREDIENT_LIST[]{INGREDIENT_LIST.BURGER_BREAD, INGREDIENT_LIST.STEAK}, false, 15, MEAL_TYPE.OMNIVOROUS, 15));
        this.menu.add(new Meal(8, "Pizza fromage", new INGREDIENT_LIST[]{INGREDIENT_LIST.PIZZA_DOUGH, INGREDIENT_LIST.CHEESE, INGREDIENT_LIST.TOMATO}, false, 25, MEAL_TYPE.VEGETARIAN, 12));
        this.menu.add(new Meal(9, "Pizza reine", new INGREDIENT_LIST[]{INGREDIENT_LIST.PIZZA_DOUGH, INGREDIENT_LIST.CHEESE, INGREDIENT_LIST.TOMATO, INGREDIENT_LIST.MUSHROOM}, false, 25, MEAL_TYPE.VEGETARIAN, 12));
        this.menu.add(new Meal(10, "Pizza chorizo", new INGREDIENT_LIST[]{INGREDIENT_LIST.PIZZA_DOUGH, INGREDIENT_LIST.CHEESE, INGREDIENT_LIST.TOMATO, INGREDIENT_LIST.SAUSAGE}, false, 28, MEAL_TYPE.OMNIVOROUS, 12));
        this.menu.add(new Meal(11, "Fajitas poulet", new INGREDIENT_LIST[]{INGREDIENT_LIST.PANCAKE, INGREDIENT_LIST.RICE, INGREDIENT_LIST.CHICKEN}, false, 22, MEAL_TYPE.OMNIVOROUS, 11));
        this.menu.add(new Meal(12, "Fajitas boeuf", new INGREDIENT_LIST[]{INGREDIENT_LIST.PANCAKE, INGREDIENT_LIST.RICE, INGREDIENT_LIST.STEAK}, false, 22, MEAL_TYPE.OMNIVOROUS, 11));
    }
    FoodMenu(List<Meal>menu){
        this.menu = menu;
    }
    public void printMenu(){
        int i = 1;
        for (Meal meal : this.menu) {
            System.out.print(i + ": " + meal.getName() + ", composition: ");
            for (INGREDIENT_LIST ingredient : meal.getRecipe()){
                System.out.print(ingredient.toString() + " ");
            }
            System.out.print("\n");
            i++;
        }
    }
}
