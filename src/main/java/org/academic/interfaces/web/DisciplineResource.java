package org.academic.interfaces.web;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.academic.application.dto.DisciplineDTO;
import org.academic.application.service.DisciplineService;

import java.util.List;

@Path("/v1/disciplines")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DisciplineResource {

    private DisciplineService disciplineService;

    public DisciplineResource(DisciplineService disciplineService){
        disciplineService = disciplineService;
    }

    @POST
    @RolesAllowed({"admin", "coordinator"})
    public Response post(DisciplineDTO disciplineDTO) {
        DisciplineDTO created = disciplineService.save(disciplineDTO);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @RolesAllowed({"admin", "coordinator"})
    public List<DisciplineDTO> get() {
        return disciplineService.getAll();
    }


    @PUT
    @Path("/{id}")
    @RolesAllowed({"admin", "coordinator"})
    public Response put(@PathParam("id") Long id, DisciplineDTO disciplineDTO) {
        DisciplineDTO updated = disciplineService.update(id, disciplineDTO);

        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin", "coordinator"})
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = disciplineService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
