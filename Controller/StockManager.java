import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockManager {
    private Stock dailyStock;
    private ShoppingList dailyShoppingList;
    StockManager(){
        this.dailyStock = new Stock();
        this.dailyShoppingList = new ShoppingList();
    }
    public Stock getDailyStock() {
        return dailyStock;
    }
    public ShoppingList getDailyShoppingList() {
        return dailyShoppingList;
    }
}
