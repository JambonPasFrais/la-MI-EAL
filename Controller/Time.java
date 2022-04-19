public class Time {
    //LMMJDVSD
    private DAY_LIST day;
    private int hours;
    private int minutes;

    Time(){
        this.day = DAY_LIST.LUNDI;
        this.hours = 12;
        this.minutes = 0;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public DAY_LIST getDay() {
        return day;
    }

    public void setDay(DAY_LIST day) {
        this.day = day;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
