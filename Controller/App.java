import java.io.*;
import java.util.*;

/*TODO
FAIRE DES TESTS !
essayer de comprendre pourquoi les ingrédients passent à -1
créer le restaurant manager
créer les employés et le employee management
relire le sujet :)
Rendez la console jolie c:
 */
public class App {
    public static void main(String[] args) throws IOException {
        /*TODO
        Manage orders to interact width stocks & meal preparation
         */

        /*Stock Creation*/
        StockManager stockManager = new StockManager();
        stockManager.getDailyStock().createStock();

        /*Menu Creation*/
        List<Meal>foodList = createFoodList();
        List<Meal>drinkList = createDrinkList();
        ClassicMenu classicMenu = new ClassicMenu(foodList, drinkList, true, -1, MENU_EDITION.CLASSIQUE);
        HundredYearsMenu hundredYearsMenu = new HundredYearsMenu(foodList, drinkList, true, MENU_EDITION.CENT_ANS);
        /*Time Creation*/
        Time gameInternalClock = new Time();//MON 10:00

        /*Manager*/
        int managerChoice = 0;

        /*Randomness*/
        Random r = new Random();

        /*Orders*/
        OrderManager orderManager = new OrderManager(classicMenu, hundredYearsMenu, stockManager.getDailyStock());
        int nbOrderAtTheSameTime;
        /*
        System.out.println("Quel écran souhaitez vous afficher?");
        System.out.println("1- Ecran prise de commande");
        System.out.println("2- Ecran cuisine");
        System.out.println("3- Ecran bar");
        System.out.println("4- Ecran Monitoring");
        Scanner scanner = new Scanner(System.in);
        int choixEcran = scanner.nextInt();
        System.out.println("Vous avez choisi l'écran: " + choixEcran);
        */
        stockManager.getDailyShoppingList().generateShoppingListFromStock(stockManager.getDailyStock(), 1500);
        stockManager.getDailyShoppingList().convertShoppingListIntoFile();
    }
    public static List<Meal> createFoodList(){
        List<Meal>menu = new ArrayList<>();
        menu.add(new Meal(0, "Salade César", new INGREDIENT_LIST[]{INGREDIENT_LIST.SALAD, INGREDIENT_LIST.TOMATO}, false, 10, MEAL_TYPE.VEGAN, 9));
        menu.add(new Meal(1, "Salade verte", new INGREDIENT_LIST[]{INGREDIENT_LIST.SALAD}, false, 5, MEAL_TYPE.VEGAN, 9));
        menu.add(new Meal(2, "Soupe d'oignon", new INGREDIENT_LIST[]{INGREDIENT_LIST.ONION, INGREDIENT_LIST.ONION, INGREDIENT_LIST.ONION}, false, 15, MEAL_TYPE.VEGAN, 8));
        menu.add(new Meal(3, "Soupe de tomate", new INGREDIENT_LIST[]{INGREDIENT_LIST.TOMATO, INGREDIENT_LIST.TOMATO, INGREDIENT_LIST.TOMATO}, false, 15, MEAL_TYPE.VEGAN, 8));
        menu.add(new Meal(4, "Soupe de champignon", new INGREDIENT_LIST[]{INGREDIENT_LIST.MUSHROOM, INGREDIENT_LIST.MUSHROOM, INGREDIENT_LIST.MUSHROOM}, false, 15, MEAL_TYPE.VEGAN, 8));
        menu.add(new Meal(5, "Burger complet", new INGREDIENT_LIST[]{INGREDIENT_LIST.BURGER_BREAD, INGREDIENT_LIST.SALAD, INGREDIENT_LIST.TOMATO, INGREDIENT_LIST.STEAK}, false, 20, MEAL_TYPE.OMNIVOROUS, 15));
        menu.add(new Meal(6, "Burger vert", new INGREDIENT_LIST[]{INGREDIENT_LIST.BURGER_BREAD, INGREDIENT_LIST.SALAD, INGREDIENT_LIST.STEAK}, false, 19, MEAL_TYPE.OMNIVOROUS, 15));
        menu.add(new Meal(7, "Burger simple", new INGREDIENT_LIST[]{INGREDIENT_LIST.BURGER_BREAD, INGREDIENT_LIST.STEAK}, false, 15, MEAL_TYPE.OMNIVOROUS, 15));
        menu.add(new Meal(8, "Pizza fromage", new INGREDIENT_LIST[]{INGREDIENT_LIST.PIZZA_DOUGH, INGREDIENT_LIST.CHEESE, INGREDIENT_LIST.TOMATO}, false, 25, MEAL_TYPE.VEGETARIAN, 12));
        menu.add(new Meal(9, "Pizza reine", new INGREDIENT_LIST[]{INGREDIENT_LIST.PIZZA_DOUGH, INGREDIENT_LIST.CHEESE, INGREDIENT_LIST.TOMATO, INGREDIENT_LIST.MUSHROOM}, false, 25, MEAL_TYPE.VEGETARIAN, 12));
        menu.add(new Meal(10, "Pizza chorizo", new INGREDIENT_LIST[]{INGREDIENT_LIST.PIZZA_DOUGH, INGREDIENT_LIST.CHEESE, INGREDIENT_LIST.TOMATO, INGREDIENT_LIST.SAUSAGE}, false, 28, MEAL_TYPE.OMNIVOROUS, 12));
        menu.add(new Meal(11, "Fajitas poulet", new INGREDIENT_LIST[]{INGREDIENT_LIST.PANCAKE, INGREDIENT_LIST.RICE, INGREDIENT_LIST.CHICKEN}, false, 22, MEAL_TYPE.OMNIVOROUS, 11));
        menu.add(new Meal(12, "Fajitas boeuf", new INGREDIENT_LIST[]{INGREDIENT_LIST.PANCAKE, INGREDIENT_LIST.RICE, INGREDIENT_LIST.STEAK}, false, 22, MEAL_TYPE.OMNIVOROUS, 11));
        return menu;
    }
    public static List<Meal> createDrinkList(){
        List<Meal>menu = new ArrayList<>();
        menu.add(new Meal(0, "Limonade", new INGREDIENT_LIST[]{INGREDIENT_LIST.LIMONADE}, false, 1, MEAL_TYPE.OMNIVOROUS, 4));
        menu.add(new Meal(1, "Cidre doux", new INGREDIENT_LIST[]{INGREDIENT_LIST.CIDER}, false, 1, MEAL_TYPE.OMNIVOROUS, 5));
        menu.add(new Meal(2, "Bière sans alcool", new INGREDIENT_LIST[]{INGREDIENT_LIST.BEER}, false, 1, MEAL_TYPE.OMNIVOROUS, 5));
        menu.add(new Meal(3, "Jus de fruit", new INGREDIENT_LIST[]{INGREDIENT_LIST.JUICE}, false, 1, MEAL_TYPE.OMNIVOROUS, 1));
        menu.add(new Meal(4, "Verre d'eau", new INGREDIENT_LIST[]{INGREDIENT_LIST.WATER}, false, 1, MEAL_TYPE.OMNIVOROUS, 0));
        return menu;
    }
    public static Map<INGREDIENT_LIST, Ingredient> createStock(){
        Map<INGREDIENT_LIST, Ingredient> stock = new HashMap<INGREDIENT_LIST, Ingredient>();
        INGREDIENT_LIST [] allIngredientName = {INGREDIENT_LIST.SALAD, INGREDIENT_LIST.TOMATO, INGREDIENT_LIST.ONION, INGREDIENT_LIST.MUSHROOM, INGREDIENT_LIST.BURGER_BREAD, INGREDIENT_LIST.STEAK, INGREDIENT_LIST.PIZZA_DOUGH, INGREDIENT_LIST.CHEESE, INGREDIENT_LIST.SAUSAGE, INGREDIENT_LIST.RICE, INGREDIENT_LIST.CHICKEN, INGREDIENT_LIST.PANCAKE, INGREDIENT_LIST.LIMONADE, INGREDIENT_LIST.CIDER, INGREDIENT_LIST.BEER, INGREDIENT_LIST.JUICE, INGREDIENT_LIST.WATER};
        for (int i = 0; i < allIngredientName.length; i++){
            Ingredient ingredient;
            if (allIngredientName[i] == INGREDIENT_LIST.LIMONADE || allIngredientName[i] == INGREDIENT_LIST.CIDER || allIngredientName[i] == INGREDIENT_LIST.BEER || allIngredientName[i] == INGREDIENT_LIST.JUICE || allIngredientName[i] == INGREDIENT_LIST.WATER){
                ingredient = new Ingredient(INGREDIENT_TYPE.DRINK, allIngredientName[i], -1);
            }
            else {
                ingredient = new Ingredient(INGREDIENT_TYPE.FOOD, allIngredientName[i], 150);
            }
            stock.put(allIngredientName[i], ingredient);
        }
        return stock;
    }
}