package vn.edu.iuh.fit.week1.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import vn.edu.iuh.fit.week1.entities.Account;
import vn.edu.iuh.fit.week1.repositories.AccountRepository;
import vn.edu.iuh.fit.week1.repositories.LogRepository;
import vn.edu.iuh.fit.week1.repositories.RoleRepository;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/ControllerServlet", "/control"})
public class ControllerServlet extends HttpServlet {

    private final LogRepository logRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;

    // Constructor or initialization block
    public ControllerServlet() {
        this.logRepository = new LogRepository();  // Adjust as needed
        this.roleRepository = new RoleRepository();  // Adjust as needed
        this.accountRepository = new AccountRepository(roleRepository);  // Ensure proper initialization
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action != null ? action.toLowerCase() : "") {
            case "login":
                handleLogin(request, response);
                break;
            case "register":
                handleRegister(request, response);
                break;
            default:
                response.getWriter().println("Invalid action");
                break;
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null) {
            response.sendRedirect("index.jsp?error=missing");
            return;
        }

        if ("admin".equals(username) && "admin".equals(password)) {
            // Admin login
            request.getSession().setAttribute("user", username);

            List<Account> accounts = accountRepository.getAllAccounts();
            request.setAttribute("accounts", accounts);

            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
            dispatcher.forward(request, response);
        } else if (accountRepository.validateLogin(username, password)) {
            // User login
            request.getSession().setAttribute("user", username);

            // Fetch the logged-in user's account details
            Account userAccount = accountRepository.getAccountById(username);
            request.setAttribute("account", userAccount);

            RequestDispatcher dispatcher = request.getRequestDispatcher("userDashboard.jsp");
            dispatcher.forward(request, response);
        } else {
            // Invalid login
            response.sendRedirect("index.jsp?error=invalid");
        }
    }


    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accountId = request.getParameter("accountId");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String statusParam = request.getParameter("status");

        Byte status = null;
        if (statusParam != null && !statusParam.isEmpty()) {
            try {
                status = Byte.parseByte(statusParam);
            } catch (NumberFormatException e) {
                response.sendRedirect("register.jsp?error=invalidstatus");
                return;
            }
        }

        // Validate input
        if (accountId != null && !accountId.isEmpty() &&
                fullName != null && !fullName.isEmpty() &&
                password != null && !password.isEmpty() &&
                email != null && !email.isEmpty() &&
                phone != null && !phone.isEmpty() &&
                status != null) {

            // Hash password
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            // Create Account object
            Account account = new Account();
            account.setAccountId(accountId);
            account.setFullName(fullName);
            account.setPassword(hashedPassword);
            account.setEmail(email);
            account.setPhone(phone);
            account.setStatus(status);

            // Insert into database
            boolean success = accountRepository.insert(account);

            if (success) {
                response.sendRedirect("index.jsp?success=true"); // Redirect to login page
            } else {
                response.sendRedirect("register.jsp?error=true");
            }
        } else {
            response.sendRedirect("register.jsp?error=missing");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("someAction".equalsIgnoreCase(action)) {
            // Handle specific GET action
        } else {
            response.getWriter().println("Invalid action");
        }
    }
}
