
package data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.json.bind.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import data.Order;
import java.util.Date;

/**
 * Servlet implementation class Confirmation
 */
@WebServlet("/Confirmation")
public class Confirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Confirmation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//String loginUser = "inf124db081";
        //String loginPasswd = "OSFdzmM@~@Cc";
        //String loginUrl = "jdbc:mysql://matt-smith-v4.ics.uci.edu/inf124db081";
		ClientConfig clientconfig = new ClientConfig();

        Client client = ClientBuilder.newClient(clientconfig);

        WebTarget target = client.target("http://centaurus-7.ics.uci.edu:5556/jerseyrest");
 
        response.setContentType("text/html"); 
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>DIASY SHORTS</title>");
		out.println("<meta charset='UTF-8'>");
		out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		out.println("<link href='css/main.css' rel='stylesheet' type='text/css'/>");
		out.println("</head>");
		out.println("<body><div><h1>DIASY SHORTS</h1><ul class=\"naviul\">");
		out.println("<li class=\"navili\"><a href=\"list.jsp\">Daisy</a></li>");            
		out.println("<li class=\"navili\"><a href=\"#news\">News</a></li>");            
		out.println("<li class=\"navili\"><a href=\"#contact\">Contact</a></li>");            
		out.println("<li class=\"navili\"><a href=\"about.html\">About</a></li>");            
		out.println("<li class=\"naviliright\"><a href=\"BuyShort\">Shopping Cart</a></li>");
		int count=Integer.parseInt(request.getParameter("count"));
		//out.print(count);
		ArrayList List = new ArrayList();
		for(;count>0;count--) {
			String short_name="short"+count;
			String temp=request.getParameter(short_name);
			List.add(temp);
			//out.println(temp);
			//short_true_name=request.getParameter(short_name);
		}
		out.println(List.size());
		out.println(" </ul> ");       
		//try {
        	//Class.forName("com.mysql.jdbc.Driver").newInstance();
    		//Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
    		//for (Object previousItem : previousItems)
    		Date date = new Date();
    		int id = (int) date.getTime();
    		id=id*-1;
    		while(id>=1000000000) {
    			id=id/100;
    		}
    		int shortId = 0;
    		int size = 0;
    		int qty = 1;
    		int customerId=0;
    		String address=request.getParameter("street_address");
    		String shipMethod="normal";		
    		String city=request.getParameter("city");
    		String state=request.getParameter("state");
    		String zip=request.getParameter("zip");
    		String total_price=request.getParameter("total_price");
    		//out.println("<script>alert("+total_price+")</script>");
    		Order od=new Order();
    		od.setId(id);
    		od.setAddress(address);
    		od.setCity(city);
    		od.setShipMethod(shipMethod);
    		od.setQty(qty);
    		od.setShortId(shortId);
    		od.setSize(size);
    		od.setState(state);
    		od.setZip(zip);
    		od.setCustomerId(customerId);
    		Response rs =
                    target.path("v1").path("api").path("orders").
                            request(). //send a request
                            post(Entity.entity(od,MediaType.APPLICATION_JSON));
    		//out.println("<script>alert("+jsonResponse2+")</script>");
    		//ObjectMapper objectMapper = new ObjectMapper();
            //Order order = objectMapper.readValue(jsonResponse2, Order.class);
    		//if(objectMapper.readValue(jsonResponse2, Order.class).equals("ORDER Added Successfully")) {
    			Order order=od;
				out.println("<h2 style='text-align:center'>Thank you for your order!</h2>");
		        out.println("<h2 style='text-align:center'>Order Details</h2>");
		        out.println("<p style='text-align:center'>Order Id:"+order.getId()+"</p >");
		        
		        out.println("<ul style='padding:0 500px'>Product Information:");
		        for(int i=0;i<List.size();i++) {
		        	out.println("<li>"+List.get(i)+"</li>");
		        }
		        out.println("<li>Total price:"+total_price+"</li>");
		        out.println("<li>Shipping Address:"+order.getAddress()+","+order.getCity()+","+order.getState()+","+order.getZip()+"</li>");
		        out.println("<li>Shipping Method:"+order.getShipMethod()+"</li>");
		        out.println("<li>Contact Number:"+request.getParameter("phone_number")+"</li></ul>");
			//}
			//if(rs.next()) {
				
			//}
//			rs.close();
//			statement.close();
//			statement1.close();
//			dbcon.close();
//        }catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			out.println(e.toString());
//			out.println(e.getMessage());
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
