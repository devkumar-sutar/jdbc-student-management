package src;
import java.sql.*;
public class DBconnection {
    public static void main(String[] args) {

        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/std",
                "root",
                "deva@21102004"
            );

            System.out.println("Connected!");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " +
                                   rs.getString(2) + " " +
                                   rs.getInt(3));
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}