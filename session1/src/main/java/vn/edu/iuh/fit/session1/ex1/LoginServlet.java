package vn.edu.iuh.fit.session1.ex1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.session1.ex1.Bean.LoginBean;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin username và password từ request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Tạo đối tượng LoginBean để kiểm tra đăng nhập
        LoginBean loginBean = new LoginBean();
        boolean result = loginBean.login(username, password);

        // Thiết lập kiểu nội dung của phản hồi
        response.setContentType("text/html");

        // Lấy đối tượng PrintWriter để ghi nội dung phản hồi
        try (PrintWriter out = response.getWriter()) {
            if (result) {
                out.println("<h1>Welcome " + username + " to my website!</h1>");
            } else {
                out.println("<h1>Sorry! Username or password is incorrect!</h1>");
            }
        }
    }
}
