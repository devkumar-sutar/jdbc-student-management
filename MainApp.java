package src;
import java.sql.*;
import java.util.Scanner;

public class MainApp {

    static final String URL = "jdbc:mysql://localhost:3306/std";
    static final String USER = "root";
    static final String PASS = "deva@21102004";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent(sc);
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    updateStudent(sc);
                    break;

                case 4:
                    deleteStudent(sc);
                    break;

                case 5:
                    System.out.println("Exited...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // 🔹 Add Student
    public static void addStudent(Scanner sc) {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Marks: ");
            int marks = sc.nextInt();

            String query = "INSERT INTO students VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, marks);

            ps.executeUpdate();

            System.out.println("Student Added!");

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 🔹 View Students
    public static void viewStudents() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            System.out.println("\nID  Name  Marks");

            while (rs.next()) {
                System.out.println(
                        rs.getInt(1) + "   " +
                        rs.getString(2) + "   " +
                        rs.getInt(3)
                );
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 🔹 Update Student
    public static void updateStudent(Scanner sc) {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            System.out.print("Enter ID to update: ");
            int id = sc.nextInt();

            System.out.print("Enter new marks: ");
            int marks = sc.nextInt();

            String query = "UPDATE students SET marks=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, marks);
            ps.setInt(2, id);

            ps.executeUpdate();

            System.out.println("Updated!");

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 🔹 Delete Student
    public static void deleteStudent(Scanner sc) {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            System.out.print("Enter ID to delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Deleted!");

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}