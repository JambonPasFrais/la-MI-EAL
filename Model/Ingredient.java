public class Ingredient {
    private INGREDIENT_TYPE typeIngredient;
    private INGREDIENT_LIST nameIngredient;
    private int nbIngredientsLefts;

    Ingredient(INGREDIENT_TYPE typeIngredient, INGREDIENT_LIST nameIngredient, int nbIngredientsLefts) {
        this.typeIngredient = typeIngredient;
        this.nameIngredient = nameIngredient;
        this.nbIngredientsLefts = nbIngredientsLefts;
    }

    public int getNbIngredientsLefts() {
        return nbIngredientsLefts;
    }

    public void setTypeIngredient(INGREDIENT_TYPE typeIngredient) {
        this.typeIngredient = typeIngredient;
    }

    public void setNbIngredientsLefts(int nbIngredientsLefts) {
        this.nbIngredientsLefts = nbIngredientsLefts;
    }

    public INGREDIENT_TYPE getIngredientTypeDependingOnIngredientList(INGREDIENT_LIST ingredientList){
        if (ingredientList == INGREDIENT_LIST.LIMONADE || ingredientList == INGREDIENT_LIST.CIDER || ingredientList == INGREDIENT_LIST.BEER || ingredientList == INGREDIENT_LIST.JUICE || ingredientList == INGREDIENT_LIST.WATER){
            return INGREDIENT_TYPE.DRINK;
        }
        else {
            return INGREDIENT_TYPE.FOOD;
        }
    }
}
