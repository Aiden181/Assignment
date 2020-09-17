import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

public class Console {
    public static void main(String[] args) throws IOException, ParseException {
        String input;
        Scanner sc = new Scanner(System.in);
        Lead myLead = new Lead();
        Interaction myInteraction = new Interaction();
        Report myReport = new Report();

        do {
            System.out.println("Managing Leads");
            System.out.println("1. View all leads");
            System.out.println("2. Create new lead");
            System.out.println("3. Update a lead");
            System.out.println("4. Delete a lead\n");
            System.out.println("Managing Interactions");
            System.out.println("5. View all interactions");
            System.out.println("6. Create new interaction");
            System.out.println("7. Update an interaction");
            System.out.println("8. Delete an interaction\n");
            System.out.println("9. Reporting and Statistic\n");
            System.out.println("10. System exit");

            String input1="0";
            input = (sc.nextLine());
            // User Input Validation
            while (!(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5")
                    || input.equals("6") || input.equals("7") || input.equals("8") || input.equals("9") || input.equals("10"))) {
                System.out.println("Please enter a valid option: ");
                input = sc.nextLine();
            }
            switch (input) {
                case "1":
                    // Print out all created leads
                    myLead.view("leads.csv");
                    break;
                case "2":
                    // Creating new lead

                    // Name Input and validation
                    System.out.println("Please enter lead's name: ");
                    String name = sc.nextLine();
                    while (!name.matches("^[A-Z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) {
                        System.out.println("Please enter a valid name: ");
                        name = sc.nextLine();
                    }

                    // DOB Input and exception handling
                    System.out.println("Please enter lead's birthday (dd/MM/YYYY): ");
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String date = sc.nextLine();
                    while (true) {
                        try {
                            Date dob = sdf.parse(date);
                            break;
                        } catch (ParseException e) {
                            System.out.println(date + " is not a valid date");
                            System.out.println("Please enter a valid lead's birthday (dd/MM/YYYY): ");
                            date = sc.nextLine();
                        }
                    }

                    // Gender input and validation
                    System.out.println("Please enter lead's gender (M/F): ");
                    String gender;
                    char inputGender = sc.nextLine().charAt(0);
                    while (!((inputGender == 'm') || (inputGender == 'f') || (inputGender == 'F') || (inputGender == 'M'))) {
                        System.out.println("Please enter a valid gender (M/F): ");
                        inputGender = sc.nextLine().charAt(0);
                    }
                    if (inputGender == 'm' || inputGender == 'M') {
                        gender = "Male";
                    } else {
                        gender = "Female";
                    }

                    // Phone number input and validation
                    System.out.println("Please enter lead's phone number (10-digits): ");
                    String phone = sc.nextLine();
                    while (!phone.matches("^\\d{10}$")) {
                        System.out.println("Please enter a valid phone number (10-digits): ");
                        phone = sc.nextLine();
                    }

                    // Email Input and validation
                    System.out.println("Please enter lead's email: ");
                    String email = sc.nextLine();
                    while (!email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
                        System.out.println("Please enter a valid email: ");
                        email = sc.nextLine();
                    }

                    // Address Input and validation
                    System.out.println("Please enter lead's address: ");
                    String address = sc.nextLine();
                    while (!address.matches("^[A-Z0-9 _]*[A-Z0-9][A-Za-z0-9 _]*$")) {
                        System.out.println("Please enter a valid address: ");
                        address = sc.nextLine();
                    }

                    // File Create and store all input
                    myLead.createFileLead("leads.csv",name,date,gender,phone,email,address);

                    break;
                case "3":
                    // Select ID to update
                    System.out.println("Enter lead ID you want to update (lead_xxx): ");
                    String updateLead = sc.nextLine();

                    while (!updateLead.contains("lead_")) {
                        System.out.println("Please enter valid lead ID (lead_xxx): ");
                        updateLead = sc.nextLine();
                    }

                    // Select info of lead to update
                    String infoOfLead;
                    System.out.println("Please enter info of lead you want to update (name/date/gender/phone/email/address): ");
                    infoOfLead = sc.nextLine();

                    switch (infoOfLead) {
                        // Enter new lead's name and validation
                        case "name" -> {
                            System.out.println("Please enter new lead's name: ");
                            String updateName = sc.next();
                            while (!updateName.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) {
                                System.out.println("Please enter a valid name: ");
                                updateName = sc.next();
                            }

                            myLead.updateLead(updateLead, updateName,"","","","","");

                        }

                        // Enter new lead's date and validation
                        case "date" -> {
                            System.out.println("Please enter new lead's birthday (dd/MM/YYYY): ");
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/uuuu");
                            String updateDate = sc.nextLine();
                            while (true) {
                                try {
                                    LocalDate dob = LocalDate.parse(updateDate, formatter);
                                    break;
                                } catch (DateTimeException e) {
                                    System.out.println(updateDate + " is not a valid date");
                                    System.out.println("Please enter a valid lead's birthday (dd/MM/YYYY): ");
                                    updateDate = sc.nextLine();
                                }
                            }

                            myLead.updateLead(updateLead,"", updateDate,"","","","");

                        }

                        // Enter new lead's gender and validation
                        case "gender" -> {

                            System.out.println("Please enter new lead's gender (M/F): ");
                            String updateGender;
                            inputGender = sc.nextLine().charAt(0);
                            while (!((inputGender == 'm') || (inputGender == 'f') || (inputGender == 'F') || (inputGender == 'M'))) {
                                System.out.println("Please enter a valid gender (M/F): ");
                                inputGender = sc.nextLine().charAt(0);
                            }
                            if (inputGender == 'm' || inputGender == 'M') {
                                updateGender = "Male";
                            } else {
                                updateGender = "Female";
                            }

                            myLead.updateLead(updateLead,"","", updateGender,"","","");

                        }

                        // Enter new lead's phone number and validation
                        case "phone" -> {
                            System.out.println("Please enter new lead's phone number (10 digit): ");
                            String updatePhone = sc.nextLine();
                            while (!updatePhone.matches("^\\d{10}$")) {
                                System.out.println("Please enter a valid phone number (10-digits): ");
                                updatePhone = sc.nextLine();
                            }

                            myLead.updateLead(updateLead,"","","",updatePhone,"","");

                        }

                        // Enter new lead's email and validation
                        case "email" -> {
                            System.out.println("Please enter new lead's email: ");
                            String updateEmail = sc.nextLine();
                            while (!updateEmail.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
                                System.out.println("Please enter a valid email: ");
                                updateEmail = sc.nextLine();
                            }

                            myLead.updateLead(updateLead, "","","","",updateEmail,"");

                        }

                        // Enter new lead's address and validation
                        case "address" -> {
                            System.out.println("Please enter new lead's address: ");
                            String updateAddress = sc.nextLine();
                            while (!updateAddress.matches("^[A-Z0-9 _]*[A-Z0-9][A-Za-z0-9 _]*$")) {
                                System.out.println("Please enter a valid address: ");
                                updateAddress = sc.nextLine();
                            }

                            myLead.updateLead(updateLead,"","","","","",updateAddress);

                        }

                        default -> {
                            System.out.println("There is no info of leads match");
                        }
                    }
                    break;
                case "4":
                    // Select lead ID to delete
                    System.out.println("Enter lead ID you want to delete (lead_xxx): ");
                    String deleteLead = sc.nextLine();
                    while (!deleteLead.contains("lead_")) {
                        System.out.println("Please enter valid lead ID (lead_xxx): ");
                        deleteLead = sc.nextLine();
                    }
                    // Confirmation to delete related interaction
                    System.out.println("Do you want to delete the related interaction (Yes/No):");
                    String confirmation = sc.nextLine();
                    if (confirmation.equals("Yes")) {
                        myLead.remove("leads.csv", deleteLead, 1);
                        myInteraction.remove("interactions.csv", deleteLead, 3);
                    } else {
                        myLead.remove("leads.csv", deleteLead, 1);
                    }
                    break;
                case "5":
                    // Print out all created interactions

                    myInteraction.view("interactions.csv");
                    break;
                case "6":
                    // Create new interaction

                    // Date of Interaction Input and Validation
                    System.out.println("Enter date of interaction (dd/MM/yyyy): ");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
                    String date2 = sc.nextLine();
                    while (true) {
                        try {
                            Date interactionDate = sdf2.parse(date2);
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

                    //Potential of each interaction Input and Validation
                    System.out.println("Enter the potential of each interaction (positive, neutral, negative):");
                    String potential = sc.nextLine();
                    while (!((potential.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) && (potential.toLowerCase().contains("positive") || potential.toLowerCase().contains("neutral") || potential.toLowerCase().contains("negative")))) {
                        System.out.println("Please enter a valid potential: ");
                        potential = sc.nextLine();
                    }

                    //Create interaction file and store all input
                    myInteraction.createIntFile("interactions.csv",date2,means,potential);

                    break;
                case "7":
                    // Select interaction's ID to update
                    System.out.println("Enter interaction ID you want to update (inter_xxx): ");
                    String updateInt = sc.nextLine();

                    while (!updateInt.contains("inter_")) {
                        System.out.println("Please enter valid interaction ID (lead_xxx): ");
                        updateInt = sc.nextLine();
                    }

                    //Select info of interactions to update
                    String infoOfInt;
                    System.out.println("Please enter info of interaction you want to update (date/means/potential): ");
                    infoOfInt = sc.nextLine();

                    switch (infoOfInt) {

                        // Update new interaction's date
                        case "date" -> {
                            System.out.println("Please enter new interaction's date (dd/MM/YYYY): ");
                            SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
                            String updateIntDate = sc.nextLine();
                            while (true) {
                                try {
                                    Date interactionDate = sdf3.parse(updateIntDate);
                                    break;
                                } catch (ParseException | DateTimeException e) {
                                    System.out.println(updateIntDate + " is not a valid date");
                                    System.out.println("Please enter a valid interaction date (dd/MM/YYYY): ");
                                    updateIntDate = sc.nextLine();
                                }
                            }

                            myInteraction.updateInt(updateInt, updateIntDate,"","");

                        }

                        // Update new interaction's means
                        case "means" -> {
                            System.out.println("Please enter new interaction's means (email/telephone/face to face/social media): ");
                            String updateMeans = sc.nextLine();
                            while (!((updateMeans.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) && (updateMeans.toLowerCase().contains("email") || updateMeans.toLowerCase().contains("telephone") || updateMeans.toLowerCase().contains("face to face") || updateMeans.toLowerCase().contains("social media")))) {
                                System.out.println("Please enter a valid mean of interaction (email/telephone/face to face/social media): ");
                                updateMeans = sc.nextLine();
                            }

                            myInteraction.updateInt(updateInt,"",updateMeans,"");

                        }

                        // Update new interaction's potential
                        case "potential" -> {

                            System.out.println("Please enter new interaction's potential (positive, neutral, negative): ");
                            String updatePotential = sc.nextLine();
                            while (!((updatePotential.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) && (updatePotential.toLowerCase().contains("positive") || updatePotential.toLowerCase().contains("neutral") || updatePotential.toLowerCase().contains("negative")))) {
                                System.out.println("Please enter a valid potential: ");
                                updatePotential = sc.nextLine();
                            }

                            myInteraction.updateInt(updateInt,"","", updatePotential);

                        }

                        default -> {
                            System.out.println("There is no info of interactions match");
                        }
                    }
                    break;
                case "8":
                    // Select interaction's ID to delete
                    String deleteInteraction;
                    System.out.println("Enter interaction ID you want to delete (inter_xxx): ");
                    deleteInteraction = sc.nextLine();
                    while (!deleteInteraction.contains("inter_")) {
                        System.out.println("Please enter valid inter ID (inter_xxx): ");
                        deleteInteraction = sc.nextLine();
                    }
                    myInteraction.remove("interactions.csv", deleteInteraction, 1);
                    break;
                case "9":
                    System.out.println("1. Leads Age Report");
                    System.out.println("2. Interaction Potential Report");
                    System.out.println("3. Interactions by Month Report");
                    int reportOption = sc.nextInt();
                    switch (reportOption) {
                        case 1:
                            // Leads Age Report
                            myReport.countLeadByAge("leads.csv");

                            break;
                        case 2:
                            // Asking for start day
                            SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
                            sc = new Scanner(System.in);
                            System.out.println("Enter start day: ");
                            String startDate = sc.nextLine();

                            // Asking for end day
                            System.out.println("Enter end day: ");
                            String endDate = sc.nextLine();

                            // Number of Interactions by potential Report
                            myReport.countIntPotential("interactions.csv",startDate,endDate);

                            break;

                        case 3:
                            // Asking for start day
                            System.out.println("Enter start day: ");
                            sc = new Scanner(System.in);
                            String startDate2 = sc.nextLine();

                            // Asking for end day
                            System.out.println("Enter end day: ");
                            String endDate2 = sc.nextLine();

                            // Number of Interactions by month report
                            myReport.countIntMonth("interactions.csv",startDate2,endDate2);
                            break;
                            }

                            break;
                case "10":
                    input1 = "1";
                    break;
                    }

            if (input1.equals("1")) {
                break;
            }

            } while (true);
    }

}
