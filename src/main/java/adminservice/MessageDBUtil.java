package adminservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import admincontrroller.DBConnect;
import model.ContactUs;

public class MessageDBUtil {
    
    // Fetch all contact messages (admin view)
    public static List<ContactUs> getAllMessages() {
        List<ContactUs> messages = new ArrayList<>();

        String sql = "SELECT * FROM contactus ORDER BY datetime DESC";

        try (
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                ContactUs c = new ContactUs(
                    rs.getInt("mid"),
                    rs.getInt("cid"),
                    rs.getString("cfname"),
                    rs.getString("cgmail"),
                    rs.getString("cmobile"),
                    rs.getString("cmessage"),
                    rs.getString("datetime")
                );
                messages.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return messages;
    }
}
