import java.time.LocalDate;

public class Interaction extends Lead{

    private String interactionID;
    private LocalDate dateOfInteraction;
    private String interactionPot;

    public String getInteractionId() {
        return interactionID;
    }

    public void setInteractionIdId(String id) {
        this.interactionID = id;
    }

    public LocalDate getDateOfInteraction() {
        return dateOfInteraction;
    }

    public void setDateOfInteraction(LocalDate dateOfInteraction) {
        this.dateOfInteraction = dateOfInteraction;
    }

    public String getInteractionPot() {
        return interactionPot;
    }

    public void setInteractionPot(String interactionPot) {
        this.interactionPot = interactionPot;
    }

    @Override
    public String toString() {
        return "interactionID = " + interactionID +
                ", dateOfInteraction = " + dateOfInteraction +
                ", id = " + getId().toString() +
                ", email = " + getEmail().toString() +
                ", interactionPot = " + interactionPot + "\n";
    }
}
