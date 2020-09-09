import java.io.*;
import java.net.URI;
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
        List<Lead> leads = new ArrayList<>();
        List<Interaction> interactions = new ArrayList<>();

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
            System.out.println("8. Delete a interaction\n");
            System.out.println("9. Report and Statistic");

            input = (sc.nextLine());
            // User Input Validation
            while (!(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5")
                    || input.equals("6") || input.equals("7") || input.equals("8") || input.equals("9"))) {
                System.out.println("Please enter a valid option: ");
                input = sc.nextLine();
            }
            switch (input) {
                case "1":
                    // Print out all created leads
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

                    String gender;
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

                    // Email Input and validation
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

                    // File Create and store all input
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

                            Lead.updateLead(updateLead, updateName);

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

                            Lead.updateLead(updateLead, updateDate);

                        }

                        // Enter new lead's gender and validation
                        case "gender" -> {

                            System.out.println("Please enter new lead's gender (M/F): ");
                            String updateGender = "";
                            inputGender = sc.nextLine().charAt(0);
                            if (inputGender == 'm' || inputGender == 'M') {
                                updateGender = "Male";
                            } else if (inputGender == 'f' || inputGender == 'F') {
                                updateGender = "Female";
                            } else {
                                updateGender = "false";
                            }

                            Lead.updateLead(updateLead, updateGender);

                        }

                        // Enter new lead's phone number and validation
                        case "phone" -> {
                            System.out.println("Please enter new lead's phone number (10 digit): ");
                            String updatePhone = sc.nextLine();
                            while (!updatePhone.matches("^\\d{10}$")) {
                                System.out.println("Please enter a valid phone number (10-digits): ");
                                updatePhone = sc.nextLine();
                            }

                            Lead.updateLead(updateLead, updatePhone);

                        }

                        // Enter new lead's email and validation
                        case "email" -> {
                            System.out.println("Please enter new lead's email: ");
                            String updateEmail = sc.nextLine();
                            while (!updateEmail.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
                                System.out.println("Please enter a valid email: ");
                                updateEmail = sc.nextLine();
                            }

                            Lead.updateLead(updateLead, updateEmail);

                        }

                        // Enter new lead's address and validation
                        case "address" -> {
                            System.out.println("Please enter new lead's address: ");
                            String updateAddress = sc.nextLine();
                            while (!updateAddress.matches("^[A-Z0-9 _]*[A-Z0-9][A-Za-z0-9 _]*$")) {
                                System.out.println("Please enter a valid address: ");
                                updateAddress = sc.nextLine();
                            }

                            Lead.updateLead(updateLead, updateAddress);

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
                    Lead.removeLead("leads.csv", deleteLead, 1);
                    break;
                case "5":

                    // Print out all created interactions
                    try {
                        File myObj2 = new File("interactions.csv");
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
                    break;
                case "6":
                    // Create new interaction
                    Interaction newInteraction = new Interaction();
                    interactions.add(newInteraction);

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
                    if (((means.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) && (means.toLowerCase().contains("email") || means.toLowerCase().contains("telephone") || means.toLowerCase().contains("face to face") || means.toLowerCase().contains("social media")))) {
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

                    //Create interaction file and store all input
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
                                scanner = new Scanner(new File("leads.csv"));
                                String id = "";
                                int lines = 1;
                                while (scanner.hasNext()) {
                                    String s = scanner.next();
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
                                BufferedWriter out = new BufferedWriter(new FileWriter("interactions.csv", true));
                                out.write(id2 + "," + date2 + "," + id + "," + means + "," + potential + "\n");
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
                    // Select interaction's ID to update
                    System.out.println("Enter interaction ID you want to update (inter_xxx): ");
                    String updateInt = sc.nextLine();

                    while (!updateInt.contains("lead_")) {
                        System.out.println("Please enter valid lead ID (lead_xxx): ");
                        updateInt = sc.nextLine();
                    }

                    //Select info of interactions to update
                    String infoOfInt;
                    System.out.println("Please enter info of lead you want to update (date/means/potential): ");
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

                            Interaction.updateInt(updateInt, updateIntDate);

                        }

                        // Update new interaction's means
                        case "means" -> {
                            System.out.println("Please enter new interaction's means (email/telephone/face to face/social media): ");
                            String updateMeans = sc.nextLine();
                            while (!((updateMeans.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) && (updateMeans.toLowerCase().contains("email") || updateMeans.toLowerCase().contains("telephone") || updateMeans.toLowerCase().contains("face to face") || updateMeans.toLowerCase().contains("social media")))) {
                                System.out.println("Please enter a valid mean of interaction (email/telephone/face to face/social media): ");
                                updateMeans = sc.nextLine();
                            }


                            Interaction.updateInt(updateInt, updateMeans);

                        }

                        // Update new interaction's potential
                        case "potential" -> {

                            System.out.println("Please enter new interaction's potential (positive, neutral, negative): ");
                            String updatePotential = sc.nextLine();
                            while (!((updatePotential.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) && (updatePotential.toLowerCase().contains("positive") || updatePotential.toLowerCase().contains("neutral") || updatePotential.toLowerCase().contains("negative")))) {
                                System.out.println("Please enter a valid mean: ");
                                updatePotential = sc.nextLine();
                            }

                            Interaction.updateInt(updateInt, updatePotential);

                        }

                        default -> {
                            System.out.println("There is no info of interactions match");
                        }
                    }
                    break;
                case "8":
                    // Select interaction's ID to delete
                    String deleteInteraction;
                    System.out.println("Enter interaction ID you want to delete (lead_xxx): ");
                    deleteInteraction = sc.nextLine();
                    while (!deleteInteraction.contains("lead_")) {
                        System.out.println("Please enter valid lead ID (lead_xxx): ");
                        deleteInteraction = sc.nextLine();
                    }
                    Interaction.removeInt("interactions.csv", deleteInteraction, 1);
                    break;

                case "9":
                    System.out.println("1. Leads Age Report");
                    System.out.println("2. Interaction Potential Report");
                    System.out.println("3. Interactions by Month Report");
                    int reportOption = sc.nextInt();
                    switch (reportOption) {
                        case 1:
                            // Leads Age Report
                            int countZeroToTen = 0;
                            int countTenToTwenty = 0;
                            int countTwentyToSixty = 0;
                            int countGreaterThanSixty = 0;
                           String searchDate;
                           try {
                               Scanner scanner = new Scanner(new File("interactions.csv"));
                               scanner.useDelimiter("[,\n]");
                               SimpleDateFormat sdf5 = new SimpleDateFormat("dd/MM/yyyy");
                               while (scanner.hasNext()){
                                   searchDate = scanner.next();

                                   // Convert searchDate into Date
                                   while (searchDate.equals(sdf5)){
                                       Date inputDate = sdf5.parse(searchDate);

                                       // Convert into age
                                       Date now = new Date();
                                       int days = (int)((now.getTime() - inputDate.getTime() / (1000 * 60 * 60 * 24)));
                                       int age = days / 365;
                                       if (age > 0 && age <= 10){
                                           countZeroToTen++;
                                       }else if (age <= 20){
                                           countTenToTwenty++;
                                       }else if (age <= 60){
                                           countTwentyToSixty++;
                                       }else if (age > 60){
                                           countGreaterThanSixty++;
                                       }
                                   }
                               }
                           }catch (Exception e){
                               e.printStackTrace();
                           }

                            System.out.println("Input: No input required");
                            System.out.println("0 - 10 (years old): " + countZeroToTen);
                            System.out.println("10 - 20 (years old): " + countTenToTwenty);
                            System.out.println("20 - 60 (years old): " + countTwentyToSixty);
                            System.out.println("> 60 (years old): " + countGreaterThanSixty + "\n");
                            break;
                        case 2:
                            // Asking for start - end day
                            SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
                            System.out.println("Enter start day: ");
                            String startDate = sc.nextLine();
                            while (true) {
                                try {
                                    Date startDate1 = sdf3.parse(startDate);
                                    break;
                                } catch (ParseException e) {
                                    System.out.println("Please enter a valid start date (dd/MM/YYYY): ");
                                    startDate = sc.nextLine();
                                }
                            }
                            System.out.println("Enter end day: ");
                            String endDate = sc.nextLine();
                            while (true) {
                                try {
                                    Date endDate1 = sdf3.parse(endDate);
                                    break;
                                } catch (ParseException e) {
                                    System.out.println("Please enter a valid end date (dd/MM/YYYY): ");
                                    endDate = sc.nextLine();
                                }
                            }
                            // Number of Interactions by potential Report
                            int countPositive = 0;
                            int countNegative = 0;
                            int countNeutral = 0;
                            String searchTerm;
                            try {
                                Scanner x = new Scanner(new File("interactions.csv"));
                                x.useDelimiter("[,\n]");
                                while (x.hasNext()) {
                                    searchTerm = x.next();
                                    if (searchTerm.toLowerCase().equals("positive")) {
                                        countPositive++;
                                    } else if (searchTerm.toLowerCase().equals("neutral")) {
                                        countNeutral++;
                                    } else if (searchTerm.toLowerCase().equals("negative")) {
                                        countNegative++;
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println("Input: " + startDate + " - " + endDate);
                            System.out.println("Positive: " + countPositive);
                            System.out.println("Negative: " + countNegative);
                            System.out.println("Neutral: " + countNeutral + "\n");
                            break;
                        case 3:
                            // Asking for start - end day
                            SimpleDateFormat sdf4 = new SimpleDateFormat("dd/MM/yyyy");
                            System.out.println("Enter start day: ");
                            String startDate2 = sc.nextLine();
                            while (true) {
                                try {
                                    Date startDate1 = sdf4.parse(startDate2);
                                    break;
                                } catch (ParseException e) {
                                    System.out.println("Please enter a valid start date (dd/MM/YYYY): ");
                                    startDate2 = sc.nextLine();
                                }
                            }
                            System.out.println("Enter end day: ");
                            String endDate2 = sc.nextLine();
                            while (true) {
                                try {
                                    Date endDate1 = sdf4.parse(endDate2);
                                    break;
                                } catch (ParseException e) {
                                    System.out.println("Please enter a valid end date (dd/MM/YYYY): ");
                                    endDate2 = sc.nextLine();
                                }
                            }
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
                            String dateInput;
                            Scanner scanner = new Scanner(new File("interactions.csv"));
                            scanner.useDelimiter("[,\n]");
                            SimpleDateFormat sdf6 = new SimpleDateFormat("dd/MM/yyyy");
                            while (scanner.hasNext()){
                                dateInput = scanner.next();
                                while (dateInput.equals(sdf6)){
                                    Date inputDate = sdf6.parse(dateInput);
                                    cal.setTime(inputDate);
                                    int month = cal.get(Calendar.MONTH);
                                    if (month == 0) {
                                        countJan++;
                                    } else if (month == 1) {
                                        countFeb++;
                                    } else if (month == 2) {
                                        countMar++;
                                    } else if (month == 3) {
                                        countApr++;
                                    } else if (month == 4) {
                                        countMay++;
                                    } else if (month == 5) {
                                        countJun++;
                                    } else if (month == 6) {
                                        countJul++;
                                    } else if (month == 7) {
                                        countAug++;
                                    } else if (month == 8) {
                                        countSep++;
                                    } else if (month == 9) {
                                        countOct++;
                                    } else if (month == 10) {
                                        countNov++;
                                    } else if (month == 1) {
                                        countDec++;
                                    }
                                }
                            }

                            System.out.println("Input: " + startDate2 + " - " + endDate2);
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
                    break;
            }
        } while (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5")
                || input.equals("6") || input.equals("7") || input.equals("8"));
    }

}
