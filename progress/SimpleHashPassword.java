package progress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SimpleHashPassword {
    
    // Hashing method (same as before)
    public static String hashPassword(String password) {
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            // Perform hashing on the password (convert to bytes and hash)
            byte[] hashBytes = digest.digest(password.getBytes());
            
            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b)); // Convert byte to hex
            }
            
            // Return the hashed password
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Method to verify if the password matches the stored hash
    public static boolean verifyPassword(String storedHashedPassword,String inputPassword) {
        // Hash the input password
        String hashedInputPassword = hashPassword(inputPassword);
        
        // Compare the hashed input with the stored hashed password
        return hashedInputPassword.equals(storedHashedPassword);
    }

    public static void main(String[] args) {
        // Example: Storing a password (the hashed version)
        String originalPassword = "2";  // Password to be hashed
        String storedHashedPassword = hashPassword(originalPassword);  // Hash the password and store it
        
        // Display the hashed password (you'd store this in a database)
        System.out.println("Stored Hashed Password: " + storedHashedPassword);

        // Example: Verifying the password (user enters their password)
        String enteredPassword = "1";  // Simulating user input for verification
        boolean isVerified = verifyPassword(storedHashedPassword,enteredPassword);

        // Print the result of the verification
        if (isVerified) {
            System.out.println("Password verified successfully!");
        } else {
            System.out.println("Incorrect password.");
        }
    }
}
