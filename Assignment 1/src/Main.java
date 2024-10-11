import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AppManager appManager = new AppManager();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        int messageIdCounter = 51;
        int contactIdCounter = 1;

        String sender = "Fatima";

        while (true) {
            System.out.println("\n      _________Message Application_________");
            System.out.println("                1. Send Message");
            System.out.println("                2. View All Messages");
            System.out.println("                3. View Unseen Messages");
            System.out.println("                4. Search Messages");
            System.out.println("                5. Mark Message as Seen");
            System.out.println("                6. Delete Message");
            System.out.println("                7. Update Message");
            System.out.println("                8. Add Contact");
            System.out.println("                9. View Contacts");
            System.out.println("                10. Exit");
            System.out.print("       Choose an option: ");


            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (choice) {

                case 1:
                    // Send message
                    System.out.print("Enter recipient name: ");
                    String recipient = scanner.nextLine();
                    System.out.print("Enter message content: ");
                    String content = scanner.nextLine();
                    String timestamp = LocalDateTime.now().format(formatter);
                    appManager.addMessage(messageIdCounter++, sender, recipient, content, timestamp);
                    System.out.println("Message sent successfully!");
                    break;

                case 2:
                    // View all messages
                    appManager.displayMessages();
                    break;

                case 3:
                    // View unseen messages
                    appManager.displayUnseenMessages();
                    break;

                case 4:
                    // Search messages
                    System.out.print("Enter recipient name to search: ");
                    String recipientName = scanner.nextLine();
                    appManager.searchMessagesByRecipient(recipientName);
                    break;

                case 5:
                    // Mark message as seen
                    System.out.print("Enter message ID to mark as seen: ");
                    int messageId = scanner.nextInt();
                    boolean markedAsSeen = appManager.markMessageAsSeen(messageId);
                    if (markedAsSeen) {
                        System.out.println("Message marked as seen.");
                    } else {
                        System.out.println("Message not found.");
                    }
                    break;

                case 6:
                    // Delete message
                    System.out.print("Enter message ID to delete: ");
                    int deleteId = scanner.nextInt();
                    boolean isDeleted = appManager.deleteMessage(deleteId);
                    if (isDeleted) {
                        System.out.println("Message deleted successfully.");
                    } else {
                        System.out.println("Message not found.");
                    }
                    break;

                case 7:
                    // Update message by ID
                    System.out.print("Enter message ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new message content: ");
                    String newContent = scanner.nextLine();
                    boolean isUpdated = appManager.updateMessageById(updateId, newContent);
                    if (isUpdated) {
                        System.out.println("Message updated successfully.");
                    } else {
                        System.out.println("Message not found.");
                    }
                    break;

                case 8:
                // Add contact
                System.out.print("Enter contact name: ");
                String name = scanner.nextLine();
                System.out.print("Enter contact phone number: ");
                String phoneNumber = scanner.nextLine();
                Contact contact = new Contact(contactIdCounter++, name, phoneNumber);
                appManager.addContact(contact);
                System.out.println("Contact added successfully!");
                break;


                case 9:
                    // View all contacts
                    appManager.displayContacts();
                    break;

                case 10:
                    // Exit the application
                    System.out.println("Exiting application...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
