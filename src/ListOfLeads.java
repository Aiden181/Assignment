public class ListOfLeads {

    private int size;
    private Lead[] listOfLead = new Lead[size];

    public boolean addLead(Lead lead){
        if (size < 0){
            return false;
        }else {
            this.listOfLead[size++] = lead;
            return true;
        }
    }
}
