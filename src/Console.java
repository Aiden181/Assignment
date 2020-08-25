import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        String input = null;
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
                if (input.equals("1")){
                    System.out.println(leads);
                }else if (input.equals("2")){
                    Lead newLead = new Lead();
                    leads.add(newLead);
                    System.out.println("Please enter lead's name: ");
                    newLead.setName(sc.nextLine());
                    System.out.println("Please enter lead's birthday: ");
                    // Blank
                    System.out.println("Please enter lead's gender (M/F): ");
                    if (sc.nextLine().toLowerCase().equals("m") || sc.nextLine().toLowerCase().equals("f")){
                        newLead.setGender(true);
                    }else {
                        newLead.setGender(false);
                    }
                    System.out.println("Please enter lead's phone number: ");
                    newLead.setPhone(sc.nextLine());
                    System.out.println("Please enter lead's address: ");
                    newLead.setAddress(sc.nextLine());
                }else if (input.equals("3")){

                }else if (input.equals("4")){

                }else if (input.equals("5")){
                    System.out.println(interactions);
                }else if (input.equals("6")){
                    Interaction newInteraction = new Interaction();
                    interactions.add(newInteraction);
                    System.out.println("Enter date of interaction: ");
                    // Blank
                }else if (input.equals("7")){

                }
        }while (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5")
        || input.equals("6") || input.equals("7") || input.equals("8"));
    }
}
