<%@page import="SGQ.construct.model.Itens"%>
<%@page import="SGQ.construct.model.RIS"%>
<%@page import="SGQ.construct.model.Usuario"%>
 <%
 HttpSession s = request.getSession();
	Usuario usuario = (Usuario)s.getAttribute("usuario");
	RIS ris = (RIS)request.getAttribute("ris");
 String obraItem =(String) request.getAttribute("obraItem");
 System.out.println("pg thiris");
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
<script src="./resources/jquery/jquery-ui.js"></script>
<script src="./resources/jquery/jquery.mobile-1.4.5.min.js"></script>
<jsp:include page="../msm.jsp" />
</head>
<body>

<div data-role="page" id="pageone">
    <div data-role="header" data-position="fixed" data-theme="b">
                <h1>ENGEPAR</h1>
                <a href="ObraController?action=ris" data-icon="back"	class="ui-btn ui-corner-all ui-icon-back ui-btn-icon-notext">go	back</a>
              
                <a class="ui-btn-right" data-icon="user" href="UsuarioController?action=loginM">${sessionScope.usuario.nome}</a>
</div>
  <div data-role="main" class="ui-content">
  
   <ul data-role="listview" data-inset="true" class="ui-listview ui-listview-inset ui-corner-all ui-shadow">
   <%if(ris.getIts()!=null){ %>
  <li data-role="divider" class="ui-li-static ui-body-inherit ui-first-child"><h2><%=ris.getNome() %></h2>
     <a href="ItsController?action=verIts&itsViewM=<%=ris.getIts().getId() %>" class=" ui-icon-info ui-btn-icon-right ui-alt-icon" ></a>
       
       
       </li>
       <%}else{ %>
    
    <li data-role="divider" class="ui-li-static ui-body-inherit ui-first-child"><h2><%=ris.getNome() %></h2>
        <a href="" class=" ui-icon-info ui-btn-icon-right ui-alt-icon" ></a>
       
       </li>
    
    <%} %>
   
     <% for(Itens it: ris.getItens()){ %>
      
    
      
      <li>
        <a href="RISController?action=iten&iten=<%=it.getId() %>&obraItem=<%=obraItem %>"> &nbsp <h1><%=it.getInspecao() %></h1></a>
      </li>
      <%} %>
      
    </ul>
  </div>
  <jsp:include page="FooterL.jspf" />
</div> 


</body>
</html>