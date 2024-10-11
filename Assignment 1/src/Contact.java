

public record Contact(int ID, String name, String phoneNumber) {

    @Override
    public String toString() {
        return "Contact ID: " + ID + ", Name: " + name + ", Phone: " + phoneNumber;
    }
}


