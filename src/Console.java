import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
                    if (name.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")){
                        newLead.setName(name);
                    }

                    // DOB Input and exception handling
                    System.out.println("Please enter lead's birthday (dd/MM/YYYY): ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/uuuu");
                    String date = sc.nextLine();
                    while (true){
                    try {
                        LocalDate dob = LocalDate.parse(date, formatter);
                        newLead.setDob(dob);
                        break;
                    } catch (DateTimeException e) {
                        System.out.println(date + " is not a valid date");
                        System.out.println("Please enter a valid lead's birthday (dd/MM/YYYY): ");
                        date = sc.nextLine();
                    }}

                    // Gender input and validation
                    System.out.println("Please enter lead's gender (M/F): ");

                    String gender = "";
                    char inputGender = sc.nextLine().charAt(0);
                    if (inputGender == 'm' ||  inputGender == 'M') {
                        newLead.setGender(true);
                        gender = "Male";
                    } else if (inputGender == 'f' || inputGender == 'F'){
                        newLead.setGender(true);
                        gender = "Female";
                    }
                    else {
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
                    if (phone.matches("^\\d{10}$")){
                        newLead.setPhone(phone);
                    }

                    // Email Input (Lead)
                    System.out.println("Please enter lead's email: ");
                    String email = sc.nextLine();
                    while (!email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
                        System.out.println("Please enter a valid email: ");
                        email = sc.nextLine();
                    }
                    if (email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")){
                        newLead.setEmail(email);
                    }

                    // Address Input and validation
                    System.out.println("Please enter lead's address: ");
                    String address = sc.nextLine();
                    while (!address.matches("^[A-Z0-9 _]*[A-Z0-9][A-Za-z0-9 _]*$")) {
                        System.out.println("Please enter a valid address: ");
                        address = sc.nextLine();
                    }
                    if (address.matches("^[A-Z0-9 _]*[A-Z0-9][A-Za-z0-9 _]*$")){
                        newLead.setAddress(address);
                    }

                    try {
                        File myObj = new File("leads.csv");
                        if (myObj.createNewFile()) {
                            System.out.println("File created: " + myObj.getName());
                            try {
                                Scanner scanner = new Scanner(new File("leads.csv"));
                                String id= "";
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
                                String id= "";
                                int lines = 1;
                                while (scanner.hasNext()){
                                    String s = scanner.nextLine();
                                    lines++;
                                }
                                String str1 = Integer.toString(lines);
                                if (lines < 10){
                                    id = "lead_00" + str1;
                                } else if (lines < 100) {
                                    id = "lead_0" + str1;
                                } else {
                                    id = "lead_" + str1;
                                }
                                BufferedWriter out = new BufferedWriter(new FileWriter("leads.csv", true));
                                out.write("\n"+ id + "," + name + "," + date + "," + gender + "," + phone + "," + email + "," + address);
                                out.close();
                                System.out.println("Successfully wrote to the file.");
                            }
                            catch (IOException e) {
                                System.out.println("exception occured" + e);
                            }

                        }

                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    break;
                case "4":
                    int deleteLead;
                    System.out.println("Enter lead ID you want to delete (1 digit): ");
                    deleteLead = sc.nextInt();
                    if (deleteLead <= leads.size()){
                        leads.remove(deleteLead - 1);
                    }while (deleteLead > leads.size()){
                        System.out.println("Item is invalid, please re-enter a valid lead ID: ");
                        deleteLead = sc.nextInt();
                }
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
                    if (interactions.add(newInteraction)){
                        for (int i = 0; i < interactions.size(); i++){
                            newInteraction.setInteractionIdId("lead_" +Integer.toString(i));
                        }
                    }

                    // Ask for Interaction Potential
//                    System.out.println("What is the potential of this interaction? ");
//                    System.out.println("1. Positive");
//                    System.out.println("2. Negative");
//                    System.out.println("3. Neutral");
//                    int choice = sc.nextInt();
//                    switch (choice){
//                        case 1:
//                            newInteraction.setInteractionPot("Potential");
//                        case 2:
//                            newInteraction.setInteractionPot("Negative");
//                        case 3:
//                            newInteraction.setInteractionPot("Neutral");
//                        default:
//                            System.out.println("Please enter a valid input (1-3): ");
//                    }

                    // Date of Interaction Input and Validation
                    System.out.println("Enter date of interaction (dd/MM/yyyy): ");
                    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d/M/uuuu");
                    String date2 = sc.nextLine();
//                    try {
//                        LocalDate interactionDate = LocalDate.parse(date2, formatter2);
//                        newInteraction.setDateOfInteraction(interactionDate);
//                    } catch (DateTimeException e) {
//                        System.out.println(date2 + " is not a valid date");
//                    }
                    while (true){
                        try {
                            LocalDate interactionDate = LocalDate.parse(date2, formatter2);
                            newInteraction.setDateOfInteraction(interactionDate);
                            break;
                        } catch (DateTimeException e) {
                            System.out.println(date2 + " is not a valid date");
                            System.out.println("Please enter a valid interaction date (dd/MM/YYYY): ");
                            date2 = sc.nextLine();
                        }}

                    //Means of interaction Input and Validation
                    System.out.println("Enter the mean of interaction (email/telephone/face to face/social media):");
                    String means = sc.nextLine();
                    while (!means.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) {
                        System.out.println("Please enter a valid mean of interaction (email/telephone/face to face/social media): ");
                        means = sc.nextLine();
                    }

                    //Potential of each interaction Input and Validation
                    System.out.println("Enter the potential of each interaction (positive, neutral, negative):");
                    String potential = sc.nextLine();
                    while (!potential.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) {
                        System.out.println("Please enter a valid mean: ");
                        potential = sc.nextLine();
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
                                while (scanner.hasNext()){
                                    String s = scanner.nextLine();
                                    lines2++;
                                }
                                String str2 = Integer.toString(lines2);
                                if (lines2 < 10){
                                    id2 = "inter_00" + str2;
                                } else if (lines2 < 100) {
                                    id2 = "inter_0" + str2;
                                } else {
                                    id2 = "inter_" + str2;
                                }
                                BufferedWriter out = new BufferedWriter(new FileWriter("interactions.csv", true));
                                out.write("\n"+ id2 + "," + date2 + "," + means + "," + potential);
                                out.close();
                                System.out.println("Successfully wrote to the file.");
                            }
                            catch (IOException e) {
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
                    if (deleteInteraction <= interactions.size()){
                        leads.remove(deleteInteraction - 1);
                    }while (deleteInteraction > interactions.size()){
                    System.out.println("Item is invalid, please re-enter a valid interaction ID: ");
                    deleteInteraction = sc.nextInt();
                }
                    break;
            }
        } while (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5")
                || input.equals("6") || input.equals("7") || input.equals("8"));
    }

}
