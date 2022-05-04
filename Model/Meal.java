public class Meal {
    private int id;
    private String name;
    private INGREDIENT_LIST[] recipe;
    private boolean isMealReady;
    private int timeToPrepare;
    private MEAL_TYPE mealType;
    private double price;

    Meal(int id, String name, INGREDIENT_LIST[]recipe, boolean isMealReady, int timeToPrepare, MEAL_TYPE mealType, double price){
        this.id = id;
        this.name = name;
        this.recipe = recipe;
        this.isMealReady = isMealReady;
        this.timeToPrepare = timeToPrepare;
        this.mealType = mealType;
        this.price = price;
    }
    public INGREDIENT_LIST[] getRecipe() {
        return recipe;
    }
    public double getPrice() {
        return price;
    }
    public boolean isMealReady() {
        return isMealReady;
    }
    public void setMealReady(boolean mealReady) {
        isMealReady = mealReady;
    }
    public String getName() {
        return name;
    }
}
