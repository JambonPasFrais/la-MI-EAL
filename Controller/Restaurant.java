import java.util.HashMap;
import java.util.Map;

public class Restaurant {
    private Map<RESTAURANT_STATUS, String> openingSchedule;
    Restaurant(){
        this.openingSchedule = new HashMap<>();
    }

    public void setOpeningSchedule(Map<RESTAURANT_STATUS, String> openingSchedule) {
        this.openingSchedule = openingSchedule;
    }

    public Map<RESTAURANT_STATUS, String> getOpeningSchedule() {
        return openingSchedule;
    }
}
