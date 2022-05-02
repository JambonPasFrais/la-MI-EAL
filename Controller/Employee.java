import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Employee extends Person{
    private JOB_TYPE job;
    private int salary;
    private int dayWorked;

    Employee(){
        super();
        this.job = JOB_TYPE.WAITER;
        this.salary = 1400;
        this.dayWorked = 0;
    }
    Employee(String firstName, String lastName, int age, JOB_TYPE job, int salary){
        super(firstName, lastName, age);
        this.job = job;
        this.salary = salary;
        this.dayWorked = 0;
    }
    public void setJob(JOB_TYPE job) {
        this.job = job;
    }
    public JOB_TYPE getJob() {
        return job;
    }
    public int getDayWorked() {
        return dayWorked;
    }
    public void setDayWorked(int dayWorked) {
        this.dayWorked = dayWorked;
    }
    public void createEmployeeFromFile(String path){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File("Contrats_Employés/" + path)));
            String line = "";
            while ((line = reader.readLine())!= null){
                String [] str = line.split(" ");
                if (line.startsWith("Nom")){
                    this.setLastName(str[1]);
                }
                else if (line.startsWith("Prénom")){
                    this.setFirstName(str[1]);
                }
                else if (line.startsWith("Job")){
                    this.setJob(JOB_TYPE.valueOf(str[1]));
                }
                else if (line.startsWith("Salaire")){
                    this.salary = Integer.parseInt(str[1]);
                }
                else {
                    this.dayWorked = 0;
                }
            }
            reader.close();
        }catch (Exception e){
            System.err.println("Error " + e.getMessage());
        }
    }
    public void createEmployeeByHand(){
        System.out.println("Création d'un employé à la main");
        Scanner sc = new Scanner(System.in);
        System.out.print("Nom: ");
        this.setLastName(sc.nextLine());
        System.out.print("Prénom: ");
        this.setFirstName(sc.nextLine());
        System.out.print("Job: ");
        this.job = JOB_TYPE.valueOf(sc.nextLine());
        System.out.print("Salaire: ");
        this.salary = sc.nextInt();
        System.out.println("Employé créé");
        this.convertEmployeeInfoIntoFile();
    }
    public void convertEmployeeInfoIntoFile(){
        String employeeFileName = "Fiche_Info_" + this.getLastName() + "_" + this.getFirstName();
        try{
            FileWriter employeeFile = new FileWriter("Contrats_Employés\\" + employeeFileName + ".txt");
            employeeFile.write("Nom " + this.getLastName() + "\nPrénom " + this.getFirstName() + "\nJob " + this.job.toString() + "\nSalaire " + this.salary);
            employeeFile.close();
            System.out.println("Info de l'employée enregistrée dans " + employeeFileName + ".txt");
        }catch(Exception e){
            System.out.println("Error on file creation");
            e.printStackTrace();
        }
    }
    public void printEmployeeInfo(){
        System.out.println("Nom " + this.getLastName() + "\nPrénom " + this.getFirstName() + "\nJob " + this.job.toString() + "\nSalaire " + this.salary + "\nNombre de jour travaillé de suite " + this.dayWorked);
    }
}
