import java.io.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Console {
    public static void main(String[] args) {
        String input;
        Scanner sc = new Scanner(System.in);
        List<Lead> leads = new ArrayList<Lead>();
        List<Interaction> interactions = new ArrayList<Interaction>();
        do {
            System.out.println("Managing Leads");
            System.out.println("1. View all leads");
            System.out.println("2. Create new lead");
            System.out.println("3. Update a lead");
            System.out.println("4. Delete a lead\n\n");
            System.out.println("Managing Interactions");
            System.out.println("5. View all interactions");
            System.out.println("6. Create new interaction");
            System.out.println("7. Update an interaction");
            System.out.println("8. Delete a interaction");
            System.out.println("9. Report and Statistic");

            input = (sc.nextLine());
            switch (input) {
                case "1": // Print out all created leads
//                    System.out.println(leads);
                    try {
                        File myObj = new File("leads.csv");
                        Scanner myReader = new Scanner(myObj);
                        while (myReader.hasNextLine()) {
                            String data = myReader.nextLine();
                            System.out.println(data);
                        }
                        myReader.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("No lead was created");
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    // Creating new lead
                    Lead newLead = new Lead();
                    leads.add(newLead);

                    // Name Input and validation
                    System.out.println("Please enter lead's name: ");
                    String name = sc.nextLine();
                    while (!name.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) {
                        System.out.println("Please enter a valid name: ");
                        name = sc.nextLine();
                    }
                    if (name.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) {
                        newLead.setName(name);
                    }

                    // DOB Input and exception handling
                    System.out.println("Please enter lead's birthday (dd/MM/YYYY): ");
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String date = sc.nextLine();
                    while (true) {
                        try {
                            Date dob = sdf.parse(date);
                            newLead.setDob(dob);
                            break;
                        } catch (ParseException e) {
                            System.out.println(date + " is not a valid date");
                            System.out.println("Please enter a valid lead's birthday (dd/MM/YYYY): ");
                            date = sc.nextLine();
                        }
                    }

                    // Gender input and validation
                    System.out.println("Please enter lead's gender (M/F): ");

                    String gender = "";
                    char inputGender = sc.nextLine().charAt(0);
                    if (inputGender == 'm' || inputGender == 'M') {
                        newLead.setGender(true);
                        gender = "Male";
                    } else if (inputGender == 'f' || inputGender == 'F') {
                        newLead.setGender(true);
                        gender = "Female";
                    } else {
                        newLead.setGender(false);
                        gender = "false";
//                        System.out.println("Please enter a valid character (M/F): ");
//                        inputGender = sc.nextLine().charAt(0);
                    }
                    // Phone number input and validation
                    System.out.println("Please enter lead's phone number (10-digits): ");
                    String phone = sc.nextLine();
                    while (!phone.matches("^\\d{10}$")) {
                        System.out.println("Please enter a valid phone number (10-digits): ");
                        phone = sc.nextLine();
                    }
                    if (phone.matches("^\\d{10}$")) {
                        newLead.setPhone(phone);
                    }

                    // Email Input (Lead)
                    System.out.println("Please enter lead's email: ");
                    String email = sc.nextLine();
                    while (!email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
                        System.out.println("Please enter a valid email: ");
                        email = sc.nextLine();
                    }
                    if (email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
                        newLead.setEmail(email);
                    }

                    // Address Input and validation
                    System.out.println("Please enter lead's address: ");
                    String address = sc.nextLine();
                    while (!address.matches("^[A-Z0-9 _]*[A-Z0-9][A-Za-z0-9 _]*$")) {
                        System.out.println("Please enter a valid address: ");
                        address = sc.nextLine();
                    }
                    if (address.matches("^[A-Z0-9 _]*[A-Z0-9][A-Za-z0-9 _]*$")) {
                        newLead.setAddress(address);
                    }

                    try {
                        File myObj = new File("leads.csv");
                        if (myObj.createNewFile()) {
                            System.out.println("File created: " + myObj.getName());
                            try {
                                Scanner scanner = new Scanner(new File("leads.csv"));
                                String id = "";
                                int lines = 1;
                                String str1 = Integer.toString(lines);
                                id = "lead_00" + str1;
                                FileWriter myWriter = new FileWriter("leads.csv");
                                myWriter.write(id + "," + name + "," + date + "," + gender + "," + phone + "," + email + "," + address);
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
                                Scanner scanner = new Scanner(new File("leads.csv"));
                                String id = "";
                                int lines = 1;
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
                                BufferedWriter out = new BufferedWriter(new FileWriter("leads.csv", true));
                                out.write("\n" + id + "," + name + "," + date + "," + gender + "," + phone + "," + email + "," + address);
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
                    break;
                case "3":

//                    try {
//                        File myObj = new File("leads.csv");
//                        Scanner myReader = new Scanner(myObj);
//                        while (myReader.hasNextLine()) {
//                            if (updateLead == id)
//                        }
//
//                    } catch (FileNotFoundException e) {
//                        System.out.println("No lead was created");
//                        e.printStackTrace();
//                    }
//                            try {
//                                // Open given file in append mode.
//                                Scanner scanner = new Scanner(new File("leads.csv"));
//                                String id= "";
//                                int lines = 1;
//                                while (scanner.hasNext()){
//                                    String s = scanner.nextLine();
//                                    lines++;
//                                }
//                                String str1 = Integer.toString(lines);
//                                if (lines < 10){
//                                    id = "lead_00" + str1;
//                                } else if (lines < 100) {
//                                    id = "lead_0" + str1;
//                                } else {
//                                    id = "lead_" + str1;
//                                }
//                                System.out.println("Enter lead ID you want to update (lead_xxx):");
//                                String updateLead = sc.nextLine();
//                                if (updateLead.equals(id)) {
//                                    System.out.println("Enter info of lead you want to update (name/date/gender/phone/email/address):");
//                                    String infoOfLead = sc.nextLine();
//                                    if (infoOfLead.equals("name")) {
//                                        System.out.println("Enter new name of lead:");
//                                        String newName = sc.nextLine();
//                                        while (!newName.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) {
//                                            System.out.println("Please enter a valid name: ");
//                                            newName = sc.nextLine();
//                                        }
//                                        if (newName.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")){
//                                            name = newName;
//                                        }
//
//                                    }
//                                }
//                                BufferedWriter out = new BufferedWriter(new FileWriter("leads.csv", true));
//                                out.write("\n"+ id + "," + name + "," + date + "," + gender + "," + phone + "," + email + "," + address);
//                                out.close();
//                                System.out.println("Successfully wrote to the file.");
//                            }
//                            catch (IOException e) {
//                                System.out.println("exception occured" + e);
//                            }

                    break;
                case "4":
                    String deleteLead;
                    System.out.println("Enter lead ID you want to delete (lead_xxx): ");
                    deleteLead = sc.nextLine();
                    while (!deleteLead.contains("lead_")) {
                        System.out.println("Please enter valid lead ID (lead_xxx): ");
                        deleteLead = sc.nextLine();
                    }
                    Lead.removeLead("leads.csv", deleteLead, 1, ",");
                    break;
                case "5":
//                    System.out.println(interactions);
                    try {
                        File myObj2 = new File("interactions.csv");
                        Scanner myReader = new Scanner(myObj2);
                        while (myReader.hasNextLine()) {
                            String data = myReader.nextLine();
                            System.out.println(data);
                        }
                        myReader.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("No lead was created");
                        e.printStackTrace();
                    }
                    break;
                case "6":
                    // Create new interaction
                    Interaction newInteraction = new Interaction();
                    interactions.add(newInteraction);

                    // Generate ID for every interaction created
                    if (interactions.add(newInteraction)) {
                        for (int i = 0; i < interactions.size(); i++) {
                            newInteraction.setInteractionIdId("lead_" + Integer.toString(i));
                        }
                    }

                    // Date of Interaction Input and Validation
                    System.out.println("Enter date of interaction (dd/MM/yyyy): ");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
                    String date2 = sc.nextLine();
                    while (true) {
                        try {
                            Date interactionDate = sdf2.parse(date2);
                            newInteraction.setDateOfInteraction(interactionDate);
                            break;
                        } catch (ParseException | DateTimeException e) {
                            System.out.println(date2 + " is not a valid date");
                            System.out.println("Please enter a valid interaction date (dd/MM/YYYY): ");
                            date2 = sc.nextLine();
                        }
                    }

                    //Means of interaction Input and Validation
                    System.out.println("Enter the mean of interaction (email/telephone/face to face/social media):");
                    String means = sc.nextLine();
                    while (!((means.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) && (means.toLowerCase().contains("email") || means.toLowerCase().contains("telephone") || means.toLowerCase().contains("face to face") || means.toLowerCase().contains("social media")))) {
                        System.out.println("Please enter a valid mean of interaction (email/telephone/face to face/social media): ");
                        means = sc.nextLine();
                    }
                    if (((means.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) && (means.toLowerCase().contains("email") || means.toLowerCase().contains("telephone") || means.toLowerCase().contains("face to face") || means.toLowerCase().contains("social media")))){
                        newInteraction.setInteractionMean(means);
                    }

                    //Potential of each interaction Input and Validation
                    System.out.println("Enter the potential of each interaction (positive, neutral, negative):");
                    String potential = sc.nextLine();
                    while (!((potential.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) && (potential.toLowerCase().contains("positive") || potential.toLowerCase().contains("neutral") || potential.toLowerCase().contains("negative")))) {
                        System.out.println("Please enter a valid mean: ");
                        potential = sc.nextLine();
                    }
                    if ((potential.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) && (potential.toLowerCase().contains("positive") || potential.toLowerCase().contains("neutral") || potential.toLowerCase().contains("negative"))) {
                        newInteraction.setInteractionPot(potential);
                    }

                    try {
                        File myObj2 = new File("interactions.csv");
                        if (myObj2.createNewFile()) {
                            System.out.println("File created: " + myObj2.getName());
                            try {
                                Scanner scanner = new Scanner(new File("interactions.csv"));
                                String id2 = "";
                                int lines2 = 1;
                                String str2 = Integer.toString(lines2);
                                id2 = "inter_00" + str2;
                                FileWriter myWriter = new FileWriter("interactions.csv");
                                myWriter.write(id2 + "," + date2 + "," + means + "," + potential);
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
                                Scanner scanner = new Scanner(new File("interactions.csv"));
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
                                BufferedWriter out = new BufferedWriter(new FileWriter("interactions.csv", true));
                                out.write("\n" + id2 + "," + date2 + "," + means + "," + potential);
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
                    break;
                case "7":
                    break;
                case "8":
                    int deleteInteraction;
                    System.out.println("Enter interaction ID you want to delete (1 digit): ");
                    deleteInteraction = sc.nextInt();
                    if (deleteInteraction <= interactions.size()) {
                        leads.remove(deleteInteraction - 1);
                    }
                    while (deleteInteraction > interactions.size()) {
                        System.out.println("Item is invalid, please re-enter a valid interaction ID: ");
                        deleteInteraction = sc.nextInt();
                    }
                    break;
                case "9":
                    // Leads Age Report
                    int countZeroToTen = 0;
                    int countTenToTwenty = 0;
                    int countTwentyToSixty = 0;
                    int countGreaterThanSixty = 0;

                    for (int i = 0; i < leads.size(); i++) {
                        if (leads.get(i).getAge() <= 10) {
                            countZeroToTen++;
                        } else if (leads.get(i).getAge() > 10 && leads.get(i).getAge() <= 10) {
                            countTenToTwenty++;
                        } else if (leads.get(i).getAge() > 20 && leads.get(i).getAge() <= 60) {
                            countTwentyToSixty++;
                        } else if (leads.get(i).getAge() > 60) {
                            countGreaterThanSixty++;
                        }
                    }
                    System.out.println("Input: No input required");
                    System.out.println("0 - 10 (years old): " + countZeroToTen);
                    System.out.println("10 - 20 (years old): " + countTenToTwenty);
                    System.out.println("20 - 60 (years old): " + countTwentyToSixty);
                    System.out.println("> 60 (years old): " + countGreaterThanSixty + "\n");

                    // Number of Interactions by potential Report
                    int countPositive = 0;
                    int countNegative = 0;
                    int countNeutral = 0;
                    for (int i = 0; i < leads.size(); i++){
                        if (interactions.get(i).getInteractionPot().toLowerCase().contains("positive")){
                            countPositive++;
                        }else if (interactions.get(i).getInteractionPot().toLowerCase().contains("neutral")){
                            countNeutral++;
                        }else if (interactions.get(i).getInteractionPot().toLowerCase().contains("negative")){
                            countNegative++;
                        }
                    }
                    System.out.println("Input: Jan 01 2020 - December 31 2020");
                    System.out.println("Positive: " + countPositive);
                    System.out.println("Negative: " + countNegative);
                    System.out.println("Neutral: " + countNeutral + "\n");

                    // Number of Interactions by month report
                    Calendar cal = Calendar.getInstance();
                    int countJan = 0;
                    int countFeb = 0;
                    int countMar = 0;
                    int countApr = 0;
                    int countMay = 0;
                    int countJun = 0;
                    int countJul = 0;
                    int countAug = 0;
                    int countSep = 0;
                    int countOct = 0;
                    int countNov = 0;
                    int countDec = 0;
                    for (int i = 0; i < interactions.size(); i++){
                        cal.setTime(interactions.get(i).getDateOfInteraction());
                        int month = cal.get(Calendar.MONTH);
                        if (month == 0){
                            countJan++;
                        }else if (month == 1){
                            countFeb++;
                        }else if (month == 2){
                            countMar++;
                        }else if (month == 3){
                            countApr++;
                        }else if (month == 4){
                            countMay++;
                        }else if (month == 5){
                            countJun++;
                        }else if (month == 6){
                            countJul++;
                        }else if (month == 7){
                            countAug++;
                        }else if (month == 8){
                            countSep++;
                        }else if (month == 9){
                            countOct++;
                        }else if (month == 10){
                            countNov++;
                        }else if (month == 1){
                            countDec++;
                        }
                    }
                    System.out.println("Input: Jan 01 2020 - December 31 2020");
                    System.out.println("Jan 2020: " + countJan);
                    System.out.println("Feb 2020: " + countFeb);
                    System.out.println("Mar 2020: " + countMar);
                    System.out.println("Apr 2020: " + countApr);
                    System.out.println("May 2020: " + countMay);
                    System.out.println("Jun 2020: " + countJun);
                    System.out.println("Jul 2020: " + countJul);
                    System.out.println("Aug 2020: " + countAug);
                    System.out.println("Sep 2020: " + countSep);
                    System.out.println("Oct 2020: " + countOct);
                    System.out.println("Nov 2020: " + countNov);
                    System.out.println("Dec 2020: " + countDec);

                    break;
            }
        } while (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5")
                || input.equals("6") || input.equals("7") || input.equals("8"));
    }

}
