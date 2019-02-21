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

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import data.Short;

/**
 * Servlet implementation class BuyShort
 */
@WebServlet("/BuyShort")
public class BuyShort extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyShort() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String name=request.getParameter("name");
		//String loginUser = "inf124db081";
        //String loginPasswd = "OSFdzmM@~@Cc";
        //String loginUrl = "jdbc:mysql://matt-smith-v4.ics.uci.edu/inf124db081";
		
		HttpSession Cartsession=request.getSession();
		ArrayList<String> choosedItems = (ArrayList<String>) Cartsession.getAttribute("choosedItems"); // Retrieve data named "choosedItems" from Cartsession

        // If "choosedItems" is not found on Cartsession, means this is a new user, thus we create a new choosedItems ArrayList for the user
        if (choosedItems == null) {
            choosedItems = new ArrayList<>();
            Cartsession.setAttribute("choosedItems", choosedItems); // Add the newly created ArrayList to Cartsession, so that it could be retrieved next time

        }

        String newItem = request.getParameter("id"); // Get parameter that sent by GET request url
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String item_name="";
		String item_color="";
		String item_price="";
		
		ClientConfig clientconfig = new ClientConfig();

        Client client = ClientBuilder.newClient(clientconfig);

        WebTarget target = client.target("http://centaurus-7.ics.uci.edu:5556/jerseyrest");


        String jsonResponse =
                target.path("v1").path("api").path("shorts").path(newItem).
                        request(). //send a request
                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                        get(String.class); // use the get method and return the response as a string
        ObjectMapper objectMapper = new ObjectMapper();
        Short rs = objectMapper.readValue(jsonResponse, Short.class);
        //try {
        //	Class.forName("com.mysql.jdbc.Driver").newInstance();
//    		Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
//			
//			String query = "SELECT name, price, color FROM short where id=?";
//			PreparedStatement statement = dbcon.prepareStatement(query);
//			statement.setString(1, newItem);
//			ResultSet rs=statement.executeQuery();
			
			//if(rs.next()) {
				item_name=rs.getName();
				item_color=rs.getColor();
				item_price=String.valueOf(rs.getPrice());
			//}
//			rs.close();
//			statement.close();
//			dbcon.close();
//        }catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			out.println(e.toString());
//			out.println(e.getMessage());
//		}
        
        out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>DIASY SHORTS</title>");
		out.println("<meta charset='UTF-8'>");
		out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		out.println("<link href='css/main.css' rel='stylesheet' type='text/css'/>");
		out.println("</head>");
		out.println("<body><script src=\"js/detail.js\"></script>"
				+ "<script src=\"js/ajax.js\"></script>"
				+ "<div><h1>DIASY SHORTS</h1><ul class=\"naviul\">");
		out.println("<li class=\"navili\"><a href=\"list.jsp\">Daisy</a></li>");            
		out.println("<li class=\"navili\"><a href=\"#news\">News</a></li>");            
		out.println("<li class=\"navili\"><a href=\"#contact\">Contact</a></li>");            
		out.println("<li class=\"navili\"><a href=\"about.html\">About</a></li>");            
		out.println("<li class=\"naviliright\"><a href=\"BuyShort\">Shopping Cart</a></li>");          
		System.out.println("success read html");
		String fn=request.getParameter("first_name");
        if(fn!=null) {
        RequestDispatcher rd=request.getRequestDispatcher("Confirmation");  
        rd.forward(request, response);
        }
		out.println(" </ul> ");       
        String title = "Items Purchased";
        String docType =
                "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n";
        out.println(String.format("%s<html>\n<head><title>%s</title></head>\n\n<h1>%s</h1>", docType, title, title));
        int total_price=0;
        // In order to prevent multiple clients, requests from altering choosedItems ArrayList at the same time, we lock the ArrayList while updating
        synchronized (choosedItems) {
            if (newItem != null) {
                choosedItems.add(item_price+item_name); // Add the new item to the choosedItems ArrayList
            }

            // Display the current choosedItems ArrayList
            if (choosedItems.size() == 0) {
                out.println("<i style='text-align:center'>No items</i>");
            } else {
                out.println("<ul style='text-align:center;list-style-type:none'>");
                for (Object choosedItem : choosedItems) {
                    out.println("<li>" + choosedItem.toString().substring(2));
                    //out.println(item_name);
                    //out.println(item_color);
                    //out.println(item_price);
                }
                
                for(Object choosedItem : choosedItems) {
                	int price=Integer.parseInt(choosedItem.toString().substring(0,2));
                	total_price=total_price+price;
                }
                out.println("<br>Total Price:"+total_price);
                out.println("</ul>");
            }
        }
        out.println("<div class='orderdiv'>");
        out.println("<h2>Order Information</h2>");
        //out.println("<form method='post' name='orderform'  onSubmit='return check()' style='margin:0 0 0 145px'>");
        out.println("<form method='post' name='orderform'  onSubmit='return check()' style='margin:0 0 0 145px'>");
        int count=0;
        
        for(Object choosedItem : choosedItems) {
        	count++;
        	String short_name="short"+count;
        	String true_name=choosedItem.toString().substring(2).replace(' ', '_');
        	out.println("<input type='hidden' name="+short_name+" value="+true_name+">");
        }
        out.println("<input type='hidden' name='count' value="+count+">");    
        out.println("<input type='hidden' name='total_price' value="+total_price+">");    
        out.println("Full Name: <br />");
        out.println("<input type='text' id='first_name' name='first_name' placeholder='First Name' size='20' required>");
        out.println("<input type='text' id='last_name' name='last_name' placeholder='Last Name' size='20' required>");
        out.println("<br /><br />");

        out.println("Contact Number: <br />");
        out.println("<input type='text' id='phone_number' pattern='\\d*' name='phone_number' placeholder='Phone Number' size='20' maxlength='10' required>");
        out.println("<br /><br />");

        out.println("Shipping Address: <br />");
        out.println("<input type='text' id='street_address' name='street_address' placeholder='Street Address' size='77' required><br />");
        out.println("<input type='text' maxlength='10' id='zip' name='zip' placeholder='Postal/Zip Code' size='20' onblur='getPlace(this.value)' pattern='\\d*' required>");
        out.println("<input type='text' name='city' id='city' placeholder='City' size='25' required>");
        out.println("<input type='text' name='state' id='state' placeholder='State' size='25' required>"); 
        out.println("<br /><br />");

        out.println("Credit/Debit Card: <br />");
        out.println("<input type='text' id='cardholder' name='cardholder' placeholder='Card Holder' size='40' required>");
        out.println("<input type='text' id='cvv' pattern='\\d*' name='cvv' placeholder='CVV' size='25' maxlength='3' required><br />");
        out.println("<input type='text' id='cardnumber' name='cardnumber' placeholder='Card Number' size='40' onkeyup='formatBankNo(this)' onkeydown='formatBankNo(this)' required>");
        out.println("<select><option value='01'>January</option><option value='02'>February </option><option value='03'>March</option><option value='04'>April</option><option value='05'>May</option><option value='06'>June</option><option value='07'>July</option><option value='08'>August</option><option value='09'>September</option><option value='10'>October</option><option value='11'>November</option><option value='12'>December</option></select>");
        out.println("<select><option value='19'> 2019</option><option value='20'> 2020</option><option value='21'> 2021</option><option value='22'> 2022</option><option value='23'> 2023</option><option value='24'> 2024</option></select>");
        out.println("<br /><br />\n<input type=\"submit\" value=\"SUBMIT\">");
        out.println("<br /><br /></form></div>");
        out.println("</body></html>");
        
        
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
