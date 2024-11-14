package vn.edu.iuh.fit.week3.backend.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.junit.platform.commons.logging.LoggerFactory;
import vn.edu.iuh.fit.week3.backend.entities.Product;
import vn.edu.iuh.fit.week3.backend.services.ProductServices;

import java.util.List;
import java.util.logging.Logger;

@Path("/products")
public class ProductResources {
    private final ProductServices productServices;
    private final Logger LOGGER = (Logger) LoggerFactory.getLogger(Class.forName(this.getClass().getName()));
    @Inject
    public ProductResources() throws ClassNotFoundException {
        productServices = new ProductServices();
    }
    @GET
    @Produces("application/json")
    public Response getAll() {
        List<Product> products = productServices.getAll();
        return Response.ok(products).build();
    }


}
