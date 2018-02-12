package nu.te4.services;

import nu.te4.beans.RecipeBean;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/")
public class RecipeService {
    @EJB
    RecipeBean bean;

    @GET
    @Path("recipes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipes() throws SQLException, ClassNotFoundException {
        return Response.ok(bean.getRecipes()).build();
    }
}
