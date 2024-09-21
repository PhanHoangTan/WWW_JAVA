package iuh.frontend.Model;

import iuh.backend.enums.EmployeeStatus;
import iuh.backend.models.Employee;
import iuh.backend.services.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeModel {

    private final EmployeeService employeeService = new EmployeeService();

    public void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String name = request.getParameter("fullName");
        String s_dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String status = request.getParameter("status");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse(s_dob, formatter);


        Employee employee = new Employee(name, dob, email, phone, address, EmployeeStatus.valueOf(status));

        EmployeeService service = new EmployeeService();
        service.insertEmployee(employee);
        response.sendRedirect("Employees.jsp");

    }

    public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        boolean deleted = employeeService.deleteEmployee(id);

        if (deleted) {
            response.sendRedirect("Employees.jsp"); // Chuyển hướng nếu xóa thành công
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Employee not found"); // Thông báo nếu không tìm thấy
        }
    }




    public void activeEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        employeeService.updateStatusE(id, EmployeeStatus.ACTIVE);
        response.sendRedirect("Employees.jsp");
    }

    public void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get parameters from the request
            long id = Long.parseLong(request.getParameter("id")); // Assuming you have an 'id' parameter
            String fullName = request.getParameter("fullName");
            String dob = request.getParameter("dob"); // You may want to parse this to a LocalDate object
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String status = request.getParameter("status"); // Assuming this is a string, handle accordingly

            // Create an Employee object
            Employee employee = new Employee();
            employee.setId(id);
            employee.setFullName(fullName);
            employee.setDob(LocalDate.parse(dob)); // Parse to LocalDate
            employee.setEmail(email);
            employee.setPhone(phone);
            employee.setAddress(address);
            employee.setStatus(EmployeeStatus.valueOf(status)); // Assuming status is an enum

            // Call the service to update the employee
            EmployeeService employeeService = new EmployeeService();
            boolean isUpdated = employeeService.updateEmployee(employee);

            // Prepare response based on the update result
            if (isUpdated) {
                response.sendRedirect("Employees.jsp"); // Redirect on success
            } else {
                // Set an error message and forward to the same page or an error page
                request.setAttribute("errorMessage", "Update failed. Please try again.");
                request.getRequestDispatcher("Employees.jsp").forward(request, response); // Forward to Employees.jsp
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately
            request.setAttribute("errorMessage", "An error occurred while updating the employee.");
            request.getRequestDispatcher("Employees.jsp").forward(request, response); // Forward to Employees.jsp
        }
    }


}
