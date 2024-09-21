package iuh.frontend.Model;

import iuh.backend.models.Customer;
import iuh.backend.services.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CustomerModel {

    private final CustomerService customerService = new CustomerService();

    public void insertCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("custName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        // Kiểm tra thông tin đầu vào
        if (name == null || email == null || name.trim().isEmpty() || email.trim().isEmpty()) {
            response.sendRedirect("insertCustomer.jsp?message=Please fill in all required fields.");
            return;
        }

        // Tạo đối tượng Customer
        Customer customer = new Customer();
        customer.setCustName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);

        // Sử dụng CustomerService để chèn khách hàng
        CustomerService service = new CustomerService();
        try {
            service.insertCustomer(customer);
            response.sendRedirect("Customer.jsp?message=Customer added successfully");
        } catch (Exception e) {
            response.sendRedirect("insertCustomer.jsp?message=Failed to add customer");
        }
    }



    public void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        boolean deleted = customerService.deleteCustomer(id); // Ensure deleteCustomer method is implemented

        if (deleted) {
            response.sendRedirect("Customer.jsp"); // Redirect if deletion is successful
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer not found"); // Notify if not found
        }
    }

    public void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("custName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        Customer customer = new Customer();
        customer.setId(id);
        customer.setCustName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);

        try {
            customerService.updateCustomer(customer);
            response.sendRedirect("Customer.jsp?message=Customer updated successfully");
        } catch (Exception e) {
            response.sendRedirect("updateCustomer.jsp?id=" + id + "&message=Failed to update customer");
        }
    }



}
