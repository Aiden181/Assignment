import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

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

    public int getAge(){
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

    public static void removeLead(String filepath, String removeTerm, int positionOfTerm, String delimiter) {
        int position = positionOfTerm - 1;
        String tempFile = "temp.csv";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        String currentLine;
        String data[];

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
