import java.util.Random;
/*TODO
Add randomness in the timings
 */
public class Time {
    //0 to 6
    private int day;
    private int hours;
    private int minutes;

    Time(){
        this.day = 0;
        this.hours = 10;
        this.minutes = 0;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void manageTime(){
        if (this.minutes >= 60){
            this.hours++;
            this.minutes -= 60;
        }
        if (this.hours >= 24){
            this.day++;
            this.hours -= 24;
        }
        if (this.day >= 7){
            this.day -= 7;
        }
    }
    public void makeCycleForStocksReconstitution(){
        this.minutes += 30;
        this.manageTime();
    }
    public void makeCycleForEmployeeManagement(){
        this.minutes += 30;
        this.manageTime();
    }
    public void makeCycleForRestaurantOrder(){
        Random r = new Random();
        int nbOfCycle = r.nextInt(2) + 1;
        this.minutes += (20 * nbOfCycle);
        this.manageTime();
    }
    public void makeCycleForShoppingListCreation(){
        this.minutes += 40;
        this.manageTime();
        this.manageTime();
    }
    public void makeCycleForRestaurantCleaning(){
        this.minutes+= 10;
        this.hours+=1;
        this.manageTime();
    }
    public void endDayOfWork(){
        this.hours = 10;
        this.minutes = 0;
    }
    public void printTime(){
        System.out.println("Day: " + this.day + " Hour: " + this.hours + " Min: " + this.minutes);
    }
}
