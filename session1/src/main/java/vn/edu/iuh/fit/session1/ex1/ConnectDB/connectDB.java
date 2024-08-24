package vn.edu.iuh.fit.session1.ex1.ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDB {
    private static Connection instance;

    /**
     * Thiết lập kết nối đến cơ sở dữ liệu MySQL nếu chưa có kết nối.
     * @param user Tên người dùng cơ sở dữ liệu.
     * @param password Mật khẩu cơ sở dữ liệu.
     */
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Login;encrypt=false"; // Cơ sở dữ liệu "Login"
    private static final String DB_USERNAME = "sa";  // Tên người dùng SQL Server
    private static final String DB_PASSWORD = "12345678";  // Mật khẩu SQL Server
    public static Connection getConnection(String user, String password) {
        if (instance == null) {
            try {
                // Nạp driver cho SQL Server
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Kết nối đến cơ sở dữ liệu
                instance = DriverManager.getConnection(DB_URL, user, password);
                System.out.println("Connected to the database.");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.err.println("Error during database connection: " + e.getMessage());
            }
        }

        return instance;
    }
}
