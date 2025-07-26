
package model;

public abstract class Person {  //super class
    protected String gmail;
    protected String gender;
    protected String dob;
    protected String mobile;
    protected String address;
    protected String nic;

  //overload constructor
    public Person(String gmail, String gender, String dob, String mobile, String address, String nic) {
        this.gmail = gmail;
        this.gender = gender;
        this.dob = dob;
        this.mobile = mobile;
        this.address = address;
        this.nic = nic;
    }

    // Getters only (encapsulation)
    public String getGmail() { return gmail; }
    public String getGender() { return gender; }
    public String getDob() { return dob; }
    public String getMobile() { return mobile; }
    public String getAddress() { return address; }
    public String getNic() { return nic; }

    // abstract method for polymorphism
    public abstract void displayProfile();
    
}
