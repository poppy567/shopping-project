package data;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class History
 */
@WebServlet("/History")
public class History extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public History() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(); // Get a instance of current session on the request
        ArrayList<String> previousItems = (ArrayList<String>) session.getAttribute("previousItems"); // Retrieve data named "previousItems" from session

        // If "previousItems" is not found on session, means this is a new user, thus we create a new previousItems ArrayList for the user
        if (previousItems == null) {
            previousItems = new ArrayList<>();
            session.setAttribute("previousItems", previousItems); // Add the newly created ArrayList to session, so that it could be retrieved next time

        }

        String newItem = request.getParameter("newItem"); // Get parameter that sent by GET request url

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Items Visited";
        String docType =
                "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n";
        out.println(String.format("%s<html>\n<head><title>%s</title></head>\n<body>\n<h1>%s</h1>", docType, title, title));
        synchronized (previousItems) {

            // Display the current previousItems ArrayList
            if (previousItems.size() == 0) {
                out.println("<i>No items</i>");
            } else {
                out.println("<ul>");
                for (Object previousItem : previousItems) {
                    out.println("<li>" + previousItem);
                }
                out.println("</ul>");
            }
        }

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
