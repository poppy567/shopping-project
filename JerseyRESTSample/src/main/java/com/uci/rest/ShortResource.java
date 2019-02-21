package com.uci.rest;

/**
 * Created by tariqibrahim on 5/28/17.
 */
import com.uci.rest.model.Short;
import com.uci.rest.service.ShortService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * This class contains the methods that will respond to the various endpoints that you define for your RESTful API Service.
 *
 */
//shorts will be the pathsegment that precedes any path segment specified by @Path on a method.
@Path("/shorts")
public class ShortResource {


    //This method represents an endpoint with the URL /shorts/{id} and a GET request ( Note that {id} is a placeholder for a path parameter)
    @Path("{id}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getShortById(@PathParam("id") int id/* The {id} placeholder parameter is resolved */) {
        //invokes the DB method which will fetch a short_list item object by id
        Short st = ShortService.getShortById(id);

        //Respond with a 404 if there is no such short_list item for the id provided
        if(st == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a short_list_item object to return as response
        return Response.ok(st).build();
    }

    //This method represents an endpoint with the URL /shorts and a GET request.
    // Since there is no @PathParam mentioned, the /shorts as a relative path and a GET request will invoke this method.
    @GET
    @Produces( { MediaType.APPLICATION_JSON })
    public Response getAllShorts() {
        List<Short> shortList = ShortService.getAllShorts();

        if(shortList == null || shortList.isEmpty()) {

        }

        return Response.ok(shortList).build();
    }

    //This method represents an endpoint with the URL /shorts and a POST request.
    // Since there is no @PathParam mentioned, the /shorts as a relative path and a POST request will invoke this method.
    @POST
    @Consumes({MediaType.APPLICATION_JSON}) //This method accepts a request of the JSON type
    public Response addShort(Short st) {

        //The short object here is automatically constructed from the json request. Jersey is so cool!
        if(ShortService.AddShort(st)) {
            return Response.ok().entity("SHORT Added Successfully").build();
        }

        // Return an Internal Server error because something wrong happened. This should never be executed
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("SHORT Added fail").build();


    }

    //Similar to the method above.
    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED}) //This method accepts form parameters.
    //If you were to send a POST through a form submit, this method would be called.
    public Response addShort(@FormParam("id") int id,
                            @FormParam("name") String name,
                            @FormParam("price") int price,
                            @FormParam("color") String color,
                            @FormParam("description") String description,
                            @FormParam("pic1") String pic1,
                            @FormParam("pic2") String pic2,
                            @FormParam("pic3") String pic3) {
        Short st = new Short();
        st.setId(id);
        st.setName(name);
        st.setPrice(price);
        st.setColor(color);
        st.setDescription(description);
        st.setPic1(pic1);
        st.setPic2(pic2);
        st.setPic3(pic3);

        System.out.println(st);

        if(ShortService.AddShort(st)) {
            return Response.ok().entity("SHORT Added Successfully").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

    //This method represents a PUT request where the id is provided as a path parameter and the request body is provided in JSON
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateShort(@PathParam("id") int id, Short st) {

        // Retrieve the short that you will need to change
        Short retrievedShort = ShortService.getShortById(id);

        if(retrievedShort == null) {
            //If not found then respond with a 404 response.
            return Response.status(Response.Status.NOT_FOUND).
                    entity("We could not find the requested resource").build();
        }

        // This is the short_object retrieved from the json request sent.
        st.setId(id);

        // if the user has provided null, then we set the retrieved values.
        // This is done so that a null value is not passed to the short object when updating it.
        if(st.getName() == null) {
            st.setName(retrievedShort.getName());
        }

        //Same as above. We only change fields in the short_resource when the user has entered something in a request.
        if (String.valueOf(st.getPrice()) == null) {
            st.setPrice(retrievedShort.getPrice());
        }
        
        if(st.getColor() == null) {
            st.setColor(retrievedShort.getColor());
        }
        
        if(st.getDescription() == null) {
            st.setDescription(retrievedShort.getDescription());
        }

        if(st.getPic1() == null) {
            st.setPic1(retrievedShort.getPic1());
        }
        
        if(st.getPic2() == null) {
            st.setPic2(retrievedShort.getPic2());
        }
                
        if(st.getPic3() == null) {
            st.setPic3(retrievedShort.getPic3());
        }
        //This calls the JDBC method which in turn calls the the UPDATE SQL command
        if(ShortService.updateShort(st)) {

            return Response.ok().entity(st).build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

    //This method represents a DELETE request where the id is provided as a path parameter and the request body is provided in JSON
    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public Response deleteShort(@PathParam("id") int id) {

        //Retrieve the short_object that you want to delete.
        Short retrievedShort = ShortService.getShortById(id);

        if(retrievedShort == null) {
            //If not found throw a 404
            return Response.status(Response.Status.NOT_FOUND).
                    entity("We could not find the requested resource").build();
        }

        // This calls the JDBC method which in turn calls the DELETE SQL command.
        if(ShortService.deleteShort(retrievedShort)) {
            return Response.ok().entity("SHORT Deleted Successfully").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

}
