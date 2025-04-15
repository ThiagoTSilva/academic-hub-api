package org.academic.interfaces.web;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.academic.application.dto.SemesterDTO;
import org.academic.application.service.SemesterService;
import org.academic.domain.Semester;

import java.util.List;

@Path("/v1/semestres")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SemesterResource {

    private SemesterService semesterService;

    public SemesterResource(SemesterService semesterService){
        this.semesterService = semesterService;
    }

    @GET
    @RolesAllowed({"admin", "coordinator"})
    public List<SemesterDTO> get() {
        return semesterService.getAll();
    }

    @POST
    @RolesAllowed({"admin", "coordinator"})
    public Response post(SemesterDTO semester) {
        semesterService.save(semester);
        return Response.status(Response.Status.CREATED).entity(semester).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"admin", "coordinator"})
    public Response update(@PathParam("id") Long id, SemesterDTO semester) {

        SemesterDTO existingSemestre = semesterService.update(id, semester);

        if (existingSemestre == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(existingSemestre).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin", "coordinator"})
    public Response delete(@PathParam("id") Long id) {
        Boolean semestre = semesterService.delete(id);
        if (!semestre) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
