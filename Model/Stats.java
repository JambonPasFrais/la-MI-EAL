public class Stats {
    private int nbOfActionsDone;
    Stats(){
        this.nbOfActionsDone = 0;
    }

    public int getNbOfActionsDone() {
        return nbOfActionsDone;
    }
    public void setNbOfActionsDone(int nbOfActionsDone) {
        this.nbOfActionsDone = nbOfActionsDone;
    }
    public void printStats(){
        System.out.println("Nombre d'actions réalisées " + this.nbOfActionsDone);
    }
}
