import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AppManager {
    final String[][] messages;
    final List<Contact> contacts;
    private static final int MAX_MESSAGES = 100;

    public AppManager() {
        messages = new String[MAX_MESSAGES][6];
        contacts = new ArrayList<>();
        preloadMessages();
    }

    private void preloadMessages() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 50; i++) {
            messages[i][0] = String.valueOf(i + 1);
            messages[i][1] = "Fatima";
            messages[i][2] = "Recipient" + (i + 1);
            messages[i][3] = "This is a sample message number " + (i + 1);
            messages[i][4] = LocalDateTime.now().format(formatter);
            messages[i][5] = "false";
        }
    }

    // Add Contact
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    // Add a new message
    public void addMessage(int id, String sender, String recipient, String content, String timestamp) {
        for (int i = 0; i < MAX_MESSAGES; i++) {
            if (messages[i][0] == null) {
                messages[i][0] = String.valueOf(id);
                messages[i][1] = sender;
                messages[i][2] = recipient;
                messages[i][3] = content;
                messages[i][4] = timestamp;
                messages[i][5] = "false";
                break;
            }
        }
    }

    // View all messages
    public void displayMessages() {
        boolean found = false;
        for (int i = 0; i < MAX_MESSAGES; i++) {
            if (messages[i][0] != null) {
                System.out.println("Message ID: " + messages[i][0] +
                        ", Sender: " + messages[i][1] +
                        ", Recipient: " + messages[i][2] +
                        ", Content: " + messages[i][3] +
                        ", Timestamp: " + messages[i][4] +
                        ", Seen: " + messages[i][5]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No messages found.");
        }
    }

    // Get unseen messages
    public void displayUnseenMessages() {
        for (int i = 0; i < MAX_MESSAGES; i++) {
            if (messages[i][0] != null && messages[i][5].equals("false")) {
                System.out.println("Message ID: " + messages[i][0] +
                        ", Sender: " + messages[i][1] +
                        ", Recipient: " + messages[i][2] +
                        ", Content: " + messages[i][3] +
                        ", Timestamp: " + messages[i][4] +
                        ", Seen: " + messages[i][5]);
            }
        }
    }

    // Mark a message as seen by ID
    public boolean markMessageAsSeen(int id) {
        for (int i = 0; i < MAX_MESSAGES; i++) {
            if (messages[i][0] != null && Integer.parseInt(messages[i][0]) == id) {
                messages[i][5] = "true";
                return true;
            }
        }
        return false;
    }

    // Search messages by recipient
    public void searchMessagesByRecipient(String recipient) {
        boolean found = false;
        for (int i = 0; i < MAX_MESSAGES; i++) {
            if (messages[i][0] != null && messages[i][2].equalsIgnoreCase(recipient)) {
                System.out.println("Message ID: " + messages[i][0] +
                        ", Sender: " + messages[i][1] +
                        ", Recipient: " + messages[i][2] +
                        ", Content: " + messages[i][3] +
                        ", Timestamp: " + messages[i][4] +
                        ", Seen: " + messages[i][5]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No messages found for the recipient: " + recipient);
        }
    }
    // Delete a message by ID
    public boolean deleteMessage(int id) {
        for (int i = 0; i < MAX_MESSAGES; i++) {
            if (messages[i][0] != null && Integer.parseInt(messages[i][0]) == id) {
                for (int j = 0; j < 6; j++) {
                    messages[i][j] = null;
                }
                return true;
            }
        }
        return false;
    }

    // Update message content by ID
    public boolean updateMessageById(int id, String newContent) {
        for (int i = 0; i < MAX_MESSAGES; i++) {
            if (messages[i][0] != null && Integer.parseInt(messages[i][0]) == id) {
                messages[i][3] = newContent;
                return true;
            }
        }
        return false;
    }
    // View all contacts
    public void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            for (Contact contact : contacts) {
                System.out.println("ID: " + contact.ID() +
                        ", Name: " + contact.name() +
                        ", Phone: " + contact.phoneNumber());
            }
        }
    }
}
