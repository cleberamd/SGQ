<%@page import="SGQ.construct.model.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="import.jsp"></jsp:include>
	
	<style type="text/css">
		th, #num,.table{
		text-align: center;
		}	
	</style>
	
	<%


	if(request.getAttribute("listUsuario")!=null){
		ArrayList<Usuario> listUsuario = (ArrayList<Usuario>) request.getAttribute("listUsuario");
	
	
	
%>
	
</head>
<body style="background-color: #EEE;">
	<jsp:include page="navbar.jsp" />
	<div class="container" >
	<div id="output" ></div>
	<jsp:include page="msm.jsp"></jsp:include>
		<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">USUARIOS CADASTRADOS</h3>
			</div>
			<div class="panel-body">
  	<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
  	
	
		
		<div class="col-md-3">
			
		
  <jsp:include page="userMenu.jsp" />
  

		</div>
		<div class="col-md-9">
		
		
		<div class="list-group" >
		<%
		for (Usuario u : listUsuario) {
		%>
	<form action="UsuarioController?action=verUsuario&usuarioId=<%=u.getId()%>" method="post">
		
		
  <button style="width: 100%;text-align: left;" type="submit" class="list-group-item"><%=u.getId()%> - <%=u.getNome()%> <%=u.getSobrenome() %></button>
  </form>
  <%} }%>
  
</div>
		
		</div>
	
			</div>
			</div>
			
		</div>
	
</body>
</html>