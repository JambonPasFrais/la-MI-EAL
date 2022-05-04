import java.util.List;

public class MenuManager {
    private ClassicMenu classicMenu;
    private HundredYearsMenu hundredYearsMenu;
    MenuManager(List<Meal>foodList, List<Meal>drinkList){
        this.classicMenu = new ClassicMenu(foodList, drinkList, true, -1, MENU_EDITION.CLASSIQUE);
        this.hundredYearsMenu = new HundredYearsMenu(foodList, drinkList, true, MENU_EDITION.CENT_ANS);
    }
    public ClassicMenu getClassicMenu() {
        return classicMenu;
    }
    public HundredYearsMenu getHundredYearsMenu() {
        return hundredYearsMenu;
    }
}
