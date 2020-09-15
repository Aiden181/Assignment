import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Report {
    public void countLeadByAge(String filepath){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int countZeroToTen = 0;
        int countTenToTwenty = 0;
        int countTwentyToSixty = 0;
        int countGreaterThanSixty = 0;
        String searchDate;
        try {
            Scanner scanner = new Scanner(new File(filepath));
            scanner.useDelimiter("[,\n]");
            while (scanner.hasNext()) {
                searchDate = scanner.next();
                String regex = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
                // Convert searchDate into Date
                if (searchDate.matches(regex)) {
                    Date inputDate = sdf.parse(searchDate);

                    // Convert into age
                    Date now = new Date();
                    int days = (int) (((now.getTime() - inputDate.getTime()) / (1000 * 60 * 60 * 24)));
                    int age = days / 365;
                    if (age > 0 && age <= 10) {
                        countZeroToTen++;
                    } else if (age <= 20) {
                        countTenToTwenty++;
                    } else if (age <= 60) {
                        countTwentyToSixty++;
                    } else {
                        countGreaterThanSixty++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Input: No input required");
        System.out.println("0 - 10 (years old): " + countZeroToTen);
        System.out.println("10 - 20 (years old): " + countTenToTwenty);
        System.out.println("20 - 60 (years old): " + countTwentyToSixty);
        System.out.println("> 60 (years old): " + countGreaterThanSixty + "\n");
    }

    public void countIntPotential(String filepath,String startDate,String endDate){
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate1 = new Date();
        while (true) {
            try {
                // Parse String into Date Object
                startDate1 = sdf3.parse(startDate);
                // Print Format
                DateFormat dFormat = new SimpleDateFormat("MMMM dd, yyyy");
                // Format date into Print Format
                startDate = dFormat.format(startDate1);
                break;
            } catch (ParseException e) {
                System.out.println("Please enter a valid start date (dd/MM/YYYY): ");
                startDate = sc.nextLine();
            }
        }
        Date endDate1 = new Date();
        while (true) {
            try {
                endDate1 = sdf3.parse(endDate);
                // Print Format
                DateFormat dFormat = new SimpleDateFormat("MMMM dd,yyyy");
                // Format date into Print Format
                endDate = dFormat.format(endDate1);
                break;
            } catch (ParseException e) {
                System.out.println("Please enter a valid end date (dd/MM/YYYY): ");
                endDate = sc.nextLine();
            }
        }
        int countPositive = 0;
        int countNegative = 0;
        int countNeutral = 0;
        String searchTerm;
        try {
            Scanner x = new Scanner(new File(filepath));
            while (x.hasNext()) {
                searchTerm = x.nextLine();
                String[] arr = searchTerm.split(",");
                Date IntDate = sdf3.parse(arr[1]);
                if (startDate1.getTime() < IntDate.getTime() && IntDate.getTime() < endDate1.getTime()) {
                    if (searchTerm.toLowerCase().contains("positive")) {
                        countPositive++;
                    } else if (searchTerm.toLowerCase().contains("neutral")) {
                        countNeutral++;
                    } else if (searchTerm.toLowerCase().contains("negative")) {
                        countNegative++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Input: " + startDate + " - " + endDate);
        System.out.println("Positive: " + countPositive);
        System.out.println("Negative: " + countNegative);
        System.out.println("Neutral: " + countNeutral + "\n");
    }

    public void countIntMonth(String filepath,String startDate2,String endDate2) throws ParseException, FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf4 = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate3 = new Date();
        while (true) {
            try {
                startDate3 = sdf4.parse(startDate2);
                // Print Format
                DateFormat dFormat = new SimpleDateFormat("MMMM dd, yyyy");
                // Format date into Print Format
                startDate2 = dFormat.format(startDate3);
                break;
            } catch (ParseException e) {
                System.out.println("Please enter a valid start date (dd/MM/YYYY): ");
                startDate2 = sc.nextLine();
            }
        }

        Date endDate3 = new Date();
        while (true) {
            try {
                endDate3 = sdf4.parse(endDate2);
                // Print Format
                DateFormat dFormat = new SimpleDateFormat("MMMM dd,yyyy");
                // Format date into Print Format
                endDate2 = dFormat.format(endDate3);
                break;
            } catch (ParseException e) {
                System.out.println("Please enter a valid end date (dd/MM/YYYY): ");
                endDate2 = sc.nextLine();
            }
        }
        int countJan = 0;
        String dateInput;
        Scanner scanner = new Scanner(new File(filepath));
        while (scanner.hasNext()) {
            dateInput = scanner.nextLine();
            String[] arr = dateInput.split(",");
            sdf4 = new SimpleDateFormat("dd/MM/yyyy");
            Date IntDate2 = sdf4.parse(arr[1]);
            if (startDate3.getTime() < IntDate2.getTime() && IntDate2.getTime() < endDate3.getTime()) {
                countJan++;
            }
        }
        String[] arr2 = new String[countJan];
        scanner.close();
        scanner = new Scanner(new File(filepath));
        int i = 0;
        while (scanner.hasNext()){
            dateInput = scanner.nextLine();
            String[] arr = dateInput.split(",");
            sdf4 = new SimpleDateFormat("dd/MM/yyyy");
            Date IntDate2 = sdf4.parse(arr[1]);
            String onlyMonth = arr[1].split("/")[1];
            String onlyYear = arr[1].split("/")[2];
            if (startDate3.getTime() < IntDate2.getTime() && IntDate2.getTime() < endDate3.getTime()) {
                arr2[i++] = onlyMonth + "/" + onlyYear;
            }
        }
        String[] loopMonth = new String[i+1];
        i = 0;
        for (String dateByMonth:arr2){
            int countMonth = 0;
            for (String dateMonth:arr2) {
                if (dateByMonth.equals(dateMonth)){
                    countMonth++;
                }
            }
            List<String> list = Arrays.asList(loopMonth);
            if(!list.contains(dateByMonth)){
                loopMonth[i++] = dateByMonth;
                SimpleDateFormat sdf5 = new SimpleDateFormat("MM/yyyy");
                Date dateB = new Date();
                dateB = sdf5.parse(dateByMonth);
                DateFormat dFormat = new SimpleDateFormat("MMMM ,yyyy");
                dateByMonth = dFormat.format(dateB);
                System.out.println(dateByMonth);
                System.out.println(countMonth);
            }

        }
    }

}
