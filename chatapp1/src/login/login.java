package login;

import java.util.Scanner;

public class login {

    // Fields: where we store the user's info
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final String cellPhoneNumber;

    // Constructor: packs the data into the objec
    public login(String firstName, String lastName, String username, String password, String cellPhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    
    
    //////////////////////////////////   6 - METHODS /////////////////////
    /// @return /
    
    // 1. checkUserName()
    // Returns true if username has an underscore_ and is 5 characters or less.
    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    
    
    
    // 2. checkPasswordComplexity()
    // Returns true if password is at least 8 chars, has a capital letter,
    // a number, and a special character.
    public boolean checkPasswordComplexity() {
        if (password.length() < 8) return false;
        boolean hasUpper = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    
    
    
    // 3. checkCellPhoneNumber()
    // Returns true if cell number starts with +27 and then exactly 9 digits.
    public boolean checkCellPhoneNumber() {
        // Regex from Stack Overflow (for SA +27)
        return cellPhoneNumber.matches("^\\+27[0-9]{9}$");
    }

    
    
    
    // 4. registerUser()
    // Calls the three checks above. If any fail, returns the exact error message.
    // If all pass, returns "Registration successful."
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your " +
                   "username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains " +
                   "at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        return "Registration successful.";
    }

    
    
    
    // 5. loginUser()
    // Compares entered username/password with the stored ones.
    // Returns true if both match, false otherwise.
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }

    
    
    
    // 6. returnLoginStatus()
    // Calls loginUser(). If success, returns welcome message with first and last name.
    // If fail, returns error message.
    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (loginUser(enteredUsername, enteredPassword)) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    
    
    
    ////////////////////////// Main method – runs the program //////////////
    /// @param args/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Banner
        System.out.println("#===========================================#");
        System.out.println("#           QUICKCHAT --- REGISTER MENU         #");
        System.out.println("#===========================================#");
        System.out.println();

        // Menu with exception handling - so we dont crash
        int choice = 0;
        boolean valid = false;
        while (!valid) {
            System.out.println("Please select an option:");
            System.out.println("  1) Register");
            System.out.println("  2) Login");
            System.out.print("Enter your choice (1 or 2): ");
            try {
                choice = scanner.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("Oops! Please enter a number (1 or 2).");
                scanner.nextLine();
            }
        }
        scanner.nextLine(); // clear Enter : after user presees (1)

        if (choice == 1) {
            System.out.println("\n-- Registration --"); // prints out and uses scaner to take input
            System.out.print("First name: ");
            String first = scanner.nextLine();
            System.out.print("Last name: ");
            String last = scanner.nextLine();
            System.out.print("Username: ");
            String uname = scanner.nextLine();
            System.out.print("Password: ");
            String pass = scanner.nextLine();
            System.out.print("Cell phone number: ");
            String cell = scanner.nextLine();

            login user = new login(first, last, uname, pass, cell);
            String result = user.registerUser(); 
            System.out.println(result);

            if (result.equals("Registration successful.")) {
                System.out.println("\n--- LOGIN ---");
                System.out.print("Enter your username: ");
                String loginUname = scanner.nextLine();
                System.out.print("Enter your password: ");
                String loginPass = scanner.nextLine();
                System.out.println(user.returnLoginStatus(loginUname, loginPass));
            }
        } else if (choice == 2) {
            System.out.println("Please register first (option 1).");
        } else {
            System.out.println("Invalid option. Please enter 1 or 2."); // so it dose not crash
        }

        scanner.close();
    }
    
    
    
}