import java.io.*;
import java.util.*;

public class Stock {
    private Map<INGREDIENT_LIST, Ingredient> stock;
    Stock(){
        this.stock = new HashMap<>();
    }
    Stock(Map<INGREDIENT_LIST, Ingredient>stock){
        this.stock = stock;
    }

    public  void createStockFromFile(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File("stock.txt")));
            String line = "";
            while ((line = reader.readLine())!= null){
                INGREDIENT_TYPE ingredient_type = INGREDIENT_TYPE.BOTH;
                String [] str = line.split(" ");
                Ingredient ingredient = new Ingredient(ingredient_type, INGREDIENT_LIST.valueOf(str[0]), Integer.parseInt(str[1]));
                ingredient.setTypeIngredient(ingredient.getIngredientTypeDependingOnIngredientList(INGREDIENT_LIST.valueOf(str[0])));
                this.stock.put(INGREDIENT_LIST.valueOf(str[0]), ingredient);
            }
            reader.close();
        }catch (Exception e){
            System.err.println("Error " + e.getMessage());
        }
    }

    public Map<INGREDIENT_LIST, Ingredient> getStock() {
        return stock;
    }

    public void useStockItem(INGREDIENT_LIST ingredient, int quantityUsed){
        if (quantityUsed >= 1 && this.stock.get(ingredient).getNbIngredientsLefts() >= 0){
            this.stock.get(ingredient).setNbIngredientsLefts(this.stock.get(ingredient).getNbIngredientsLefts() - quantityUsed);
        }
        else {
            System.err.println("Quantity Used is <=0");
        }
    }
    public void refillStockItem(INGREDIENT_LIST ingredient, int nbOfIngredientRefilled){
        if (nbOfIngredientRefilled >= 1){
            this.stock.get(ingredient).setNbIngredientsLefts(this.stock.get(ingredient).getNbIngredientsLefts() + nbOfIngredientRefilled);
        }
        else {
            System.err.println("Quantity is <=0");
        }
    }
    public void printStock(){
        this.stock.forEach((key, value) -> System.out.println(key.toString() + " " + value.getNbIngredientsLefts()));
    }
    public void updateStockForOneMeal(Meal meal){
        for (INGREDIENT_LIST ingredientUsed : meal.getRecipe()){
            this.useStockItem(ingredientUsed, 1);
        }
    }
    public boolean isMealCookable(Meal meal){
        List<INGREDIENT_LIST>ingredientUsed = new ArrayList<>();
        for (INGREDIENT_LIST ingredientUsable:meal.getRecipe()){
            if (this.stock.get(ingredientUsable).getNbIngredientsLefts() <= 0){
                for (INGREDIENT_LIST ingredientList:ingredientUsed){
                    this.refillStockItem(ingredientList, 1);
                }
                return false;
            }
            else {
                this.useStockItem(ingredientUsable, 1);
                ingredientUsed.add(ingredientUsable);
            }
        }
        for (INGREDIENT_LIST ingredientList:ingredientUsed){
            this.refillStockItem(ingredientList, 1);
        }
        return true;
    }
    public void reconstituteStockFromShoppingList(ShoppingList shoppingList){
        this.stock.forEach((key, value)->{
            if (shoppingList.getShoppingList().containsKey(key)){
                this.stock.get(key).setNbIngredientsLefts(shoppingList.getShoppingList().get(key) + this.stock.get(key).getNbIngredientsLefts());
            }
        });
    }
    public void reconstituteStockByHand(){
        Scanner sc = new Scanner(System.in);
        int nbIngredientToAdd = 0;
        for (INGREDIENT_LIST ingredientList : INGREDIENT_LIST.values()){
            if (ingredientList != INGREDIENT_LIST.BEER && ingredientList != INGREDIENT_LIST.LIMONADE && ingredientList != INGREDIENT_LIST.WATER && ingredientList != INGREDIENT_LIST.JUICE && ingredientList != INGREDIENT_LIST.CIDER){
                System.out.println("Nombre actuel de " + ingredientList + ": " + this.stock.get(ingredientList).getNbIngredientsLefts());
                System.out.print("Combien en ajouter: ");
                nbIngredientToAdd = sc.nextInt();
                this.stock.get(ingredientList).setNbIngredientsLefts(this.stock.get(ingredientList).getNbIngredientsLefts() + nbIngredientToAdd);
            }
        }
        System.out.println("Liste de Stock finale: ");
        this.printStock();
    }
    public void convertStockIntoFile(){
        String stockFileName = "stock";
        try{
            FileWriter shoppingListFile = new FileWriter(stockFileName + ".txt");
            this.stock.forEach((key, value)->{
                try {
                    shoppingListFile.write(key.toString() + " " + value.getNbIngredientsLefts() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            shoppingListFile.close();
            System.out.println("Liste des stocks mises à jour dans le fichier");
        }catch(Exception e){
            System.out.println("Error on file creation");
            e.printStackTrace();
        }
    }
}
