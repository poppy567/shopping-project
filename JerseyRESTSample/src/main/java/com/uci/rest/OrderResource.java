package com.uci.rest;

/**
 * Created by tariqibrahim on 5/28/17.
 */
import com.uci.rest.model.Order;
import com.uci.rest.service.OrderService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * This class contains the methods that will respond to the various endpoints that you define for your RESTful API Service.
 *
 */
//orders will be the pathsegment that precedes any path segment specified by @Path on a method.
@Path("/orders")
public class OrderResource {


    //This method represents an endpoint with the URL /orders/{id} and a GET request ( Note that {id} is a placeholder for a path parameter)
    @Path("{id}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getOrderById(@PathParam("id") int id/* The {id} placeholder parameter is resolved */) {
        //invokes the DB method which will fetch a order_list item object by id
        Order od = OrderService.getOrderById(id);

        //Respond with a 404 if there is no such order_list item for the id provided
        if(od == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a order_list_item object to return as response
        return Response.ok(od).build();
    }

    //This method represents an endpoint with the URL /orders and a GET request.
    // Since there is no @PathParam mentioned, the /orders as a relative path and a GET request will invoke this method.
    @GET
    @Produces( { MediaType.APPLICATION_JSON })
    public Response getAllOrders() {
        List<Order> orderList = OrderService.getAllOrders();

        if(orderList == null || orderList.isEmpty()) {

        }

        return Response.ok(orderList).build();
    }

    //This method represents an endpoint with the URL /orders and a POST request.
    // Since there is no @PathParam mentioned, the /orders as a relative path and a POST request will invoke this method.
    @POST
    @Consumes({MediaType.APPLICATION_JSON}) //This method accepts a request of the JSON type
    public Response addOrder(Order od) {

        //The order object here is automatically constructed from the json request. Jersey is so cool!
        if(OrderService.AddOrder(od)) {
            return Response.ok().entity("ORDER Added Successfully").build();
        }

        // Return an Internal Server error because something wrong happened. This should never be executed
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ORDER Added fail").build();


    }

    //Similar to the method above.
    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED}) //This method accepts form parameters.
    //If you were to send a POST through a form submit, this method would be called.
    public Response addOrder(@FormParam("id") int id,
                            @FormParam("shortId") int shortId,
                            @FormParam("size") int size,
                            @FormParam("qty") int qty,
                            @FormParam("customerId") int customerId,
                            @FormParam("address") String address,
                            @FormParam("shipMethod") String shipMethod,
                            @FormParam("city") String city,
                            @FormParam("state") String state,
                            @FormParam("zip") String zip) {
        Order od = new Order();
        od.setId(id);
        od.setShortId(shortId);
        od.setSize(size);
        od.setQty(qty);
        od.setCustomerId(customerId);
        od.setAddress(address);
        od.setShipMethod(shipMethod);
        od.setCity(city);
        od.setState(state);
        od.setZip(zip);

        System.out.println(od);

        if(OrderService.AddOrder(od)) {
            return Response.ok().entity("ORDER Added Successfully").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

    //This method represents a PUT request where the id is provided as a path parameter and the request body is provided in JSON
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateOrder(@PathParam("id") int id, Order od) {

        // Retrieve the order that you will need to change
        Order retrievedOrder = OrderService.getOrderById(id);

        if(retrievedOrder == null) {
            //If not found then respond with a 404 response.
            return Response.status(Response.Status.NOT_FOUND).
                    entity("We could not find the requested resource").build();
        }

        // This is the order_object retrieved from the json request sent.
        od.setId(id);

        // if the user has provided null, then we set the retrieved values.
        // This is done so that a null value is not passed to the order object when updating it.
        if(od.getShortId() == 0) {
            od.setShortId(retrievedOrder.getShortId());
        }

        //Same as above. We only change fields in the order_resource when the user has entered something in a request.
        if(od.getSize() == 0) {
            od.setSize(retrievedOrder.getSize());
        }
        
        if(od.getQty() == 0) {
            od.setQty(retrievedOrder.getQty());
        }
        
        if(od.getCustomerId() == 0) {
            od.setCustomerId(retrievedOrder.getCustomerId());
        }
        
        if(od.getAddress() == null) {
            od.setAddress(retrievedOrder.getAddress());
        }

        if(od.getShipMethod() == null) {
            od.setShipMethod(retrievedOrder.getShipMethod());
        }
        
        if(od.getCity() == null) {
            od.setCity(retrievedOrder.getCity());
        }
                
        if(od.getState() == null) {
            od.setState(retrievedOrder.getState());
        }
        
        if(od.getZip() == null) {
            od.setZip(retrievedOrder.getZip());
        }
        //This calls the JDBC method which in turn calls the the UPDATE SQL command
        if(OrderService.updateOrder(od)) {

            return Response.ok().entity(od).build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

    //This method represents a DELETE request where the id is provided as a path parameter and the request body is provided in JSON
    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public Response deleteOrder(@PathParam("id") int id) {

        //Retrieve the order_object that you want to delete.
        Order retrievedOrder = OrderService.getOrderById(id);

        if(retrievedOrder == null) {
            //If not found throw a 404
            return Response.status(Response.Status.NOT_FOUND).
                    entity("We could not find the requested resource").build();
        }

        // This calls the JDBC method which in turn calls the DELETE SQL command.
        if(OrderService.deleteOrder(retrievedOrder)) {
            return Response.ok().entity("ORDER Deleted Successfully").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

}
