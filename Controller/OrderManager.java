import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*TODO
* Pour gérer comment savoir si on a encore assez de ressources pour créer tel ou tel plat, à chaque demande d'un plat par un client on va interagir avec les stocks et on saura ainsi en direct si on dispose d'assez d'ingrédients pour satiusfaire sa demande
* */

public class OrderManager {
    private List<Order>dayOrderList;
    private Scanner scanner;
    private ClassicMenu classicMenu;
    private HundredYearsMenu hundredYearsMenu;
    private Stock stock;
    OrderManager(){
        this.dayOrderList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.classicMenu = new ClassicMenu();
        this.hundredYearsMenu = new HundredYearsMenu();
        this.stock = new Stock();
    }
    OrderManager(ClassicMenu classicMenu, HundredYearsMenu hundredYearsMenu, Stock stock){
        this.dayOrderList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.classicMenu = classicMenu;
        this.hundredYearsMenu = hundredYearsMenu;
        this.stock = stock;
    }

    public Stock getStock() {
        return stock;
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
    public void setStock(Stock stock) {
        this.stock = stock;
    }
    public void setDayOrderList(List<Order> dayOrderList) {
        this.dayOrderList = dayOrderList;
    }
    public void addOrder(Order order){
        this.dayOrderList.add(order);
    }
    public void setClassicMenu(ClassicMenu classicMenu) {
        this.classicMenu = classicMenu;
    }
    public void setHundredYearsMenu(HundredYearsMenu hundredYearsMenu) {
        this.hundredYearsMenu = hundredYearsMenu;
    }

    public void generateOrder(){

        //Var
        Random r = new Random();
        int choice, max, min = 0, foodMenuSize, drinkMenuSize;
        List<Meal>foodOrder = new ArrayList<>();
        List<Meal>drinkOrder = new ArrayList<>();

        //Création de la commande
        Order currentOrder = new Order();
        currentOrder.setIdOrder(this.dayOrderList.size());

        //Menu choice
        max = 1;
        choice = r.nextInt((max - min) + 1) + min;
        if (choice == 0){
            currentOrder.setMenuEdition(MENU_EDITION.CLASSIQUE);

            max = 7; min = 1;
            foodMenuSize = r.nextInt((max - min) + 1) + min;

            min = foodMenuSize;max = (int) (foodMenuSize + Math.round(0.5 * foodMenuSize));
            drinkMenuSize = r.nextInt((max - min) + 1) + min;
        }
        else {
            currentOrder.setMenuEdition(MENU_EDITION.CENT_ANS);
            foodMenuSize = this.hundredYearsMenu.getMaximumFoodQuantity();
            drinkMenuSize = this.hundredYearsMenu.getMaximumDrinkQuantity();
        }
        System.out.println("Menu edition : " + currentOrder.getMenuEdition());

        //Food Menu Choice
        for (int i = 0; i < foodMenuSize; i++){
            //Menu Update
            if (currentOrder.getMenuEdition() == MENU_EDITION.CLASSIQUE){
                this.updateClassicMenu();
            }
            else{
                this.updateHundredYearsMenu();
            }

            //special case if out of Stocks during an order
            if (this.classicMenu.getFoodMenu().size() < 1 || this.hundredYearsMenu.getFoodMenu().size() < 1){
                System.out.println("Rupture de Stock Totale");
                return;
            }

            //Guest Choice (random)
            choice = r.nextInt(this.classicMenu.getFoodMenu().size());//0 to size-1 included;

            //We have to create a new variable each time otherwise if we modify the classicMenu it will modify every order !! (Object things)
            Meal askedMeal = this.classicMenu.getFoodMenuItem(choice);
            foodOrder.add(askedMeal);

            //Stock update
            this.stock.updateStockForOneMeal(askedMeal);
        }
        currentOrder.setFoodOrder(foodOrder);
        currentOrder.printOneOrderType(currentOrder.getFoodOrder());

        //Drink menu Choice
        for (int i = 0; i < drinkMenuSize; i++){
            choice = r.nextInt(this.classicMenu.getDrinkMenu().size());//0 to size-1 included;
            //We have to create a new variable each time otherwise if we modify the classicMenu it will modify every order !! (Object things)
            Meal askedMeal = this.classicMenu.getDrinkMenuItem(choice);
            drinkOrder.add(askedMeal);
        }
        currentOrder.setDrinkOrder(drinkOrder);
        currentOrder.printOneOrderType(currentOrder.getDrinkOrder());

        //Order generated
        this.dayOrderList.add(currentOrder);
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
    public void updateClassicMenu(){
        List<Meal>mealToDeleteTab = new ArrayList<>();
        for (Meal meal: this.classicMenu.getFoodMenu()) {
            if (!stock.isMealCookable(meal)) {
                mealToDeleteTab.add(meal);
            }
        }
        for (Meal mealToDelete: mealToDeleteTab){
            this.classicMenu.getFoodMenu().remove(mealToDelete);
        }
    }
    public void updateHundredYearsMenu(){
        List<Meal>mealToDeleteTab = new ArrayList<>();
        for (Meal meal: this.hundredYearsMenu.getFoodMenu()) {
            if (!stock.isMealCookable(meal)) {
                mealToDeleteTab.add(meal);
            }
        }
        System.out.println("Je suis là 1");
        for (Meal mealToDelete: mealToDeleteTab){
            System.out.println("Je suis là 2");
            this.hundredYearsMenu.getFoodMenu().remove(mealToDelete);
            System.out.println("Je suis là 3");
        }
    }

    public void printOrder(List<Meal>order){
        System.out.print("Choix: ");
        for (Meal meal:order){
            System.out.print(" / " + meal.getName());
        }
        System.out.print(".\n");
    }
}
