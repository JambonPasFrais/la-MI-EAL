import java.util.ArrayList;
import java.util.List;

/*
Un menu contient une liste de boisson et une liste de plat avec certaines spécificités
 */
public class Menu {
    private List<Meal> foodMenu;
    private List<Meal> drinkMenu;
    private boolean isAvailable;
    private int menuPrice;
    Menu(){
      this.foodMenu = new ArrayList<Meal>();
      this.drinkMenu = new ArrayList<Meal>();
      this.isAvailable = false;
      this.menuPrice = -1;//Default
    }
    Menu(List<Meal>foodMenu, List<Meal>drinkMenu, boolean isAvailable, int menuPrice){
        this.foodMenu = foodMenu;
        this.drinkMenu = drinkMenu;
        this.isAvailable = isAvailable;
        this.menuPrice = menuPrice;
    }
    void addFoodMenuItem(Meal meal){
        this.foodMenu.add(meal);
    }
    void addDrinkMenuItem(Meal meal){
        this.drinkMenu.add(meal);
    }
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
    public void setMenuPrice(int menuPrice){
        this.menuPrice = menuPrice;
    }
    public Meal getFoodMenuItem(int id){
        return this.foodMenu.get(id);
    }
    public Meal getDrinkMenuItem(int id){
        return this.drinkMenu.get(id);
    }
    public List<Meal> getDrinkMenu() {
        return drinkMenu;
    }
    public List<Meal> getFoodMenu() {
        return foodMenu;
    }
    public boolean getIsAvailable(){
        return this.isAvailable;
    }
    public int getMenuPrice() {
        return menuPrice;
    }
    public void printOneMenu(List<Meal>Menu){
        int i = 1;
        for (Meal meal : Menu) {
            System.out.print(i + ": " + meal.getName() + ", composition: ");
            for (INGREDIENT_LIST ingredient : meal.getRecipe()){
                System.out.print(ingredient.toString() + " ");
            }
            System.out.print("\n");
            i++;
        }
    }
}
