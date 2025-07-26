package adminmodel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//encapsulation
public class Feedback {
    private int feedbackId;//fields representing feedback data
    private int messageId;
    private int userId;
    private int adminId;
    private String feedbackMessage;
    private LocalDateTime feedbackDatetime;
    private String category;
    private String adminMobile;
    private String adminName;
    private String adminEmail;
    private String language;
    private String attachmentFile;

   // Constructor to initialize all fields of the Feedback object
    public Feedback(int feedbackId, int messageId, int userId, int adminId, String feedbackMessage,
                    LocalDateTime feedbackDatetime, String category, String adminMobile,
                    String adminName, String adminEmail, String language, String attachmentFile) {
        this.feedbackId = feedbackId;
        this.messageId = messageId;
        this.userId = userId;
        this.adminId = adminId;
        this.feedbackMessage = feedbackMessage;
        this.feedbackDatetime = feedbackDatetime;
        this.category = category;
        this.adminMobile = adminMobile;
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.language = language;
        this.attachmentFile = attachmentFile;
    }

    // Getters
    public int getFeedbackId() {
        return feedbackId;
    }

    public int getMessageId() {
        return messageId;
    }

    public int getUserId() {
        return userId;
    }

    public int getAdminId() {
        return adminId;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }

    public LocalDateTime getFeedbackDatetime() {
        return feedbackDatetime;
    }

    public String getCategory() {
        return category;
    }

    public String getAdminMobile() {
        return adminMobile;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public String getLanguage() {
        return language;
    }

    public String getAttachmentFile() {
        return attachmentFile;
    }

    // Abstracted Methods

    // Combine and return formatted admin contact
    public String getAdminContactInfo() {
        return adminName + " | " + adminEmail + " | " + adminMobile;
    }

    // Returns formatted datetime for display
    public String getFormattedFeedbackDatetime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return feedbackDatetime.format(formatter);
    }

    // Check if attachment exists
    public boolean isAttachmentPresent() {
        return attachmentFile != null && !attachmentFile.trim().isEmpty();
    }

    // Check if feedback is in a specific language
    public boolean isInLanguage(String lang) {
        return language != null && language.equalsIgnoreCase(lang);
    }

    // String representation
    @Override
    public String toString() {
        return "Feedback from Admin [" + adminName + "] on " + getFormattedFeedbackDatetime() + ": " + feedbackMessage;
    }
}
