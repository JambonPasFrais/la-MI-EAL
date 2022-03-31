public class PersonPrinter {
    private Person person;
    //Cnstrct
    public PersonPrinter(Person person){
        this.person = person;
    }
    //S&G
    public void setPerson(Person person) {
        this.person = person;
    }
    public Person getPerson() {
        return person;
    }
    //Methode
    public void print(){
        System.out.println("Nom: " + this.person.getLastName() + " Prénom: " + this.person.getFirstName() + " Age: "+ this.person.getAge());
    }
}
