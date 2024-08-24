package vn.edu.iuh.fit.session1.ex1.Bean;

import vn.edu.iuh.fit.session1.ex1.ConnectDB.connectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginBean {

    /**
     * Kiểm tra thông tin đăng nhập với cơ sở dữ liệu.
     * @param username Tên người dùng cần kiểm tra.
     * @param password Mật khẩu cần kiểm tra.
     * @return true nếu thông tin đăng nhập hợp lệ, ngược lại false.
     */
    public boolean login(String username, String password) {
        boolean isValid = false;

        // Truy vấn SQL để kiểm tra username và password
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try {
            // Lấy đối tượng kết nối từ ConnectDB
            Connection conn = connectDB.getConnection("sa", "12345678");

            // Tạo PreparedStatement và thiết lập giá trị cho các tham số
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Thực thi truy vấn và lấy kết quả
            ResultSet rs = stmt.executeQuery();

            // Kiểm tra kết quả
            if (rs.next()) {
                isValid = true;  // Đăng nhập thành công nếu tìm thấy bản ghi phù hợp
            }

            // Đóng các tài nguyên sau khi sử dụng
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error during login: " + e.getMessage());
        }

        return isValid;
    }
}
