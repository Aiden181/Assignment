import java.time.LocalDate;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Lead {

    private String id;
    private String name;
    private LocalDate dob;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
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


    @Override
    public String toString() {
        return "id = " + id + '\'' +
                ", name = " + name +
                ", dob = " + dob +
                ", gender = " + gender +
                ", phone = " + phone +
                ", email = " + email +
                ", address = " + address + "\n";
    }

//    try {
//        File myObj = new File("leads.csv");
//        if (myObj.createNewFile()) {
//            System.out.println("File created: " + myObj.getName());
//        } else {
//            System.out.println("File already exists.");
//        }
//    } catch (IOException e) {
//        System.out.println("An error occurred.");
//        e.printStackTrace();
//    }
//    try {
//        FileWriter myWriter = new FileWriter("leads.csv");
//        myWriter.write(name + "," + dob + "," + gender + "," + phone + "," + email + "," + address);
//        myWriter.close();
//        System.out.println("Successfully wrote to the file.");
//    } catch (IOException e) {
//        System.out.println("An error occurred.");
//        e.printStackTrace();
//    }

}
