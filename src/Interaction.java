import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class Interaction implements ViewFile,Delete {

//    private String interactionID;
//    private Date dateOfInteraction;
//    private String interactionPot;
//    private String interactionMean;
//
//    public String getInteractionId() {
//        return interactionID;
//    }
//
//    public void setInteractionIdId(String id) {
//        this.interactionID = id;
//    }
//
//    public Date getDateOfInteraction() {
//        return dateOfInteraction;
//    }
//
//    public void setDateOfInteraction(Date dateOfInteraction) {
//        this.dateOfInteraction = dateOfInteraction;
//    }
//
//    public String getInteractionMean() {
//        return interactionMean;
//    }
//
//    public void setInteractionMean(String interactionMean) {
//        this.interactionMean = interactionMean;
//    }
//
//    public String getInteractionPot() {
//        return interactionPot;
//    }
//
//    public void setInteractionPot(String interactionPot) {
//        this.interactionPot = interactionPot;
//    }
//
//    @Override
//    public String toString() {
//        return "interactionID = " + interactionID +
//                ", dateOfInteraction = " + dateOfInteraction +
//                ", id = " + getId().toString() +
//                ", email = " + getInteractionId().toString() +
//                ", interactionPot = " + interactionPot + "\n";
//    }

    public void view(String filepath){
        try {
            File myObj2 = new File(filepath);
            Scanner myReader = new Scanner(myObj2);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No interaction was created");
            e.printStackTrace();
        }
    }

    public void createIntFile(String filepath,String date2,String means,String potential){
        try {
            File myObj2 = new File(filepath);
            if (myObj2.createNewFile()) {
                System.out.println("File created: " + myObj2.getName());
                try {
                    String id2 = "";
                    int lines2 = 1;
                    String str2 = Integer.toString(lines2);
                    id2 = "inter_00" + str2;
                    FileWriter myWriter = new FileWriter(filepath);
                    myWriter.write(id2 + "," + date2 + "," + "lead_001" + "," + means + "," + potential);
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("File already exists.");
                try {
                    // Open given file in append mode.
                    Scanner scanner = new Scanner(new File(filepath));
                    String id2 = "";
                    int lines2 = 1;
                    while (scanner.hasNext()) {
                        String s = scanner.nextLine();
                        lines2++;
                    }
                    String str2 = Integer.toString(lines2);
                    if (lines2 < 10) {
                        id2 = "inter_00" + str2;
                    } else if (lines2 < 100) {
                        id2 = "inter_0" + str2;
                    } else {
                        id2 = "inter_" + str2;
                    }
                    scanner = new Scanner(new File("leads.csv"));
                    String id = "";
                    int lines = 0;
                    while (scanner.hasNext()) {
                        String s = scanner.nextLine();
                        lines++;
                    }
                    String str1 = Integer.toString(lines);
                    if (lines < 10) {
                        id = "lead_00" + str1;
                    } else if (lines < 100) {
                        id = "lead_0" + str1;
                    } else {
                        id = "lead_" + str1;
                    }
                    BufferedWriter out = new BufferedWriter(new FileWriter(filepath, true));
                    out.write("\n"+id2 + "," + date2 + "," + id + "," + means + "," + potential);
                    out.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("exception occured" + e);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void remove(String filepath, String removeTerm, int positionOfTerm) {
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

    public void updateInt(String editTerm, String newDate,String newMeans,String newPotential) {
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
            FileWriter fw = new FileWriter(newFile);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner scanner = new Scanner(new File(filepath));


            while (scanner.hasNext()) {
                String intUpdate = scanner.nextLine();
                String[] int_arr = intUpdate.split(",");
                id = int_arr[0];
                date = int_arr[1];
                leadId = int_arr[2];
                means = int_arr[3];
                potential = int_arr[4];

                if (id.equals(editTerm)) {
                    if (!newDate.equals("")) {
                        pw.println(id + "," + newDate + "," + leadId + "," + means + "," + potential);
                    } else if (!newMeans.equals("")) {
                        pw.println(id + "," + date + "," + leadId + "," + newMeans + "," + potential);
                    } else if (!newPotential.equals("")) {
                        pw.println(id + "," + date + "," + leadId + "," + means + "," + newPotential);
                    }
                } else {
                    pw.println(id + "," + date + "," + leadId + "," + means + "," + potential);
                }
            }

                scanner.close();
                pw.flush();
                pw.close();

                oldFile.delete();
                File dump = new File(filepath);
                newFile.renameTo(dump);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
