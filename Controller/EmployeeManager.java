import java.io.File;
import java.util.*;

public class EmployeeManager {
    private Map<JOB_TYPE, List<Employee>>AllEmployee;
    private Map<JOB_TYPE, List<Employee>>dailyEmployeeMap;
    EmployeeManager(){
        this.AllEmployee = new HashMap<>();
        for (JOB_TYPE job_type : JOB_TYPE.values()){
            this.AllEmployee.put(job_type, new ArrayList<>());
        }
        this.dailyEmployeeMap = new HashMap<>();
        for (JOB_TYPE job_type : JOB_TYPE.values()){
            this.dailyEmployeeMap.put(job_type, new ArrayList<>());
        }
    }
    public Map<JOB_TYPE, List<Employee>> getAllEmployee() {
        return AllEmployee;
    }
    public Map<JOB_TYPE, List<Employee>> getDailyEmployeeMap() {
        return dailyEmployeeMap;
    }
    public void generateAllEmployeeMapFromFolder(String pathFolder){
        List<String>fileNameList = new ArrayList<>();
        File employeeFolder = new File(pathFolder);
        for (File file : Objects.requireNonNull(employeeFolder.listFiles())) {
            if (!file.isDirectory()) {
                fileNameList.add(file.getName());
            } else {
                generateAllEmployeeMapFromFolder(file.getName());
            }
        }
        for (String fileName : fileNameList){
            Employee employee = new Employee();
            employee.createEmployeeFromFile(fileName);
            this.AllEmployee.get(employee.getJob()).add(employee);
        }
    }
    public void generateDailyEmployeeMap(){
        //Reinitialization
        this.dailyEmployeeMap = new HashMap<>();
        for (JOB_TYPE job_type : JOB_TYPE.values()){
            this.dailyEmployeeMap.put(job_type, new ArrayList<>());
        }
        Map<JOB_TYPE, List<Employee>> dailyAvailableEmployee = this.makeAvailableEmployeeMap();

        Scanner scanner = new Scanner(System.in);
        int addSomeoneChoice = 0;
        int addSomeoneInAJobChoice;
        JOB_TYPE addSomeoneJob = JOB_TYPE.WAITER;
        //Traitement
        while(addSomeoneChoice != 5 || dailyEmployeeMap.get(JOB_TYPE.MANAGER).size() < 1 || dailyEmployeeMap.get(JOB_TYPE.COOKER).size() < 4 || dailyEmployeeMap.get(JOB_TYPE.BARMAN).size() < 1 || dailyEmployeeMap.get(JOB_TYPE.WAITER).size() < 2){

            //Print dailyEmployees
            System.out.println("Voici la liste des employés du jour : ");
            dailyEmployeeMap.forEach((key, value)->{
                System.out.print(key + ": ");
                if (dailyEmployeeMap.get(key).size() != 0){
                    for (Employee e : value){
                        System.out.print(e.getLastName() + " " + e.getFirstName() + " / ");
                    }
                }
                else{
                    System.out.print("Aucun");
                }
                System.out.print("\n");
            });

            //Ask choice
            System.out.println("Souhaitez-vous : \n1: Ajouter un cuisinier\n2: Ajouter un barman\n3: Ajouter un serveur\n4: Ajouter un manager\n5: Terminer la génération des employés du jour");
            addSomeoneChoice = scanner.nextInt();
            switch (addSomeoneChoice) {
                case 1 -> addSomeoneJob = JOB_TYPE.COOKER;
                case 2 -> addSomeoneJob = JOB_TYPE.BARMAN;
                case 3 -> addSomeoneJob = JOB_TYPE.WAITER;
                case 4 -> addSomeoneJob = JOB_TYPE.MANAGER;
                case 5 -> System.out.println("Merci pour vos choix");
            }
            if (addSomeoneChoice != 5){
                this.printEmployeeFromASpecificJobType(addSomeoneJob, dailyAvailableEmployee);
                addSomeoneInAJobChoice = scanner.nextInt();
                dailyEmployeeMap.get(addSomeoneJob).add(dailyAvailableEmployee.get(addSomeoneJob).get(addSomeoneInAJobChoice));
                dailyEmployeeMap.get(addSomeoneJob).get(dailyEmployeeMap.get(addSomeoneJob).size() - 1).setDayWorked(dailyEmployeeMap.get(addSomeoneJob).get(dailyEmployeeMap.get(addSomeoneJob).size() - 1).getDayWorked() + 1);
                dailyAvailableEmployee.get(addSomeoneJob).remove(dailyAvailableEmployee.get(addSomeoneJob).get(addSomeoneInAJobChoice));
            }
        }
        //Update Employee day worked
        AllEmployee.forEach((key, value)->{
            for (Employee e:value){
                if (!dailyEmployeeMap.get(key).contains(e)){
                    e.setDayWorked(0);
                }
            }
        });
    }
    public void printEmployeeFromASpecificJobType(JOB_TYPE job_type, Map<JOB_TYPE, List<Employee>>dailyAvailableEmployee){
        int i = 0;
        System.out.println(job_type + ": ");
        for(Employee e : dailyAvailableEmployee.get(job_type)){
            System.out.print(i + ": " + e.getLastName() + " " + e.getFirstName() + " / ");
            i++;
        }
        System.out.print("\n");
    }
    public Map<JOB_TYPE, List<Employee>>makeAvailableEmployeeMap(){
        Map<JOB_TYPE, List<Employee>>availableEmployee = new HashMap<>();
        for(JOB_TYPE job_type:JOB_TYPE.values()){
            availableEmployee.put(job_type, new ArrayList<>());
        }

        this.AllEmployee.forEach((key, value)->{
            for (Employee e:value){
                if (e.getJob() == JOB_TYPE.MANAGER || e.getDayWorked() < 2){
                    availableEmployee.get(key).add(e);
                }
            }
        });

        return availableEmployee;
    }
    public void printAllEmployee(){
        this.AllEmployee.forEach((key, value)->{
            for (Employee e: value){
                e.printEmployeeInfo();
            }
        });
    }
    public void showCookerInterface(List<Order>globalOrderList){
        this.printEmployeeFromASpecificJobType(JOB_TYPE.COOKER, getDailyEmployeeMap());
        this.showOrderToMakeForSpecificOrderType(globalOrderList, "FoOd");
    }
    public void showBarmanInterface(List<Order>globalOrderList){
        this.printEmployeeFromASpecificJobType(JOB_TYPE.BARMAN, getDailyEmployeeMap());
        this.showOrderToMakeForSpecificOrderType(globalOrderList, "Drink");
    }
    public void showOrderToMakeForSpecificOrderType(List<Order>globalOrderList, String foodOrDrink){
        System.out.println("Voici les commandes à préparer:");
        if (!globalOrderList.isEmpty()){
            for (Order order:globalOrderList){
                if (!order.isServed()){
                    System.out.print("Commande " + order.getIdOrder() +": ");
                    if (foodOrDrink.toLowerCase().equals("food")){
                        for(Meal foodMeal : order.getFoodOrder()){
                            if(!foodMeal.isMealReady()){
                                System.out.print(foodMeal.getName() + " ( ");
                                for (INGREDIENT_LIST ingredientList:foodMeal.getRecipe()){
                                    System.out.print(ingredientList.toString() + " ");
                                }
                                System.out.print(") / ");
                            }
                        }
                    }
                    else if(foodOrDrink.toLowerCase().equals("drink")){
                        for(Meal drinkMeal : order.getDrinkOrder()){
                            if(!drinkMeal.isMealReady()){
                                System.out.print(drinkMeal.getName() + " / ");
                            }
                        }
                    }
                    else{
                        System.err.println("Error on String");
                        return;
                    }
                    System.out.print("\n");
                }
            }
        }
        else{
            System.out.println("Aucune");
        }
    }
}
