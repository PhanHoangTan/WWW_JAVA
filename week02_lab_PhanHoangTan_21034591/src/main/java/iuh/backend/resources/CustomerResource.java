package iuh.backend.resources;


import iuh.backend.models.Customer;
import iuh.backend.services.CustomerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/customers")
public class CustomerResource {
    private final CustomerService customerService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Inject
    public CustomerResource() {
        customerService = new CustomerService();
    }

    @GET
    @Produces("application/json")
    public Response getAll() {
        Response.ResponseBuilder response = Response.ok();
        response.entity(customerService.getAll());
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") long id) {
        Response.ResponseBuilder response = Response.ok();
        response.entity(customerService.findById(id));
        return response.build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response insertCustomer(Customer customer) {
        try {
            // Validate the customer object if necessary
            if (customer == null || customer.getCustName() == null || customer.getEmail() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid customer data").build();
            }

            // Insert the customer
            customerService.insertCustomer(customer);

            // Return a response indicating the customer was created successfully
            return Response.status(Response.Status.CREATED)
                    .entity("Customer created successfully").build();
        } catch (Exception e) {
            logger.error("Error inserting customer: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while creating the customer").build();
        }
    }
    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response deleteCustomer(@PathParam("id") long id) {
        try {
            boolean deleted = customerService.deleteCustomer(id);

            if (deleted) {
                return Response.ok("Customer deleted successfully").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Customer not found").build();
            }
        } catch (Exception e) {
            logger.error("Error deleting customer: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while deleting the customer").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateCustomer(@PathParam("id") long id, Customer customer) {
        try {
            // Validate the customer object if necessary
            if (customer == null || customer.getCustName() == null || customer.getEmail() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid customer data").build();
            }

            // Set the ID of the customer to update
            customer.setId(id);
            boolean updated = customerService.updateCustomer(customer);

            if (updated) {
                return Response.ok("Customer updated successfully").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Customer not found").build();
            }
        } catch (Exception e) {
            logger.error("Error updating customer: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while updating the customer").build();
        }
    }


}
