public class StockManager {
    private Stock dailyStock;
    StockManager(){
        this.dailyStock = new Stock();
    }

    public void setDailyStock(Stock dailyStock) {
        this.dailyStock = dailyStock;
    }

    public Stock getDailyStock() {
        return dailyStock;
    }

    public Order manageStocks (Order order){
        for (Meal foodMeal : order.getFoodOrder()){
            for (INGREDIENT_LIST ingredientUsed : foodMeal.getRecipe()){
                if (this.dailyStock.getStock().get(ingredientUsed).getNbIngredientsLefts() == 0){
                    /*TODO
                    Generate new random meal
                     */
                }
                else{
                    this.dailyStock.useStockItem(ingredientUsed, 1);
                }
            }
        }
        return order;
    }
}
