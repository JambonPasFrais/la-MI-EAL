import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Meal> foodMenu;
    private List<Meal> drinkMenu;
    private boolean isAvailable;
    private int menuPrice;
    private MENU_EDITION name;
    Menu(){
      this.foodMenu = new ArrayList<>();
      this.drinkMenu = new ArrayList<>();
      this.isAvailable = false;
      this.menuPrice = -1;//Default
        this.name = MENU_EDITION.CLASSIQUE;
    }
    Menu(List<Meal>foodMenu, List<Meal>drinkMenu, boolean isAvailable, int menuPrice, MENU_EDITION name){
        this.foodMenu = foodMenu;
        this.drinkMenu = drinkMenu;
        this.isAvailable = isAvailable;
        this.menuPrice = menuPrice;
        this.name = name;
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
    public MENU_EDITION getName() {
        return name;
    }
    public void printOneMenuType(List<Meal>menu){
        int i = 1;
        for (Meal meal : menu) {
            System.out.print(i + ": " + meal.getName() + ", composition: ");
            for (INGREDIENT_LIST ingredient : meal.getRecipe()){
                System.out.print(ingredient.toString() + " ");
            }
            System.out.print("\n");
            i++;
        }
    }
    public void updateMenuFromStock(Stock stock){
        List<Meal> mealToDeleteTab = new ArrayList<>();
        for (Meal meal: this.foodMenu) {
            if (!stock.isMealCookable(meal)) {
                mealToDeleteTab.add(meal);
            }
        }
        for (Meal mealToDelete: mealToDeleteTab){
            this.foodMenu.remove(mealToDelete);
        }
    }
}
