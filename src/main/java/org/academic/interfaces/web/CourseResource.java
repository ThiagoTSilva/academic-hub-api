package org.academic.interfaces.web;


import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.academic.application.dto.CourseDTO;
import org.academic.application.service.CourseService;

import java.util.List;

@Path("/v1/courses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseResource {

    private CourseService courseService;

    public CourseResource(CourseService courseService){
        this.courseService = courseService;
    }

    @POST
    @RolesAllowed({"admin", "coordinator"})
    public Response post(CourseDTO curso) {
        CourseDTO created = courseService.create(curso);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @RolesAllowed({"admin", "coordinator"})
    public List<CourseDTO> get() {
        return courseService.getAllCursos();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"admin", "coordinator"})
    public Response getCursoById(@PathParam("id") Long id) {
        CourseDTO curso = courseService.getCursoById(id);
        if (curso == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(curso).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"admin", "coordinator"})
    public Response updateCurso(@PathParam("id") Long id, CourseDTO curso) {
        CourseDTO updated = courseService.update(id, curso);
        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin", "coordinator"})
    public Response deleteCurso(@PathParam("id") Long id) {
        boolean deleted = courseService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
