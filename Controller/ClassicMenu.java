import java.util.List;

public class ClassicMenu extends Menu{
    ClassicMenu(){
        super();
    }
    ClassicMenu(List<Meal> foodMenu, List<Meal>drinkMenu, boolean isAvailable, int menuPrice, MENU_EDITION name){
        super(foodMenu, drinkMenu, isAvailable, menuPrice, name);
    }
}
