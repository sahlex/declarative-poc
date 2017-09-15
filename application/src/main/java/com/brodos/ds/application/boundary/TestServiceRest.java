/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brodos.ds.application.boundary;

import com.brodos.ds.domain.entity.Article;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author saalexander
 */
@Path("/test")
public interface TestServiceRest {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticle(@PathParam(value = "id")
            @Pattern(regexp = "[0-9]*", message = "Only numeric ids allowed") String id);

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewArticle(@Valid Article article, @Context UriInfo uri);

}
