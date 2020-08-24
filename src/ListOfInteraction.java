public class ListOfInteraction {

    private int size;
    private Interaction[] interaction = new Interaction[size];

    public boolean addInteraction(Interaction interaction){
        if (size < 0){
            return false;
        }else {
            this.interaction[size++] = interaction;
            return true;
        }
    }
}
