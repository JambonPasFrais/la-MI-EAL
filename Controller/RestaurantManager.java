import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class RestaurantManager {
    private StockManager stockManager;
    private MenuManager menuManager;
    private OrderManager orderManager;
    private EmployeeManager employeeManager;
    private TableManager tableManager;

    RestaurantManager(){
        this.stockManager = new StockManager();
        this.menuManager = new MenuManager();
        this.orderManager = new OrderManager();
        this.employeeManager = new EmployeeManager();
        this.employeeManager.generateAllEmployeeMapFromFolder("Contrats_Employés");
    }
    RestaurantManager(List<Meal> foodList, List<Meal>drinkList, int nbTable, int[]nbSitPerTable){
        this.menuManager = new MenuManager(foodList, drinkList);
        this.stockManager = new StockManager();
        this.stockManager.getDailyStock().createStockFromFile();
        this.orderManager = new OrderManager();
        this.employeeManager = new EmployeeManager();
        this.employeeManager.generateAllEmployeeMapFromFolder("Contrats_Employés");
        this.tableManager = new TableManager(nbTable, nbSitPerTable);
    }
    void launchRestaurantApp(){
        while (true){
            System.out.println("Quel écran souhaitez vous afficher?");
            System.out.println("1- Ecran Serveur");
            System.out.println("2- Ecran Cuisine");
            System.out.println("3- Ecran Bar");
            System.out.println("4- Ecran Manager");
            Scanner scanner = new Scanner(System.in);
            int choixEcran = scanner.nextInt();
            if (choixEcran == 1){
                this.showWaiterInterface();
            }
            else if (choixEcran == 2){
                this.showCookerInterface();
            }
            else if (choixEcran == 3){
                this.showBarmanInterface();
            }
            else if (choixEcran == 4){
                this.showManagerInterface();

            }
            else {
                System.out.println("Bye Bye");
                return;
            }
        }
    }
    void showWaiterInterface(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("1- Donner une table");
        System.out.println("2- Prendre une commande");
        System.out.println("3- Servir une table");
        System.out.println("4- Rendre une table");
        int choixInterface = scanner.nextInt();
        switch (choixInterface){
            case 1 -> tableManager.accessToTableInterface();
            case 2 -> orderManager.takeOrderFromTable(menuManager.getClassicMenu(), menuManager.getHundredYearsMenu(), this.stockManager.getDailyStock());
            case 3 -> employeeManager.showJobInterface(this.orderManager.getDayOrderList(), JOB_TYPE.WAITER);
            case 4 -> tableManager.freeTableInterface();
        }
    }
    void showCookerInterface(){
        employeeManager.showJobInterface(this.orderManager.getDayOrderList(), JOB_TYPE.COOKER);
    }
    void showBarmanInterface(){
        employeeManager.showJobInterface(this.orderManager.getDayOrderList(), JOB_TYPE.BARMAN);
    }
    void showManagerInterface(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("1- Générer la liste des employés du jour");
        System.out.println("2- Management des employés");//Stats, Supprimer, Ajouter
        System.out.println("3- Stocks");//Ajouter à la main, depuis la liste de course, imprimer
        System.out.println("4- Liste de course");//Imprimer, créer à la main, depuis les stocks
        int choixInterface = scanner.nextInt();
        switch (choixInterface){
            case 1 -> {
                this.employeeManager.generateDailyEmployeeMap();
            }
            case 2 -> {
                int choixInterfaceEmployeeManagement = 1;
                while(choixInterfaceEmployeeManagement > 0 && choixInterfaceEmployeeManagement < 4){
                    System.out.println("Que faire dans l'interface du management des employés ?");
                    System.out.println("1- Voir les statistiques");
                    System.out.println("2- Ajouter un employé au restaurant");
                    System.out.println("3- Supprimer un employé");
                    choixInterfaceEmployeeManagement = scanner.nextInt();
                    switch (choixInterfaceEmployeeManagement){
                        case 1 -> {
                            System.out.println("Ceci est une page de Stats");
                        }
                        case 2 -> {
                            Employee newEmployee = new Employee();
                            newEmployee.createEmployeeByHand();
                            newEmployee.convertEmployeeInfoIntoFile();
                            employeeManager.getAllEmployee().get(newEmployee.getJob()).add(newEmployee);
                        }
                        case 3 -> {
                            employeeManager.removeOneEmployeeInterface();
                        }
                        default -> System.out.println("Fermeture de l'interface du management des employés");
                    }
                }
            }
            case 3 -> {
                int choixInterfaceStock = 1;
                while(choixInterfaceStock > 0 && choixInterfaceStock < 5){
                    System.out.println("Que faire dans les Stocks ?");
                    System.out.println("1- Voir l'état");
                    System.out.println("2- Ajouter du Stock à la main");
                    System.out.println("3- Ajouter du Stock depuis une liste de course imprimée");
                    System.out.println("4- Imprimer le Stock");
                    choixInterfaceStock = scanner.nextInt();
                    switch (choixInterfaceStock){
                        case 1 -> {
                            stockManager.getDailyStock().printStock();
                        }
                        case 2 -> {
                            stockManager.getDailyStock().reconstituteStockByHand();
                        }
                        case 3 -> {
                            DateTimeFormatter currentDate = DateTimeFormatter.ofPattern("dd_MM_yyyy");
                            String path = "Shopping_List_" + currentDate.format(LocalDateTime.now()) + ".txt";
                            this.stockManager.getDailyShoppingList().convertFileIntoShoppingList(path);
                            stockManager.getDailyStock().reconstituteStockFromShoppingList(this.stockManager.getDailyShoppingList());
                            System.out.println("Stock reconstitués");
                        }
                        case 4 -> stockManager.getDailyStock().convertStockIntoFile();
                        default -> System.out.println("Fermeture de l'interface Stock");
                    }
                }
            }
            case 4 -> {
                int choixInterfaceShoppingList = 1;
                while(choixInterfaceShoppingList > 0 && choixInterfaceShoppingList < 4){
                    System.out.println("Que faire dans l'interface de la liste de course ?");
                    System.out.println("1- Créer la liste de course à la main");
                    System.out.println("2- Créer la liste de course à partir des stocks restants");
                    System.out.println("3- Imprimer la liste de course");
                    choixInterfaceShoppingList = scanner.nextInt();
                    switch (choixInterfaceShoppingList){
                        case 1 -> {
                            stockManager.getDailyShoppingList().generateShoppingListByHand(stockManager.getDailyStock());
                        }
                        case 2 -> {
                            System.out.println("Jusqu'à combien d'articles voulez-vous remplir les stocks ?");
                            int amountWanted = scanner.nextInt();
                            stockManager.getDailyShoppingList().generateShoppingListFromStock(stockManager.getDailyStock(), amountWanted);
                        }
                        case 3 -> {
                            stockManager.getDailyShoppingList().convertShoppingListIntoFile();
                        }
                        default -> System.out.println("Fermeture de l'interface liste de course");
                    }
                }
            }
        }
    }
}
/*TODO
/!\ scanner -> corriger les outofbounds
Les Stats :D
BUG : on peut servir les commandes qu'on ne voit pas :(
AMELIORATION : affichage de "aucun" lorsqu'aucune commande ne peut être faite ou servie
AMELIORATION : ajouter un mdp pour le manager
 */