import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StatsManager {
    Map<JOB_TYPE, Stats> dailyJobStats;
    StatsManager(){
        this.dailyJobStats = new HashMap<>();
        for (JOB_TYPE jobType: JOB_TYPE.values()){
            this.dailyJobStats.put(jobType, new Stats());
        }
    }

    public Map<JOB_TYPE, Stats> getDailyJobStats() {
        return dailyJobStats;
    }
    public void addActionsDoneForSpecificJob(JOB_TYPE jobType){
        this.dailyJobStats.get(jobType).setNbOfActionsDone(this.dailyJobStats.get(jobType).getNbOfActionsDone() + 1);
    }
    public void showStatsInterface() {
        System.out.println("Voici les statistiques de la journée par métier:");
        for (JOB_TYPE jobType : JOB_TYPE.values()) {
            System.out.println(jobType + ":");
            this.dailyJobStats.get(jobType).printStats();
        }
    }
}
