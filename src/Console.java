import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
            System.out.println("3. Delete a lead");
            System.out.println("4. Update a lead");
            System.out.println();
            System.out.println();
            System.out.println("Managing Interactions");
            System.out.println("5. View all interactions");
            System.out.println("6. Create new interaction");
            System.out.println("7. Delete a interaction");
            System.out.println("8. Update an interaction");


            input = (sc.nextLine());
            switch (input) {
                case "1": // Print out all created leads
                    System.out.println(leads);
                    break;
                case "2":
                    // Creating new lead
                    Lead newLead = new Lead();
                    leads.add(newLead);

                    // Generating ID for every lead created
                    if (leads.add(newLead)){
                        for (int i = 0; i < leads.size(); i++){
                            newLead.setId("lead_" +Integer.toString(i));
                        }
                    }

                    // Name Input and validation
                    System.out.println("Please enter lead's name: ");
                    if (sc.nextLine().matches("/[A-Za-z]{3,30}$/")) {
                        newLead.setName(sc.nextLine());
                    } else {
                        System.out.println("Please enter a valid name: "); // Any suggestion to repeat this loop whenever the input is wrong?
                        newLead.setName(sc.nextLine());
                    }

                    // DOB Input and exception handling
                    System.out.println("Please enter lead's birthday (dd/MM/YYYY): ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/uuuu");
                    String date = sc.nextLine();
                    try {
                        LocalDate dob = LocalDate.parse(date, formatter);
                        newLead.setDob(dob);
                    } catch (DateTimeException e) {
                        System.out.println(date + " is not a valid date");
                        System.out.println("Please enter a valid lead's birthday (dd/MM/YYYY): ");
                        sc.nextLine();
                    }

                    // Gender input and validation
                    System.out.println("Please enter lead's gender (M/F): ");
                    char gender = sc.nextLine().charAt(0);
                    if (gender == 'm' || gender == 'f' || gender == 'M' || gender == 'F') {
                        newLead.setGender(true);
                    } else {
                        newLead.setGender(false);
                        System.out.println("Please enter a valid character (M/F): ");
                        sc.nextLine();
                    }

                    // Phone number input and validation
                    System.out.println("Please enter lead's phone number: ");
                    if (sc.nextLine().matches("/[^0-9]/")) {
                        newLead.setPhone(sc.nextLine());
                    } else {
                        System.out.println("Please enter a valid phone number: "); //same as the above
                        newLead.setPhone(sc.nextLine());
                    }

                    // Email Input (Lead)
                    System.out.println("Please enter lead's email: ");
                    String email = sc.nextLine();
                    if (sc.nextLine().matches("/[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+.[a-zA-Z]{2,4}/")) {
                        newLead.setEmail(sc.nextLine());
                    } else {
                        System.out.println("Please enter a valid email: "); //same as the above
                        newLead.setEmail(sc.nextLine());
                    }

                    // Address Input and validation
                    System.out.println("Please enter lead's address: ");
                    if (sc.nextLine().matches("/[A-Za-z0-9\\-\\\\,.]+/")) {
                        newLead.setAddress(sc.nextLine());
                    } else {
                        System.out.println("Please enter a valid address"); //same as above
                        newLead.setAddress(sc.nextLine());
                    }
                    break;

                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    System.out.println(interactions);
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
                    System.out.println("What is the potential of this interaction? ");
                    System.out.println("1. Positive");
                    System.out.println("2. Negative");
                    System.out.println("3. Neutral");
                    int choice = sc.nextInt();
                    switch (choice){
                        case 1:
                            newInteraction.setInteractionPot("Potential");
                        case 2:
                            newInteraction.setInteractionPot("Negative");
                        case 3:
                            newInteraction.setInteractionPot("Neutral");
                        default:
                            System.out.println("Please enter a valid input (1-3): ");
                    }

                    // Date of Interaction Input and Validation
                    System.out.println("Enter date of interaction (dd/MM/yyyy): ");
                    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d/M/uuuu");
                    String date2 = sc.nextLine();
                    try {
                        LocalDate interactionDate = LocalDate.parse(date2, formatter2);
                        newInteraction.setDateOfInteraction(interactionDate);
                    } catch (DateTimeException e) {
                        System.out.println(date2 + " is not a valid date");
                    }
                    break;
                case "7":
                    break;
                case "8":
                    break;
            }
        } while (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5")
                || input.equals("6") || input.equals("7") || input.equals("8"));
    }
}
