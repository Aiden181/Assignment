import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            switch (input){
                case "1":
                    System.out.println(leads);

                case "2":
                    Lead newLead = new Lead();
                    leads.add(newLead);
                    System.out.println("Please enter lead's name: ");
                    newLead.setName(sc.nextLine());
                    System.out.println("Please enter lead's birthday (dd/MM/YYYY): ");
                    // Blank
                    System.out.println("Please enter lead's gender (M/F): ");
                    char gender = sc.nextLine().charAt(0);
                    if (gender == 'm' || gender == 'f' || gender == 'M' || gender == 'F'){
                        newLead.setGender(true);
                    }else {
                        newLead.setGender(false);
                    }
                    System.out.println("Please enter lead's phone number: ");
                    newLead.setPhone(sc.nextLine());
                    System.out.println("Please enter lead's email: ");
                    newLead.setEmail(sc.nextLine());
                    System.out.println("Please enter lead's address: ");
                    newLead.setAddress(sc.nextLine());

                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    System.out.println(interactions);
                    break;
                case "6":
                    Interaction newInteraction = new Interaction();
                    interactions.add(newInteraction);
                    System.out.println("Enter date of interaction: ");
                    // Blank
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
