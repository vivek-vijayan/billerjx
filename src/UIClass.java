import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.UUID;
import java.sql.Connection;
import java.util.Vector;


public class UIClass extends Billerdb {
    UIClass( String username, String password) {
        super(username,password);
    }
    /*
    UI Class which shows the UI Frame for the application
    Program : BillerJX
     */
    // UUID generation
    public static UUID uuid_generator() throws SQLException {
        // validating with the existing uuid
        Vector<UUID> all_uuid = new Vector<>();
        ResultSet result =  billerJX_executeQuery("SELECT * FROM BILLERJX_TRANSACTION");
        while(result.next()){
               UUID tempId = UUID.fromString(result.getString("TRANSACTION_ID"));
               all_uuid.add(tempId);
        }
        // Mechanism of getting the correct unique ID
        UUID uuid = UUID.randomUUID();
        while(all_uuid.contains(uuid)){
            uuid = UUID.randomUUID();
        }
        boolean status = billerJX_addTransactionID(uuid.toString());
        if(status)
            System.out.println("UUID updated successfully");
        else
            System.out.println("Error while updating to database");
        return uuid;
    }


    public static void main(String[] args) {
        final double VERSION = 1.0;

        // Declaration:
        JFrame billerJX_frame = new JFrame("BillerJX Application");
        JButton generateBill, connect, disconnect, update, fetch;
        JLabel ApplicationName, title, subtitle, biller_name, amount, total, billing_date;

        // Setup for Frame
        billerJX_frame.setSize(600,700);
        billerJX_frame.setResizable(false);
        billerJX_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Asking for login credentials
        String username = JOptionPane.showInputDialog(billerJX_frame,"Enter your username");
        String password = JOptionPane.showInputDialog(billerJX_frame,"Enter your password");
        UIClass uiClass = new UIClass(username, password);

        // Main frame opens
        if(billerJX_Authenticator(username, password))
        {
            billerJX_frame.setVisible(true);

        }
        else
        {
            JOptionPane.showMessageDialog(billerJX_frame, "Login failed, please reopen the application", "Login failed", JOptionPane.ERROR_MESSAGE);
            billerJX_frame.dispose();
        }
    }
}
