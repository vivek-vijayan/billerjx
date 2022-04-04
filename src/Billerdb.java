import java.sql.*;

public class Billerdb {
    // Public to be accessed by UIClass
    private static Connection conn = null;
    public static Connection getConnection(){
        return conn;
    }
    Billerdb(String username, String password){
        // Trying to connect with Postgres
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/javadb",
                    username,
                    password
            );
            if(conn!=null)
            {
                System.out.println("Connection established");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean billerJX_Authenticator(String username, String password){
        return conn != null;
    }

    static ResultSet billerJX_executeQuery(String query) throws SQLException {
        Statement st = conn.createStatement();
        return st.executeQuery(query);
    }

    static boolean billerJX_addTransactionID(String uuid) {
        try {
            Statement st = conn.createStatement();
            String query = "INSERT INTO BILLERJX_TRANSACTION (TRANSACTION_ID) VALUES ('" + uuid + "');";
            st.executeUpdate(query);
            st.close();
            return true;
        }
        catch (SQLException e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            return false;
        }
    }
    public static void main(String[] args){

    }
}
