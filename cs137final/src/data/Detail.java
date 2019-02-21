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
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import data.Short;


/**
 * Servlet implementation class Detail
 */
@WebServlet("/Detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Detail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
        
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Pic1</title><meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<link rel='stylesheet' href='https://www.w3schools.com/w3css/4/w3.css'>");
        out.println("<link href='css/main.css' rel='stylesheet' type='text/css'/></head>");
        out.println("<link href=\"/try/bootstrap/twitter-bootstrap-v2/docs/assets/css/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\" />");
        out.println("<body><div><h1>DIASY SHORTS</h1>");
        out.println("<ul class='naviul'>");
        out.println("<li class='navili'><a href='list.jsp\'>Daisy</a ></li>");
        out.println("<li class='navili'><a href='#news'>News</a ></li>");
        out.println("<li class='navili'><a href='#contact'>Contact</a ></li>");
        out.println("<li class='navili'><a href='about.html\'>About</a ></li>");
        out.println("<li class='naviliright'><a href='BuyShort\'>Shopping Cart</a ></li></ul></div>");
        String item_id=request.getParameter("id");
        
        HttpSession session = request.getSession(); // Get a instance of current session on the request
        ArrayList<String> previousItems = (ArrayList<String>) session.getAttribute("previousItems"); // Retrieve data named "previousItems" from session

        // If "previousItems" is not found on session, means this is a new user, thus we create a new previousItems ArrayList for the user
        if (previousItems == null) {
            previousItems = new ArrayList<>();
            session.setAttribute("previousItems", previousItems); // Add the newly created ArrayList to session, so that it could be retrieved next time

        }
        	//Class.forName("com.mysql.jdbc.Driver").newInstance();
    		//Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
			//System.out.println("success connection");
			
			//get movie id
			//String query = "SELECT id, pic1,pic2,pic3,name, price, color, description FROM short where id=?";
			//PreparedStatement statement = dbcon.prepareStatement(query);
			//statement.setString(1, item_id);
			//ResultSet rs=statement.executeQuery();
        ClientConfig clientconfig = new ClientConfig();

        Client client = ClientBuilder.newClient(clientconfig);

        WebTarget target = client.target("http://centaurus-7.ics.uci.edu:5556/jerseyrest");


        String jsonResponse =
                target.path("v1").path("api").path("shorts").path(item_id).
                        request(). //send a request
                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                        get(String.class); // use the get method and return the response as a string
        ObjectMapper objectMapper = new ObjectMapper();
        Short rs = objectMapper.readValue(jsonResponse, Short.class);
        //ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library
    		
    		//if(rs.next()) {
    			out.println("<div style='height:800px; padding:0 0 100px;'>");
                out.println("<div class='w3-content' style='max-width:700px; margin:50px 50px 0 200px; float:left'>");
                out.println("<div class='w3-row-padding w3-section leftfloat'>");
                out.println("<div>");
                out.println("<img class='demo w3-opacity w3-hover-opacity-off' src='" + rs.getPic1() +"' style='width:100%; padding:5px' onclick='currentDiv(1)'>");
                out.println("</div>");
                out.println("<div>");
                out.println("<img class='demo w3-opacity w3-hover-opacity-off' src='" + rs.getPic2() +"' style='width:100%; padding:5px' onclick='currentDiv(2)'>");
                out.println("</div>");
                out.println("<div>");
                out.println("<img class='demo w3-opacity w3-hover-opacity-off' src='" + rs.getPic3() +"' style='width:100%; padding:5px' onclick='currentDiv(3)'>");
                out.println("</div>");
                out.println("</div>");
                out.println("<div>");
                out.println("<img class='mySlides floyd w3-animate-top' src='" + rs.getPic1() + "' style='width:70%'>");
                out.println("<img class='mySlides floyd w3-animate-top' src='" + rs.getPic2() + "' style='width:70%'>");
                out.println("<img class='mySlides w3-animate-top floyd' src='" + rs.getPic3() + "' style='width:70%'>");
                out.println("</div>");
                out.println("</div>");


                out.println("<div class='information_details' style='padding:30px'>");
                out.println("<h3>" + rs.getName() + "</h3>");
                out.println("<p>$" + rs.getPrice() + "</p >");
                out.println("<p class='small_caption'>Color: </p >");
                out.println("<p class='color'>" + rs.getColor() + "</p >");
                out.println("<p class='small_caption'>Size available:</p >");
        
                out.println("<table class='sizetable' border>");
                out.println("<td class='sizetd'>24</td>");
                out.println("</table>");
                out.println("<table class='sizetable' border>");
                out.println("<td class='sizetd'>25</td>");
                out.println("</table>");
                out.println("<table class='sizetable' border>");
                out.println("<td class='sizetd'>26</td>");
                out.println("</table>");
                out.println("<table class='sizetable' border>");
                out.println("<td class='sizetd'>27</td>");
                out.println("</table>");
                out.println("<table class='sizetable' border>");
                out.println("<td class='sizetd'>28</td>");
                out.println("</table>");
                out.println("<table class='sizetable' border>");
                out.println("<td class='sizetd'>29</td>");
                out.println("</table>");
                out.println("<table class='sizetable' border>");
                out.println("<td class='sizetd'>30</td>");
                out.println("</table>");
                out.println("<table class='sizetable' border>");
                out.println("<td class='sizetd'>31</td>");
                out.println("</table>");
                out.println("<br /><br />");
                out.println("<p class='customersay'>");
            
                out.println("Customers Say: True to Size</p >");
       
                out.println("<p class='small_caption'>Details:</p >");
                out.println("<p>Product ID:" + item_id + "</p >");
                out.println("<br>" + rs.getDescription());
                String url = "BuyShort?id="+item_id;
                //String urlname = "BuyShort?name="+rs.getString("name");
                out.println("</br>");
                out.println("<a style='font-weight:bold' href='"+url+"'>ADD TO CART</a>");
                //out.println("<a href='"+urlname+"'>ADD TO CART</a>");
                out.println("</div> </div>");
    		//}
    		out.println("<script src=\"js/ajax.js\"></script>");
    		out.println("<script src=\"js/detail.js\"></script>");
    		out.println("</body></html>");
    		
    		//String newItem = request.getParameter("name"); // Get parameter that sent by GET request url
            String newItem=rs.getName();
            synchronized (previousItems) {
                if (newItem != null) {
                	if(previousItems.size()>=5) {
                		previousItems.remove(previousItems.get(0));
                	}
                    previousItems.add(newItem); // Add the new item to the previousItems ArrayList
                }
            }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
