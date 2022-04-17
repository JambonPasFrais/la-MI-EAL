import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*TODO
* Gérer les Scanner de manière rnadom (on ne doit pas interagir du point de vue client)
* Factures dans les fichiers (créer un fichier par facture)
* */

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
            currentOrder.setMenuEdition(MENU_EDITION.CLASSIQUE);
        }
        else if (menuChoice == 2){
            currentOrder.setMenuEdition(MENU_EDITION.CENT_ANS);
        }
        else{
            System.out.println("Erreur");
            return;
        }
        System.out.println(currentOrder.getMenuEdition() + " " + this.hundredYearsMenu.getName());
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
            this.printOrder(foodOrder);
        }while((currentOrder.getMenuEdition() == this.hundredYearsMenu.getName() &&  foodOrder.size() < this.hundredYearsMenu.getMaximumFoodQuantity()) || (choice != -1 && currentOrder.getMenuEdition()== this.classicMenu.getName()));
        currentOrder.setFoodOrder(foodOrder);

        System.out.println("On passe maintenant aux boissons");

        //Demande drink
        choice = 0;//Default
        List<Meal>drinkOrder = new ArrayList<>();
        do {
            this.askForDrink();
            choice = this.scanner.nextInt();
            if (choice != -1){
                Meal askedMeal = this.classicMenu.getDrinkMenuItem(choice - 1);
                drinkOrder.add(askedMeal);
            }
            this.printOrder(drinkOrder);
        }while((currentOrder.getMenuEdition() == this.hundredYearsMenu.getName() &&  drinkOrder.size() < this.hundredYearsMenu.getMaximumDrinkQuantity()) || (choice != -1 && currentOrder.getMenuEdition() == this.classicMenu.getName()));
        currentOrder.setDrinkOrder(drinkOrder);

        this.dayOrderList.add(currentOrder);

        System.out.print("Merci pour vos choix !\n");
    }
    public void prepareOrder(){
        for (int i = 0; i < this.dayOrderList.size(); i++){
            if (!this.dayOrderList.get(i).isServed()){
                //Make the bill
                this.dayOrderList.get(i).makeInvoice();
                //interact with stocks
                /*TODO

                 */
                //End of treatment
                this.dayOrderList.get(i).setServed(true);
            }
        }
    }
    public void askForMenu(){
        System.out.println("Quel type de menu souhaitez-vous ?\n1: Menu " + MENU_EDITION.CLASSIQUE.toString() + "\n2: Menu " + MENU_EDITION.CENT_ANS.toString());
    }
    public void askForFood(){
        System.out.format("Que voulez-vous comme plat(s) ? (-1 pour quitter)\n");
        this.classicMenu.printOneMenuType(this.classicMenu.getFoodMenu());
    }

    public void askForDrink(){
        System.out.format("Que voulez-vous comme boisson(s) ? (-1 pour quitter)\n");
        this.classicMenu.printOneMenuType(this.classicMenu.getDrinkMenu());
    }

    public void printOrder(List<Meal>order){
        System.out.print("Choix: ");
        for (Meal meal:order){
            System.out.print(" / " + meal.getName());
        }
        System.out.print(".\n");
    }
}
