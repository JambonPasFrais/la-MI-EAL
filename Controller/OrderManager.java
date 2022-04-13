import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManager {
    private List<Order>DayOrderList;
    private FoodMenu foodMenu;
    private DrinkMenu drinkMenu;
    private Scanner scanner;
    private String foodOrder;
    private String drinkOrder;
    OrderManager(){
        this.DayOrderList = new ArrayList<>();
        this.foodMenu = new FoodMenu();
        this.drinkMenu = new DrinkMenu();
        this.scanner = new Scanner(System.in);
        this.foodOrder="";
        this.drinkOrder ="";
    }
    public void addOrder(Order order){
        this.DayOrderList.add(order);
    }
    public void askForMenu(){
        //Création de la commande
        System.out.format("Est-ce que vous prendrez :\n1: menu spécial\n2: menu classique ?\n");
        int menuChoice = this.scanner.nextInt();
        int choice = 0;//Default
        do {
            this.askForFood();
            choice = this.scanner.nextInt();
            if (choice != -1){
                this.foodOrder += choice;
            }
            this.printFoodOrder();
        }while(choice != -1);
        System.out.println("On passe maintenant aux plats");
        do {
            this.askForDrink();
            choice = this.scanner.nextInt();
            if (choice != -1){
                this.drinkOrder += choice + " ";
            }
            this.printDrinkOrder();
        }while(choice != -1);
        System.out.println("Merci pour vos choix " + this.drinkOrder + " " + this.foodOrder);
    }

    public void askForFood(){
        System.out.format("Que voulez-vous comme plat(s) ? (-1 pour quitter)\n");
        this.foodMenu.printMenu();
    }
    public void printFoodOrder(){
        System.out.println("Les plats choisis sont : " + this.foodOrder);
    }
    public void askForDrink(){
        System.out.format("Que voulez-vous comme boisson(s) ? (-1 pour quitter)\n");
        this.drinkMenu.printMenu();
    }
    public void printDrinkOrder(){
        System.out.println("Les boissons choisies sont : " + this.drinkOrder);
    }
}
