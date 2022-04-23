public class EmployeeStatus {
    boolean isWorkingToday;
    boolean isBusy;
    EmployeeStatus(){
        this.isWorkingToday = false;
        this.isBusy = false;
    }
    EmployeeStatus(boolean isWorkingToday, boolean isBusy){
        this.isWorkingToday = isWorkingToday;
        this.isBusy = isBusy;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public boolean isWorkingToday() {
        return isWorkingToday;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public void setWorkingToday(boolean workingToday) {
        isWorkingToday = workingToday;
    }
}
