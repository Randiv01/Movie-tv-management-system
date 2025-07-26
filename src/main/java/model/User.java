
package model;

public class User extends Person {    // User inherit from the Person
    private int id;
    private String fname;
    private String lname;
    private String password;
    private String country;
    private String profileimage;

    //overload constructor
    public User(int id, String fname, String lname, String gmail, String password, String country,
                String gender, String dob, String mobile, String profileimage, String nic, String address) {

        super(gmail, gender, dob, mobile, address, nic); // Inherited attributes
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.country = country;
        this.profileimage = profileimage;
    }

    // Getters (encapsulation)
    public int getId() { return id; }
    public String getFname() { return fname; }
    public String getLname() { return lname; }
    public String getPassword() { return password; }
    public String getCountry() { return country; }
    public String getProfileimage() { return profileimage; }
    
    
    //polymorphism
    @Override
    public void displayProfile() {
        System.out.println("User: " + fname + " " + lname);
        System.out.println("Email: " + gmail);
        System.out.println("Mobile: " + mobile);
    }
}
