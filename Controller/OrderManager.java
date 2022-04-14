import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManager {
    private List<Order>dayOrderList;
    private Scanner scanner;
    //New
    private ClassicMenu classicMenu;
    private HundredYearsMenu hundredYearsMenu;
    OrderManager(){
        this.dayOrderList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.classicMenu = new ClassicMenu();
        this.hundredYearsMenu = new HundredYearsMenu();
    }
    OrderManager(ClassicMenu classicMenu, HundredYearsMenu hundredYearsMenu){
        this.dayOrderList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.classicMenu = classicMenu;
        this.hundredYearsMenu = hundredYearsMenu;
    }
    public ClassicMenu getClassicMenu() {
        return classicMenu;
    }
    public HundredYearsMenu getHundredYearsMenu() {
        return hundredYearsMenu;
    }
    public List<Order> getDayOrderList() {
        return dayOrderList;
    }
    public Scanner getScanner() {
        return scanner;
    }
    public void setDayOrderList(List<Order> dayOrderList) {
        this.dayOrderList = dayOrderList;
    }
    public void addOrder(Order order){
        this.dayOrderList.add(order);
    }
    public void askForOrder(){
        //Création de la commande
        Order currentOrder = new Order();
        currentOrder.setIdOrder(this.dayOrderList.size());
        //Demande menu
        this.askForMenu();
        int menuChoice = this.scanner.nextInt();
        if (menuChoice == 1){
            currentOrder.setItASpecialOne(false);
        }
        else if (menuChoice == 2){
            currentOrder.setItASpecialOne(true);
        }
        else{
            System.out.println("Erreur");
            return;
        }

        //Demande food
        int choice = 0;//Default
        List<Meal>foodOrder = new ArrayList<>();
        do {
            this.askForFood();
            choice = this.scanner.nextInt();
            if (choice != -1){
                Meal askedMeal = this.classicMenu.getFoodMenuItem(choice - 1);
                foodOrder.add(askedMeal);
            }
            this.printFoodOrder(foodOrder);
        }while(choice != -1);
        currentOrder.setFoodOrder(foodOrder);

        System.out.println("On passe maintenant aux plats");

        //demande drink
        choice = 0;//Default
        List<Meal>drinkOrder = new ArrayList<>();
        do {
            this.askForDrink();
            choice = this.scanner.nextInt();
            if (choice != -1){
                Meal askedMeal = this.classicMenu.getDrinkMenuItem(choice - 1);
                drinkOrder.add(askedMeal);
            }
            this.printDrinkOrder(drinkOrder);
        }while(choice != -1);
        currentOrder.setDrinkOrder(drinkOrder);

        System.out.print("Merci pour vos choix:\n");
        this.printFoodOrder(foodOrder);
        this.printDrinkOrder(drinkOrder);
        System.out.print(".\n");
    }
    public void askForMenu(){
        System.out.println("Quel type de menu souhaitez-vous ?\n1:Menu classique\n2: Menu 100 ans");
    }
    public void askForFood(){
        System.out.format("Que voulez-vous comme plat(s) ? (-1 pour quitter)\n");
        this.classicMenu.printOneMenuType(this.classicMenu.getFoodMenu());
    }

    public void askForDrink(){
        System.out.format("Que voulez-vous comme boisson(s) ? (-1 pour quitter)\n");
        this.classicMenu.printOneMenuType(this.classicMenu.getDrinkMenu());
    }
    /*TODO
    rendre les 2 fonctions suivantes en 1
     */
    public void printFoodOrder(List<Meal>foodOrder){
        System.out.print("Choix:");
        for (Meal meal:foodOrder){
            System.out.print(" / " + meal.getName());
        }
        System.out.print(".\n");
    }
    public void printDrinkOrder(List<Meal>drinkOrder){
        System.out.print("Choix:");
        for (Meal meal:drinkOrder){
            System.out.print(" " + meal.getName());
        }
        System.out.print(".\n");
    }
}
