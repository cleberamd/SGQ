<%@page import="SGQ.construct.model.Obra"%>
<%@page import="SGQ.construct.model.Its"%>
<%@page import="java.util.List"%>
 <%
 HttpSession s = request.getSession(true);
	Obra obra =(Obra)s.getAttribute("obra1");
	List<Its> its = (List<Its>)request.getAttribute("its");
	System.out.println( "pg thisIts");
    %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SQO</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="./resources/jquery/jquery.mobile-1.4.5.min.css">
<link rel="stylesheet" href="./resources/jquery/jquery.mobile.structure-1.4.5.css">
<script src="./resources/jquery/jquery-1.11.3.min.js"></script>
<script src="./resources/jquery/jquery.mobile-1.4.5.min.js"></script>
</head>
<body>

<div data-role="page" id="pageone">
    <jsp:include page="headerL.jsp" />
  <div data-role="main" class="ui-content">
  
   <ul data-role="listview" data-inset="true" class="ui-listview ui-listview-inset ui-corner-all ui-shadow">
      <li data-role="divider" class="ui-li-static ui-body-inherit ui-first-child"><h2><%=obra.getNome() %>&nbsp(Its)</h2></li>
      
    <%  for(Its i: its){
     	
    	 %>
      
      		<li>
        <a href="ItsController?action=verIts&itsViewM=<%=i.getId() %>"> &nbsp<h1> <h1><%=i.getNome() %></h1></h1></a>
      </li>
      <%} %>
     
  
      
    </ul>
  </div>
  <jsp:include page="FooterL.jspf" />
</div> 


</body>
</html>