import java.io.*;
import java.util.Date;
import java.util.Scanner;


public class Lead implements ViewFile,Delete {

    private String id;
    private String name;
    private Date dob;
    private boolean gender;
    private String phone;
    private String email;
    private String address;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        Date now = new Date();
        int days = (int) ((now.getTime() - this.getDob().getTime()) / (1000 * 60 * 60 * 24));

        int age = (days / 365);
        return age;
    }


    @Override
    public String toString() {
        return
                " name = " + name +
                ", dob = " + dob +
                ", gender = " + gender +
                ", phone = " + phone +
                ", email = " + email +
                ", address = " + address;
    }

    public void view(String filepath) {
        try {
            File myObj = new File(filepath);
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
    }

    public void createFileLead(String filepath,String name,String date,String gender,String phone,String email,String address) {
        try {
            File myObj = new File(filepath);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                try {
                    String id = "";
                    int lines = 1;
                    String str1 = Integer.toString(lines);
                    id = "lead_00" + str1;
                    FileWriter myWriter = new FileWriter(filepath);
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
    }

    public void updateLead(String editTerm,String newName,String newDate,String newGender,String newPhone,String newEmail,String newAddress) {
        String tempFile = "temp.csv";
        String filepath = "leads.csv";

        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        String id;
        String name;
        String date;
        String gender;
        String phone;
        String email;
        String address;

        try {
            FileWriter fw = new FileWriter(newFile);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner sc = new Scanner(oldFile);

            while (sc.hasNext()) {
                String leadUpdate = sc.nextLine();
                String[] lead_arr = leadUpdate.split(",");
                id = lead_arr[0];
                name = lead_arr[1];
                date = lead_arr[2];
                gender = lead_arr[3];
                phone = lead_arr[4];
                email = lead_arr[5];
                address = lead_arr[6];
                System.out.println(name);
                if (id.equals(editTerm)) {
                    if (!newName.equals("")) {
                        pw.println(id + "," + newName + "," + date + "," + gender + "," + phone + "," + email + "," + address);
                    } else if (!newDate.equals("")) {
                        pw.println(id + "," + name + "," + newDate + "," + gender + "," + phone + "," + email + "," + address);
                    } else if (!newGender.equals("")) {
                        pw.println(id + "," + name + "," + date + "," + newGender + "," + phone + "," + email + "," + address);
                    } else if (!newPhone.equals("")) {
                        pw.println(id + "," + name + "," + date + "," + gender + "," + newPhone + "," + email + "," + address);
                    } else if (!newEmail.equals("")) {
                        pw.println(id + "," + name + "," + date + "," + gender + "," + phone + "," + newEmail + "," + address);
                    } else if (!newAddress.equals("")) {
                        pw.println(id + "," + name + "," + date + "," + gender + "," + phone + "," + email + "," + newAddress);
                    }
                }
                else {
                    pw.println(id + "," + name + "," + date + "," + gender + "," + phone + "," + email + "," + address);
                }
            }
            sc.close();
            pw.flush();
            pw.close();

            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(oldFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(String filepath, String removeTerm, int positionOfTerm) {
        int position = positionOfTerm - 1;
        String tempFile = "temp.csv";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        String currentLine;
        String[] data;

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
}
