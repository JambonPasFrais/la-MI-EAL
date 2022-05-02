import java.util.List;
import java.util.Scanner;

public class RestaurantManager {
    private StockManager stockManager;
    private MenuManager menuManager;
    private OrderManager orderManager;
    private EmployeeManager employeeManager;

    RestaurantManager(){
        this.stockManager = new StockManager();
        this.menuManager = new MenuManager();
        this.orderManager = new OrderManager();
        this.employeeManager = new EmployeeManager();
    }
    RestaurantManager(List<Meal> foodList, List<Meal>drinkList){
        this.menuManager = new MenuManager(foodList, drinkList);
        this.stockManager = new StockManager();
        this.stockManager.getDailyStock().createStockFromFile();
        this.orderManager = new OrderManager();
        this.employeeManager = new EmployeeManager();
    }
    void launchRestaurantApp(){
        while (true){
            System.out.println("Quel écran souhaitez vous afficher?");
            System.out.println("1- Ecran prise de commande");
            System.out.println("2- Ecran cuisine");
            System.out.println("3- Ecran bar");
            System.out.println("4- Ecran Monitoring");
            Scanner scanner = new Scanner(System.in);
            int choixEcran = scanner.nextInt();
            if (choixEcran == 1){
                orderManager.takeOrderFromTable(menuManager.getClassicMenu(), menuManager.getHundredYearsMenu(), this.stockManager.getDailyStock());
            }
            else if (choixEcran == 2){
                for (Order order : orderManager.getDayOrderList()){
                    order.printOneOrderType(order.getFoodOrder());
                }
            }
            else if (choixEcran == 3){
                menuManager.getClassicMenu().printOneMenuType(menuManager.getClassicMenu().getFoodMenu());
            }
            else if (choixEcran == 4){
                stockManager.getDailyStock().printStock();
            }
            else {
                System.out.println("Error");
                return;
            }
        }

    }
}
