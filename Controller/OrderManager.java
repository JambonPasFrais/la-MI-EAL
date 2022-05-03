import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*TODO
* Pour gérer comment savoir si on a encore assez de ressources pour créer tel ou tel plat, à chaque demande d'un plat par un client on va interagir avec les stocks et on saura ainsi en direct si on dispose d'assez d'ingrédients pour satiusfaire sa demande
* */

public class OrderManager {
    private List<Order>dayOrderList;
    private Scanner scanner;
    OrderManager(){
        this.dayOrderList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
    public List<Order> getDayOrderList() {
        return dayOrderList;
    }
    public Scanner getScanner() {
        return scanner;
    }
    public void takeOrderFromTable(ClassicMenu classicMenu, HundredYearsMenu hundredYearsMenu, Stock stock){
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
        //Demande food
        int choice = 0;//Default
        List<Meal>foodOrder = new ArrayList<>();
        do {
            classicMenu.updateMenuFromStock(stock);
            hundredYearsMenu.updateMenuFromStock(stock);
            this.askForFood(classicMenu);
            choice = this.scanner.nextInt();
            if (choice > 0 && choice <= classicMenu.getFoodMenu().size()){
                Meal askedMeal = classicMenu.getFoodMenuItem(choice - 1);
                foodOrder.add(askedMeal);
                stock.updateStockForOneMeal(askedMeal);
            }
            this.printOrder(foodOrder);
        }while((currentOrder.getMenuEdition() == hundredYearsMenu.getName() &&  foodOrder.size() < hundredYearsMenu.getMaximumFoodQuantity()) || (choice != -1 && currentOrder.getMenuEdition()== classicMenu.getName()));
        currentOrder.setFoodOrder(foodOrder);

        System.out.println("On passe maintenant aux boissons");

        //Demande drink
        choice = 0;//Default
        List<Meal>drinkOrder = new ArrayList<>();
        do {
            this.askForDrink(classicMenu);
            choice = this.scanner.nextInt();
            if (choice > 0 && choice <= classicMenu.getDrinkMenu().size()){
                Meal askedMeal = classicMenu.getDrinkMenuItem(choice - 1);
                drinkOrder.add(askedMeal);
            }
            this.printOrder(drinkOrder);
        }while((currentOrder.getMenuEdition() == hundredYearsMenu.getName() &&  drinkOrder.size() < hundredYearsMenu.getMaximumDrinkQuantity()) || (choice != -1 && currentOrder.getMenuEdition() == classicMenu.getName()));
        currentOrder.setDrinkOrder(drinkOrder);

        currentOrder.makeInvoice();
        this.dayOrderList.add(currentOrder);

        System.out.print("Merci pour vos choix !\n");
    }
    public void askForMenu(){
        System.out.println("Quel type de menu souhaitez-vous ?\n1: Menu " + MENU_EDITION.CLASSIQUE.toString() + "\n2: Menu " + MENU_EDITION.CENT_ANS.toString());
    }
    public void askForFood(ClassicMenu classicMenu){
        System.out.format("Que voulez-vous comme plat(s) ? (-1 pour quitter)\n");
        classicMenu.printOneMenuType(classicMenu.getFoodMenu());
    }
    public void askForDrink(ClassicMenu classicMenu){
        System.out.format("Que voulez-vous comme boisson(s) ? (-1 pour quitter)\n");
        classicMenu.printOneMenuType(classicMenu.getDrinkMenu());
    }
    public void printOrder(List<Meal>order){
        System.out.print("Choix: ");
        for (Meal meal:order){
            System.out.print(" / " + meal.getName());
        }
        System.out.print(".\n");
    }
}
