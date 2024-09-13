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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;




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

        if (action != null) {
            switch (action.toLowerCase()) {
                case "login":
                    handleLogin(request, response);
                    break;
                case "adduser":
                    handleAddUser(request, response);
                    break;
                case "updateuser":
                    handleEditUser(request, response);
                    break;
                case "assignrole":
                    handleAssignRole(request, response);
                    break;
                default:
                    response.sendRedirect("dashboard.jsp?error=invalidAction");
                    break;
            }
        } else {
            response.sendRedirect("dashboard.jsp?error=noAction");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action.toLowerCase()) {
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
        } else {
            response.sendRedirect("dashboard.jsp?error=noAction");
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
            String roleId = accountRepository.getRoleIdbyAccountId(username);
            request.getSession().setAttribute("user", username);

            if ("admin".equals(roleId)) {
                List<Account> accounts = accountRepository.getAllAccounts();
                request.setAttribute("accounts", accounts);
                RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
                dispatcher.forward(request, response);
            } else {
                Account userAccount = accountRepository.getAccountById(username);
                request.setAttribute("account", userAccount);
                RequestDispatcher dispatcher = request.getRequestDispatcher("userDashboard.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("index.jsp?error=invalid");
        }
    }

    private void handleAssignRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accountId = request.getParameter("accountId");
        String[] roleIds = request.getParameterValues("roleIds");

        if (accountId == null || roleIds == null || roleIds.length == 0) {
            response.sendRedirect("dashboard.jsp?error=missing");
            return;
        }

        try {
            boolean success = grantAccessRepository.assignRolesToUser(accountId, Arrays.asList(roleIds));
            response.sendRedirect(success ? "dashboard.jsp?message=roleAssigned" : "dashboard.jsp?error=failed");
        } catch (Exception e) {

            response.sendRedirect("dashboard.jsp?error=exception");
        }
    }




    private void handleAddUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String statusStr = request.getParameter("status");
        Byte status = statusStr != null ? Byte.valueOf(statusStr) : null;

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

    private void handleEditUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String statusStr = request.getParameter("status");
        Byte status = statusStr != null ? Byte.valueOf(statusStr) : null;

        String[] roleIds = request.getParameterValues("roles");

        if (id == null || username == null || email == null || password == null || phone == null || status == null) {
            response.sendRedirect("editUser.jsp?error=missing");
            return;
        }

        try {
            // Update the account details
            Account account = new Account(id, username, password, email, phone, status);
            accountRepository.updateAccount(account);

            // Update roles
            List<String> roleIdsList = roleIds != null ? Arrays.asList(roleIds) : Collections.emptyList();
            grantAccessRepository.updateRoles(id, roleIdsList);

            response.sendRedirect("dashboard.jsp?message=userUpdated");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error updating user: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("editUser.jsp");
            dispatcher.forward(request, response);
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
            response.sendRedirect(success ? "dashboard.jsp?message=deleted" : "dashboard.jsp?error=failed");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("dashboard.jsp?error=exception");
        }
    }
}
