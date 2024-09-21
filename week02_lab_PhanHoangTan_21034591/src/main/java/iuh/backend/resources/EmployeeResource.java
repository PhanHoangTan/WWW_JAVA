package iuh.backend.resources;

import iuh.backend.models.Employee;
import iuh.backend.services.EmployeeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/employees")
public class EmployeeResource {
    private final EmployeeService employeeService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Inject
    public EmployeeResource() {
        employeeService = new EmployeeService();
    }
    @GET
    @Produces("application/json")
    public Response getAll() {
           Response.ResponseBuilder response = Response.ok();
              List<Employee> employees = employeeService.getAll();
                response.entity(employees);
                return response.build();
    }

    @POST
    @Consumes("application/json")
    public Response insertEmployee(Employee employee) {
        Response.ResponseBuilder response = Response.status(Response.Status.BAD_REQUEST);
        try {
            employeeService.insertEmployee(employee);
            response = Response.status(Response.Status.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateEmployee(@PathParam("id") long id, Employee employee) {
        Response.ResponseBuilder response = Response.status(Response.Status.BAD_REQUEST);

        try {
            // Đảm bảo ID từ URL và đối tượng nhân viên là giống nhau
            employee.setId(id);

            // Cập nhật thông tin nhân viên
            if (employeeService.updateEmployee(employee)) {
                // Nếu cập nhật thành công, trả về 200 OK cùng với đối tượng nhân viên đã cập nhật
                response = Response.ok(employee);
            } else {
                // Nếu không tìm thấy nhân viên, trả về 404 Not Found
                response = Response.status(Response.Status.NOT_FOUND)
                        .entity("Employee not found with ID: " + id);
            }
        } catch (Exception e) {
            // Xử lý lỗi và trả về mã lỗi nội bộ
            logger.error("Error updating employee: {}", e.getMessage(), e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while updating the employee.");
        }

        return response.build();
    }


    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public Response deleteEmployee(@PathParam("id") long id) {
        Response.ResponseBuilder response = Response.status(Response.Status.BAD_REQUEST);
        try {
            if (employeeService.deleteEmployee(id)) {
                response = Response.status(Response.Status.OK); // Xóa thành công
            } else {
                response = Response.status(Response.Status.NOT_FOUND); // Không tìm thấy nhân viên
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR); // Lỗi nội bộ
        }
        return response.build();
    }



    @PUT
    @Path("/{id}/active")
    public Response updateStatus(@PathParam("id") long id) {
        Response.ResponseBuilder response = Response.status(Response.Status.BAD_REQUEST);
        try {
            if (employeeService.updateStatus(id)) {
                response = Response.status(Response.Status.OK);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return response.build();
    }

    @PUT
    @Path("/{id}/rest")
    public Response restEmployee(@PathParam("id") long id) {
        Response.ResponseBuilder response = Response.status(Response.Status.BAD_REQUEST);
        try {
            if (employeeService.restEmployee(id)) {
                response = Response.status(Response.Status.OK);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return response.build();
    }



}
