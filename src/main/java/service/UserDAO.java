package service;

import model.User;
import java.util.List;

/*
 * Interface for data access operations related to the User entity.
 * This includes methods for user validation, retrieval, insertion, 
 * update, deletion, and password management. 
 */

public interface UserDAO {
	
    boolean validate(String gmail, String password);
    List<User> getCustomer(String gmail);
    boolean validateAdmin(String gmail, String password);
    boolean insertUser(String fname, String lname, String gmail, String password, String country, String gender, String dob, String mobile, String profileimage, String nic, String address);
    boolean updateCustomer(String id, String fname, String lname, String gmail, String password, String country, String gender, String dob, String mobile, String profileimage, String nic, String address);
    List<User> getCustomerDetails(String id);
    boolean deleteCustomer(String id);
    List<User> getAllUsers();
    boolean isEmailRegistered(String email);
    boolean isUserValidForPasswordReset(String email, String mobile, String nic);
    boolean updatePassword(String email, String newPassword);
}
