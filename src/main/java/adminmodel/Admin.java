package adminmodel;

import model.Person;

public class Admin extends Person {    // Admin inherit from the Person
    private int aid;
    private String apassword;
    private String afullname;
    private String admintype;

    //overload constructor
    public Admin(int aid, String agmail, String apassword, String afullname, String agender,
                 String adob, String amobile, String aaddress, String anic,String admintype) {

        super(agmail, agender, adob, amobile, aaddress, anic); // Inherited attributes
        this.aid = aid;
        this.apassword = apassword;
        this.afullname = afullname;
        this.admintype = admintype;
    }

    // Getters (encapsulation)
    public int getAid() { return aid; }
    public String getApassword() { return apassword; }
    public String getAfullname() { return afullname; }
    public String getAdmintype() { return admintype; }

    //polymorphism
    @Override
    public void displayProfile() {
        System.out.println("Admin: " + afullname);
        System.out.println("Email: " + gmail);
        System.out.println("NIC: " + nic);
    }
}
