package com.work.resource;

import com.work.business.BaseBusiness;
import com.work.model.BaseEntity;
import com.work.repository.BaseRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;

public class BaseResource<Entity extends BaseEntity<Long>, Repostory extends BaseRepository<Entity>, Busines extends BaseBusiness<Entity, Repostory>> {

    @Inject
    private Busines busines;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("hello")
    public String hello() {
        return "Hello RESTEasy";
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(busines.findById(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() throws NoContentException {
        return Response.ok(busines.listAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Entity pessoa) {
        return Response.ok(Response.Status.CREATED)
            .entity(busines.save(pessoa))
            .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Entity pessoa) {
        return Response.ok(busines.update(pessoa)).build();
    }

    @PUT
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
        busines.delete(id);
        return Response.noContent().build();
    }
}
