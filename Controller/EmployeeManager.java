import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EmployeeManager {
    private Map<JOB_TYPE, Map<Integer, Employee>>AllEmployee;
    EmployeeManager(){
        this.AllEmployee = new HashMap<>();
    }

    public void setAllEmployee(Map<JOB_TYPE, Map<Integer, Employee>> allEmployee) {
        AllEmployee = allEmployee;
    }

    public Map<JOB_TYPE, Map<Integer, Employee>> getAllEmployee() {
        return AllEmployee;
    }

    public Map<JOB_TYPE, Map<Integer, Employee>> generateDailyEmployeeList(){
        Map<JOB_TYPE, Map<Integer, Employee>>dailyEmployee = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int managerChoice = 0;
        int generateDailyEmployeeListEnd = 0;
        int addSomeoneChoice = 0;
        int addSomeoneInAJobChoice = 0;
        JOB_TYPE addSomeoneJob = JOB_TYPE.WAITER;

        //Traitement
        while(addSomeoneChoice != 5 || (dailyEmployee.get(JOB_TYPE.MANAGER).size() < 1 && dailyEmployee.get(JOB_TYPE.COOKER).size() < 4 && dailyEmployee.get(JOB_TYPE.BARMAN).size() < 1 && dailyEmployee.get(JOB_TYPE.WAITER).size() < 2)){

            //Print dailyEmployees
            System.out.println("Voici la liste des employés du jour : ");
            dailyEmployee.forEach((key, value)->{
                System.out.print(key + ": ");
                if (dailyEmployee.get(key) != null){
                    dailyEmployee.get(key).forEach((key2, value2)->{
                        System.out.print(key2 + ": " + value2.getLastName() + " / ");
                    });
                }
                else{
                    System.out.println("aucun");
                }

            });

            //Ask choice
            System.out.println("Souhaitez-vous : \n1: Ajouter un cuisinie\n2: Ajouter un barman\n3: Ajouter un serveur\n4: Ajouter un manager\n5: Terminer la génération des employés du jour");
            addSomeoneChoice = scanner.nextInt();
            switch (addSomeoneChoice) {
                case 1 -> {
                    addSomeoneJob = JOB_TYPE.COOKER;
                    this.printEmployeeFromASpecificJobType(addSomeoneJob, this.AllEmployee);
                    addSomeoneInAJobChoice = scanner.nextInt();
                    dailyEmployee.put(addSomeoneJob, (Map<Integer, Employee>) dailyEmployee.get(addSomeoneJob).put(dailyEmployee.get(addSomeoneJob).size(), this.AllEmployee.get(addSomeoneJob).get(addSomeoneInAJobChoice)));
                }
                case 2 -> {
                    addSomeoneJob = JOB_TYPE.BARMAN;
                    this.printEmployeeFromASpecificJobType(addSomeoneJob, this.AllEmployee);
                    addSomeoneInAJobChoice = scanner.nextInt();
                    dailyEmployee.put(addSomeoneJob, (Map<Integer, Employee>) dailyEmployee.get(addSomeoneJob).put(dailyEmployee.get(addSomeoneJob).size(), this.AllEmployee.get(addSomeoneJob).get(addSomeoneInAJobChoice)));
                }
                case 3 -> {
                    addSomeoneJob = JOB_TYPE.WAITER;
                    this.printEmployeeFromASpecificJobType(addSomeoneJob, this.AllEmployee);
                    addSomeoneInAJobChoice = scanner.nextInt();
                    dailyEmployee.put(addSomeoneJob, (Map<Integer, Employee>) dailyEmployee.get(addSomeoneJob).put(dailyEmployee.get(addSomeoneJob).size(), this.AllEmployee.get(addSomeoneJob).get(addSomeoneInAJobChoice)));
                }
                case 4 -> {
                    addSomeoneJob = JOB_TYPE.MANAGER;
                    this.printEmployeeFromASpecificJobType(addSomeoneJob, this.AllEmployee);
                    addSomeoneInAJobChoice = scanner.nextInt();
                    dailyEmployee.put(addSomeoneJob, (Map<Integer, Employee>) dailyEmployee.get(addSomeoneJob).put(dailyEmployee.get(addSomeoneJob).size(), this.AllEmployee.get(addSomeoneJob).get(addSomeoneInAJobChoice)));
                }
                case 5 -> System.out.println("Merci pour vos choix");
            }
        }

        return dailyEmployee;
    }
    public void printEmployeeFromASpecificJobType(JOB_TYPE job_type, Map<JOB_TYPE, Map<Integer, Employee>>dailyAvailableEmployee){
        System.out.println(job_type + ": ");
        dailyAvailableEmployee.get(job_type).forEach((key2, value2)->{
            System.out.print(key2 + ": " + value2.getLastName() + " / ");
        });
        System.out.print("\n");
    }
    /*TODO
    Pour corriger le problème des employés déjà sélectionnés et ceux qui ont déjà travaillé 2 soirs de suite, il faut au début de la méthode, créer une nouvelle double HashMap qui effectuera un tri parmi la grande Hashmap à l'aide d'une autre méthode makeAvailableEmployeeMap()
    A chaque choix, on retirera la personne sélectionnée de cette nouvelle double hashmap qu'on appelera dailyAvaibleEmployee
    Rename tous les choices et remove les inutils
    /!\ à la méthode, bien tester les fonctionnalités
     */
}
