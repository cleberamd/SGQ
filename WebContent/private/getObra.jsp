<%@page import="SGQ.construct.model.Obra"%>

<%@page import="java.util.ArrayList"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>

<head>	

	<jsp:include page="import.jsp"></jsp:include>
	
	<style type="text/css">
	th, #num{
	text-align: center;
	}	
	</style>

	
</head>
<body style="background-color: #EEE;">



	

	
<jsp:include page="navbar.jsp"></jsp:include>
<div class="container">
<div id="output" ></div>
<jsp:include page="msm.jsp"></jsp:include>
	<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">OBRAS CADASTRADAS</h3>
			</div>
			<div class="panel-body">
  	<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
  	
	<div class="col-md-3">
  	  <jsp:include page="obraMenu.jsp" />
  	</div>
  	<div class="col-md-9">
  	
  	<div class="list-group" >
  
  	<%
	HttpSession s = request.getSession();
	if(session.getAttribute("listObra")!=null){
		ArrayList<Obra> listaObra = (ArrayList<Obra>) s.getAttribute("listObra");
		for (Obra o : listaObra) {
	
	
	%>
	<form action="ObraController?action=verObra&obraId=<%=o.getId()%>" method="post">
  		<button style="width: 100%;text-align: left;" type="submit" class="list-group-item"><%=o.getNumero()%> <%=o.getNome()%> </button>
  		
	 </form>
	 <%}}%>
	</div>
	
  	<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
  	</div>
    
</div>
  	</div>
  	</div>
  
  
</body>

</html>

