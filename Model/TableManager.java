import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TableManager {
    List<Table> tableList;
    TableManager(int nbTable, int[]nbSitPerTable){
        this.tableList = new ArrayList<>();
        for(int i = 0; i < nbTable; i++){
            tableList.add(new Table(i, nbSitPerTable[i]));
        }
    }
    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }
    public List<Table> getTableList() {
        return tableList;
    }
    public void accessToTableInterface(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Voici les tables accessibles: ");
            for(Table table : tableList){
                if (table.getTableStatus() == TABLE_STATUS.FREE){
                    System.out.println("La table " + table.getId() + " avec un nombre de place de " + table.getNbSitAvailable());
                }
            }
            System.out.print("Prendre la table: ");
            int tableIdChoice = sc.nextInt();
        if (tableIdChoice >= 0 && tableIdChoice < this.tableList.size()) {
            this.tableList.get(tableIdChoice).setTableStatus(TABLE_STATUS.OCCUPIED);
        }
    }
    public void freeTableInterface(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Liste des tables occupées:");
            for(Table table : tableList){
                if (table.getTableStatus() == TABLE_STATUS.OCCUPIED){
                    System.out.println("La table " + table.getId() + " avec un nombre de place de " + table.getNbSitAvailable());
                }
            }
            System.out.print("Libérer la table: ");
            int tableIdChoice = sc.nextInt();
            if (tableIdChoice >= 0 && tableIdChoice < this.tableList.size()) {
                this.tableList.get(tableIdChoice).setTableStatus(TABLE_STATUS.FREE);
            }
    }
}
