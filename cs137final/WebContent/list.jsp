<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*,java.io.IOException,
java.io.PrintWriter,
java.sql.Connection,
java.sql.DriverManager,
java.sql.PreparedStatement,
java.sql.ResultSet,
java.sql.SQLException,
java.util.ArrayList,

javax.servlet.RequestDispatcher,
javax.servlet.ServletException,
javax.servlet.annotation.WebServlet,
javax.servlet.http.HttpServlet,
javax.servlet.http.HttpServletRequest,
javax.servlet.http.HttpServletResponse,
javax.servlet.http.HttpSession,

org.codehaus.jackson.map.ObjectMapper,
org.codehaus.jackson.type.TypeReference,
org.glassfish.jersey.client.ClientConfig,

javax.ws.rs.client.Client,
javax.ws.rs.client.ClientBuilder,
javax.ws.rs.client.WebTarget,
javax.ws.rs.core.MediaType,
javax.ws.rs.core.UriBuilder,
java.net.URI,
java.util.List,
data.Short" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DIASY SHORTS</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/main.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div>
	<h1>DIASY SHORTS</h1>
	<ul class="naviul">
    	<li class="navili"><a href="list.jsp">Daisy</a></li>
        <li class="navili"><a href="#news">News</a></li>
        <li class="navili"><a href="#contact">Contact</a></li>
        <li class="navili"><a href="about.html">About</a></li>
        <li class="naviliright"><a href="#shoppingcart">Shopping Cart</a></li>
	</ul> 

	<table cellpadding="70">
	
 
<%

ClientConfig clientconfig = new ClientConfig();

Client client = ClientBuilder.newClient(clientconfig);

WebTarget target = client.target("http://centaurus-7.ics.uci.edu:5556/jerseyrest");


String jsonResponse =
        target.path("v1").path("api").path("shorts").
                request(). //send a request
                accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                get(String.class); // use the get method and return the response as a string


ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

List<Short> stList = objectMapper.readValue(jsonResponse, new TypeReference<List<Short>>(){});


int count = 1;
for(Short st : stList) {
    /* out.print("<li>");
    out.print(todo.getSummary() + " - " + todo.getDescription());
    out.print("</li>"); */
	if (count%3==1){
		out.println("<tr><td><a href='Detail?id="+st.getId()+"'><img src="+st.getPic1()+" width='300' alt=''/>"+
        "<p>"+st.getName()+"</p></a>"+
        "<p>$:"+st.getPrice()+"</p>"+
        "<p>color:"+st.getColor()+
        "</p></td>"
        );
		count++;	
	}
	else if (count%3==2){
		out.println("<td><a href='Detail?id="+st.getId()+"'><img src="+st.getPic1()+" width='300' alt=''/>"+
        "<p>"+st.getName()+"</p></a>"+
        "<p>$:"+st.getPrice()+"</p>"+
        "<p>color:"+st.getColor()+
        "</p></td>"
        );
		count++;	
	}
	else if (count%3==0){
		out.println("<td><a href='Detail?id="+st.getId()+"'><img src="+st.getPic1()+" width='300' alt=''/>"+
        "<p>"+st.getName()+"</p></a>"+
        "<p>$:"+st.getPrice()+"</p>"+
        "<p>color:"+st.getColor()+
        "</p></td></tr>"
        );
		count++;	
	}
}

%>
</table>
</div>
</body>
</html>