package org.academic.interfaces.web;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.academic.application.dto.SemesterCourseDisciplineDTO;
import org.academic.application.service.SemesterCourseDisciplineService;

import java.util.List;


@Path("/curriculum-matrix")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class semesterCourseDisciplineResource {

    private SemesterCourseDisciplineService semesterCourseDisciplineService;

    public semesterCourseDisciplineResource(SemesterCourseDisciplineService semesterCourseDisciplineService){
        this.semesterCourseDisciplineService = semesterCourseDisciplineService;
    }

    @POST
    @RolesAllowed({"admin", "coordinator"})
    public Response associateDiscipline(SemesterCourseDisciplineDTO semesterCourseDisciplineDTO) {

        SemesterCourseDisciplineDTO semesterCourseDiscipline = semesterCourseDisciplineService.associateDiscipline(semesterCourseDisciplineDTO);

        if(semesterCourseDiscipline == null) return Response.status(Response.Status.BAD_REQUEST)
                .entity("Course, Semester, or Discipline not found.")
                .build();

        return Response.status(Response.Status.CREATED).entity(semesterCourseDiscipline).build();
    }

    @GET
    @Path("/{courseId}")
    @RolesAllowed({"admin", "teacher", "studant"})
    public Response viewCurriculumMatrix(@PathParam("courseId") Long courseId) {

        List<SemesterCourseDisciplineDTO> course = semesterCourseDisciplineService.viewCurriculumMatrix(courseId);

        if (course == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(course).build();
    }
}
