package org.academic.interfaces.web;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.academic.application.dto.course.CourseResponse;
import org.academic.application.dto.user.UserResponse;
import org.academic.application.service.UserService;

import java.util.List;

@Path("/api/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @RolesAllowed("admin")
    public List<UserResponse> get() {
        return userService.listAll();
    }

    @POST
    @RolesAllowed("admin")
    public Response post(UserResponse user) {
        userService.create(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public void delete(@PathParam("id") String id) {
        userService.delete(id);
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response put(@PathParam("id") String id, UserResponse user) {
        userService.update(id, user);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"admin"})
    public Response getCourseById(@PathParam("id") String id) {
        List<UserResponse> user = userService.getUserById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

}
