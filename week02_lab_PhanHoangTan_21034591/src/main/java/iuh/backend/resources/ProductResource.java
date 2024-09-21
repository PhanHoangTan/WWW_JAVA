package iuh.backend.resources;

import iuh.backend.models.Product;
import iuh.backend.services.ProductService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/products")
public class ProductResource {
    private final ProductService productService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Inject
    public ProductResource() {
        productService = new ProductService();
    }

    @GET
    @Produces("application/json")
    public Response getAll() {
        List<Product> products = productService.getAll();
        return Response.ok(products).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response insertProduct(Product product) {
        if (product == null || product.getName() == null || product.getStatus() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Product name and status are required.")
                    .build();
        }

        try {
            productService.insertProduct(product);
            return Response.status(Response.Status.CREATED).entity("Product created successfully.").build();
        } catch (Exception e) {
            logger.error("Error inserting product: " + e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error inserting product: " + e.getMessage())
                    .build();
        }
    }


    @PUT
    @Path("/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response updateProduct(@PathParam("id") long id, Product product) {
        Response.ResponseBuilder response = Response.status(Response.Status.BAD_REQUEST);
        try {
            product.setId(id);
            if (productService.updateProduct(product)) {
                response = Response.status(Response.Status.OK);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response deleteProduct(@PathParam("id") long id) {
        Response.ResponseBuilder response = Response.status(Response.Status.BAD_REQUEST);
        try {
            if (productService.deleteProduct(id)) {
                response = Response.status(Response.Status.OK);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findById(@PathParam("id") long id) {
        Response.ResponseBuilder response = Response.status(Response.Status.BAD_REQUEST);
        try {
            response = Response.ok(productService.findById(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return response.build();
    }

    @PUT
    @Path("/{id}/active")
    @Produces("application/json")
    public Response activeProduct(@PathParam("id") long id) {
        Response.ResponseBuilder response = Response.status(Response.Status.BAD_REQUEST);
        try {
            if (productService.activeProduct(id)) {
                response = Response.status(Response.Status.OK);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return response.build();
    }

    @PUT
    @Path("/{id}/rest")
    @Produces("application/json")
    public Response restProduct(@PathParam("id") long id) {
        Response.ResponseBuilder response = Response.status(Response.Status.BAD_REQUEST);
        try {
            if (productService.restProduct(id)) {
                response = Response.status(Response.Status.OK);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return response.build();
    }
}
