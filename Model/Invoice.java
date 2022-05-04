import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Invoice {
    private int amount;
    private String details;
    Invoice(){
        this.amount = 0;
        this.details ="";
    }
    public String getDetails() {
        return details;
    }
    public void createInvoiceDetails(List<Meal> foodOrder, List<Meal>drinkOrder, MENU_EDITION menuEdition){
        this.details = "MENU " + menuEdition;
        this.details += "\nPlats: ";
        for (int i = 0; i < foodOrder.size(); i++){
            if (i == foodOrder.size() - 1){
                this.details += foodOrder.get(i).getName() + ".";
            }
            else {
                this.details += foodOrder.get(i).getName() + "/";
            }
            this.amount += foodOrder.get(i).getPrice();
        }

        this.details += "\nBoissons: ";
        for (int i = 0; i < drinkOrder.size(); i++){
            if (i == drinkOrder.size() - 1){
                this.details += drinkOrder.get(i).getName() + ".";
            }
            else {
                this.details += drinkOrder.get(i).getName() + "/";
            }
            this.amount += drinkOrder.get(i).getPrice();
        }

        if (menuEdition == MENU_EDITION.CENT_ANS){
            this.amount = 100;
        }
        this.details += "\nPrix: " + this.amount + "€";
    }
    public void createInvoiceFile(int invoiceId) {
        DateTimeFormatter currentDate = DateTimeFormatter.ofPattern("dd_MM_yyyy");
        String invoiceFileName = "Invoice_" + invoiceId + "_" + currentDate;
        try{
            FileWriter invoiceFile = new FileWriter("Factures/" + invoiceFileName + ".txt");
            invoiceFile.write(this.details);
            invoiceFile.close();
            System.out.println("Facture prête à être imprimée");
        }catch(Exception e){
            System.out.println("Error on file creation");
            e.printStackTrace();
        }
    }
}
