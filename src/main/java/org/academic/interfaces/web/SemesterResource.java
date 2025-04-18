package org.academic.interfaces.web;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.academic.application.dto.semester.SemesterResponse;
import org.academic.application.service.SemesterService;

import java.util.List;

@Path("/api/v1/semesters")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SemesterResource {

    private SemesterService semesterService;

    public SemesterResource(SemesterService semesterService){
        this.semesterService = semesterService;
    }

    @GET
    @RolesAllowed({"admin", "coordinator"})
    public List<SemesterResponse> get() {
        return semesterService.getAll();
    }

    @POST
    @RolesAllowed({"admin", "coordinator"})
    public Response post(SemesterResponse semester) {
        semesterService.save(semester);
        return Response.status(Response.Status.CREATED).entity(semester).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"admin", "coordinator"})
    public Response update(@PathParam("id") Long id, SemesterResponse semester) {

        SemesterResponse updated = semesterService.update(id, semester);

        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin", "coordinator"})
    public Response delete(@PathParam("id") Long id) {
        Boolean delete = semesterService.delete(id);
        if (!delete) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
