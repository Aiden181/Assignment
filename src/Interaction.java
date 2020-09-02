import java.util.Date;

public class Interaction extends Lead {

    private String interactionID;
    private Date dateOfInteraction;
    private String interactionPot;
    private String interactionMean;

    public String getInteractionId() {
        return interactionID;
    }

    public void setInteractionIdId(String id) {
        this.interactionID = id;
    }

    public Date getDateOfInteraction() {
        return dateOfInteraction;
    }

    public void setDateOfInteraction(Date dateOfInteraction) {
        this.dateOfInteraction = dateOfInteraction;
    }

    public String getInteractionMean() {
        return interactionMean;
    }

    public void setInteractionMean(String interactionMean) {
        this.interactionMean = interactionMean;
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
                ", email = " + getInteractionId().toString() +
                ", interactionPot = " + interactionPot + "\n";
    }
}
