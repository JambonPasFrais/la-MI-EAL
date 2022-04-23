public class Contract {
    private int salary;
    private int endOfContractInYears;
    JOB_TYPE job;
    Contract(){
        this.salary = 1200;
        this.endOfContractInYears = 1;
        this.job = JOB_TYPE.WAITER;
    }

    public int getEndOfContractInYears() {
        return endOfContractInYears;
    }

    public int getSalary() {
        return salary;
    }

    public JOB_TYPE getJob() {
        return job;
    }

    public void setEndOfContractInYears(int endOfContractInYears) {
        this.endOfContractInYears = endOfContractInYears;
    }

    public void setJob(JOB_TYPE job) {
        this.job = job;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
