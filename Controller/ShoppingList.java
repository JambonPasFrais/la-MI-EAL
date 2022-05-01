import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShoppingList {
    private Map<INGREDIENT_LIST, Integer>shoppingList;
    ShoppingList(){
        this.shoppingList = new HashMap<>();
    }

    public Map<INGREDIENT_LIST, Integer> getShoppingList() {
        return shoppingList;
    }
    public void addElementToShoppingList(INGREDIENT_LIST ingredientName, int amountWanted){
        this.shoppingList.put(ingredientName, amountWanted);
    }
    public void generateShoppingListFromStock(Stock stock, int globalAmountWanted){
        stock.getStock().forEach((key, value) -> {
            if (key != INGREDIENT_LIST.BEER && key != INGREDIENT_LIST.LIMONADE && key != INGREDIENT_LIST.WATER && key != INGREDIENT_LIST.JUICE && key != INGREDIENT_LIST.CIDER){
                this.addElementToShoppingList(key, globalAmountWanted - value.getNbIngredientsLefts());
            }
        });
    }
    public void generateShoppingListByHand(){
        Scanner sc = new Scanner(System.in);
        int nbIngredientToAdd = 0;
        for (INGREDIENT_LIST ingredientList : INGREDIENT_LIST.values()){
            if (ingredientList != INGREDIENT_LIST.BEER && ingredientList != INGREDIENT_LIST.LIMONADE && ingredientList != INGREDIENT_LIST.WATER && ingredientList != INGREDIENT_LIST.JUICE && ingredientList != INGREDIENT_LIST.CIDER){
                System.out.println("Nombre actuel de " + ingredientList + ": " + this.shoppingList.get(ingredientList));
                System.out.print("Combien en ajouter: ");
                nbIngredientToAdd = sc.nextInt();
                this.shoppingList.put(ingredientList, nbIngredientToAdd);
            }
        }
        System.out.println("Liste de course finale: ");
        this.printShoppingList();
    }
    public void convertShoppingListIntoFile(){
        DateTimeFormatter currentDate = DateTimeFormatter.ofPattern("dd_MM_yyyy");
        String shoppingListFileName = "Shopping_List_" + currentDate.format(LocalDateTime.now());
        try{
            FileWriter shoppingListFile = new FileWriter(shoppingListFileName + ".txt");
            this.shoppingList.forEach((key, value)->{
                try {
                    shoppingListFile.write(key.toString() + " " + value.toString() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            shoppingListFile.close();
            System.out.println("Liste de course prête à être imprimée");
        }catch(Exception e){
            System.out.println("Error on file creation");
            e.printStackTrace();
        }
    }
    public void convertFileIntoShoppingList(String filePath){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
            String line = "";
            while ((line = reader.readLine())!= null){
                String [] str = line.split(" ");
                this.shoppingList.put(INGREDIENT_LIST.valueOf(str[0]), Integer.parseInt(str[1]));
            }
            reader.close();
        }catch (Exception e){
            System.err.println("Error " + e.getMessage());
        }
    }
    public void printShoppingList(){
        this.shoppingList.forEach((key, value)->{
            System.out.println(key.toString() + " " + this.shoppingList.get(key));
        });
    }
}
