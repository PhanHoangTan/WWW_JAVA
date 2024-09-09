package vn.edu.iuh.fit.week1.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.edu.iuh.fit.week1.entities.Account;
import vn.edu.iuh.fit.week1.repositories.AccountRepository;
import vn.edu.iuh.fit.week1.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.week1.repositories.LogRepository;
import vn.edu.iuh.fit.week1.repositories.RoleRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {

    private final LogRepository logRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final GrantAccessRepository grantAccessRepository;

    public ControllerServlet() {
        this.logRepository = new LogRepository();  // Adjust as needed
        this.roleRepository = new RoleRepository();  // Adjust as needed
        this.accountRepository = new AccountRepository();  // Ensure proper initialization
        this.grantAccessRepository = new GrantAccessRepository();  // Ensure proper initialization
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action");

        System.out.println(action ) ;

        switch (action != null ? action.toLowerCase() : "") {
            case "login":
                handleLogin(request, response);
                break;
            case "adduser":
                handleAddUser(request, response);
                break;
            case "updateuser":
                handleEditUser(request, response);
                break;
//            case "deleteuser":
//                handleDeleteUser(request, response);
//                break;
            default:
                response.getWriter().println("Invalid action");
                break;
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        System.out.println(action);

        switch (action != null ? action.toLowerCase() : "") {
            case "deleteuser":
                handleDeleteUser(request, response);
                break;
            default:
                List<Account> accounts = accountRepository.getAllAccounts(); // Use the initialized accountRepository
                request.setAttribute("accounts", accounts);
                RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
                dispatcher.forward(request, response);
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

        Optional<Account> accountOpt = accountRepository.logon(username, password);

        if (accountOpt.isPresent()) {
            // Get the roleId from GrantAccess
            String roleId = accountRepository.getRoleIdbyAccountId(username);

            // Set the user session
            request.getSession().setAttribute("user", username);

            if ("admin".equals(roleId)) {
                // Admin login
                List<Account> accounts = accountRepository.getAllAccounts();
                request.setAttribute("accounts", accounts);
                RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
                dispatcher.forward(request, response);
            } else {
                // User login
                Account userAccount = accountRepository.getAccountById(username);
                request.setAttribute("account", userAccount);
                RequestDispatcher dispatcher = request.getRequestDispatcher("userDashboard.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // Invalid login
            response.sendRedirect("index.jsp?error=invalid");
        }
    }


    private void handleAddUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        Byte status = Byte.valueOf(request.getParameter("status"));

        if (username == null || email == null || password == null || phone == null || status == null) {
            response.sendRedirect("addUser.jsp?error=missing");
            return;
        }

        Account newAccount = new Account(id, username, password, email, phone, status);
        boolean success = accountRepository.insert(newAccount);

        if (success) {
            response.sendRedirect("dashboard.jsp");
        } else {
            response.sendRedirect("addUser.jsp?error=failed");
        }
    }


    private void handleEditUser(HttpServletRequest request, HttpServletResponse response) {
        // Get user parameters from the request
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String status = request.getParameter("status");

        // Initialize AccountRepository
        AccountRepository accountRepository = new AccountRepository();

        try {
            // Validate input (e.g., check for null or empty fields)
            if (id == null || username == null || email == null || password == null || phone == null || status == null) {
                throw new IllegalArgumentException("All fields are required.");
            }

            // Create Account object
            Account account = new Account();
            account.setAccountId(id);
            account.setFullName(username);
            account.setEmail(email);
            account.setPassword(password);
            account.setPhone(phone);
            account.setStatus(Byte.valueOf(status));

            // Update account in the database
            accountRepository.updateAccount(account);

            // Redirect to dashboard with success message
            request.setAttribute("message", "User updated successfully!");
            response.sendRedirect("dashboard.jsp");

        } catch (Exception e) {
            // Handle exceptions (e.g., validation errors, database errors)
            request.setAttribute("errorMessage", "Error updating user: " + e.getMessage());
            try {
                request.getRequestDispatcher("editUser.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void handleDeleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accountId = request.getParameter("id");

        if (accountId == null || accountId.isEmpty()) {
            response.sendRedirect("dashboard.jsp?error=missing");
            return;
        }

        try {
            boolean success = accountRepository.deleteAccount(accountId);
            if (success) {
                response.sendRedirect("dashboard.jsp?message=deleted");
            } else {
                response.sendRedirect("dashboard.jsp?error=failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("dashboard.jsp?error=exception");
        }
    }

}
