import java.util.Date;

public class Interaction {

    private String id;
    private Date dateOfInteraction;
    private String email;
    private String interactionPot;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateOfInteraction() {
        return dateOfInteraction;
    }

    public void setDateOfInteraction(Date dateOfInteraction) {
        this.dateOfInteraction = dateOfInteraction;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInteractionPot() {
        return interactionPot;
    }

    public void setInteractionPot(String interactionPot) {
        this.interactionPot = interactionPot;
    }
}
