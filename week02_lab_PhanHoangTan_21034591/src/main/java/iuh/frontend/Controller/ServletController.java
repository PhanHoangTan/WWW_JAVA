package iuh.frontend.Controller;


import iuh.frontend.Model.CustomerModel;
import iuh.frontend.Model.EmployeeModel;
import iuh.frontend.Model.ProductModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/controls")
public class ServletController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (action != null) {
                switch (action) {
                    case "insert_employee":
                        EmployeeModel employeeModel = new EmployeeModel();
                        employeeModel.insertEmployee(request, response);
                        response.sendRedirect("employee.jsp"); // Chuyển hướng về trang nhân viên
                        break;
                    case "insert_product":
                        ProductModel productModel = new ProductModel();
                        productModel.insertProduct(request, response);
                        response.sendRedirect("product.jsp"); // Chuyển hướng về trang sản phẩm
                        break;
                    case "insert_customer":
                        CustomerModel customerModel = new CustomerModel();
                        customerModel.insertCustomer(request, response);
                        response.sendRedirect("customer.jsp"); // Chuyển hướng về trang khách hàng
                        break;
                    case "update_employee":
                        EmployeeModel updateEmployeeModel = new EmployeeModel();
                        updateEmployeeModel.updateEmployee(request, response);
                        response.sendRedirect("employee.jsp"); // Chuyển hướng về trang nhân viên
                        break;
                    case "update_customer":
                        CustomerModel updateCustomerModel = new CustomerModel();
                        updateCustomerModel.updateCustomer(request, response);
                        response.sendRedirect("customer.jsp"); // Chuyển hướng về trang khách hàng
                        break;
                    default:
                        response.sendRedirect("index.jsp");
                        break;
                }
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            // Xử lý lỗi và có thể hiển thị thông báo
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (action != null) {
                switch (action) {
                    case "delete_product":
                        ProductModel productModel = new ProductModel();
                        productModel.deleteProduct(request, response);
                        break;
                    case "delete_employee":
                        EmployeeModel employeeModel = new EmployeeModel();
                        employeeModel.deleteEmployee(request, response);
                        break;
                    case "delete_customer": // Add case for deleting customer
                        CustomerModel customerModel = new CustomerModel();
                        customerModel.deleteCustomer(request, response);
                        break;
                    case "active_employee":
                        EmployeeModel employeeModel2 = new EmployeeModel();
                        employeeModel2.activeEmployee(request, response);
                        break;
                    case "active_product":
                        ProductModel productModel2 = new ProductModel();
                        productModel2.activeProduct(request, response);
                        break;
                    default:
                        response.sendRedirect("index.jsp");
                        break;
                }
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}




