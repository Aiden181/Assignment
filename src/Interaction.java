import java.io.*;
import java.util.Date;
import java.util.Scanner;

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

    public static void removeInt(String filepath, String removeTerm, int positionOfTerm) {
        int position = positionOfTerm - 1;
        String tempFile = "temp2.csv";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        String currentLine;
        String data[];

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            while ((currentLine = br.readLine()) != null) {
                data = currentLine.split(",");
                if (!data[position].equalsIgnoreCase(removeTerm)) {
                    pw.println(currentLine);
                }
            }

            pw.flush();
            pw.close();
            fr.close();
            br.close();
            bw.close();
            fw.close();

            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public static void updateInt(String editTerm, String newData) {
        String tempFile = "temp.csv";
        String filepath = "interactions.csv";

        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        String id;
        String date;
        String leadId;
        String means;
        String potential;

        try {
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File(filepath));
            x.useDelimiter(",|\\n");

            while (x.hasNext()) {
                id = x.next();
                date = x.next();
                leadId = x.next();
                means = x.next();
                potential = x.next();

                if (id.equals(editTerm)) {
                    if (!newData.equals(date)) {
                        pw.print(id + "," + newData + "," + leadId + "," + means + "," + potential);
                    } else if (!newData.equals(means)) {
                        pw.print(id + "," + date + "," + leadId + "," + newData + "," + potential);
                    } else if (!newData.equals(potential)) {
                        pw.print(id + "," + date + "," + leadId + "," + means + "," + newData);
                    } else {
                        pw.print(id + "," + date + "," + leadId + "," + newData + "," + potential);
                    }
                }

                x.close();
                pw.flush();
                pw.close();

                oldFile.delete();
                File dump = new File(filepath);
                newFile.renameTo(dump);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
