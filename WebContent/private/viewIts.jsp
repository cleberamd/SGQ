
<%@page import="SGQ.construct.model.Its"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<jsp:include page="import.jsp"></jsp:include>
	
		  
  
<%
		 
		Its i = (Its) request.getAttribute("its");
		
			
			
	%>

</head>
<body style="background-color: #EEE;">
<jsp:include page="navbar.jsp"></jsp:include>
<div class="container" >

	<div class="container" >
		<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">ITS CADASTRADA COM SUCESSO!</h3>
			</div>
			<div class="panel-body">
  	<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
  	
	
		
		<div class="row">
		<i><%=i.getNome() %></i>&nbsp;&nbsp;<i><%=i.getDescric() %></i>
		</div>
			<iframe width="100%" height="750" src="<%=i.getLink() %>" frameBorder=0></iframe>
		
									<p>Caso não carregue automaticamente click <a href="<%=i.getLink()%>" data-ajax="false"><b>aqui</b> </a></p>
		
		</div>
	<div class="row">
	<br/>
		<a class="btn btn-danger" href="ItsController?action=deleta_its&itsId=<%=i.getId()%>" role="button">Deletar</a>
	</div>
	
	</div>
	
	</div>
	</div>
	</body>

</html>