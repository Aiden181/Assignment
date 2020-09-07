
import java.io.*;
import java.util.Date;
import java.util.Scanner;


public class Lead {

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
        return "id = " + id +
                ", name = " + name +
                ", dob = " + dob +
                ", gender = " + gender +
                ", phone = " + phone +
                ", email = " + email +
                ", address = " + address + "\n";
    }

    public static void removeLead(String filepath, String removeTerm, int positionOfTerm) {
        int position = positionOfTerm - 1;
        String tempFile = "leads.csv";
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


    public static void updateLead(String editTerm,String newData) {
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
            FileWriter fw = new FileWriter(newFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File(filepath));
            x.useDelimiter(",|\\n");

            while (x.hasNext()) {
                id = x.next();
                name = x.next();
                date = x.next();
                gender = x.next();
                phone = x.next();
                email = x.next();
                address = x.next();

                if (id.equals(editTerm)) {
                    if (!newData.equals(name)) {
                        pw.print(id + "," + newData + "," + date + "," + gender + "," + phone + "," + email + "," + address);
                    } else if (!newData.equals(date)) {
                        pw.print(id + "," + name + "," + newData + "," + gender + "," + phone + "," + email + "," + address);
                    } else if (!newData.equals(gender)) {
                        pw.print(id + "," + name + "," + date + "," + newData + "," + phone + "," + email + "," + address);
                    } else if (!newData.equals(phone)) {
                        pw.print(id + "," + name + "," + date + "," + gender + "," + newData + "," + email + "," + address);
                    } else if (!newData.equals(email)) {
                        pw.print(id + "," + name + "," + date + "," + gender + "," + phone + "," + newData + "," + address);
                    } else if (!newData.equals(address)) {
                        pw.print(id + "," + name + "," + date + "," + gender + "," + phone + "," + email + "," + newData);
                    }
                } else {
                    pw.print(id + "," + name + "," + date + "," + gender + "," + phone + "," + email + "," + address);
            }}

            x.close();
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
