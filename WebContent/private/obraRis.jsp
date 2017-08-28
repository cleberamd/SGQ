<%@page import="SGQ.construct.model.RIS"%>
<%@page import="SGQ.construct.model.Itens"%>
<%@page import="java.util.ArrayList"%>
<%@page import="SGQ.construct.model.Obra"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>

<head>
	<jsp:include page="import.jsp"></jsp:include>

<style type="text/css">
 #num, .table {
	text-align: center;
}
</style>

<%
	HttpSession s = request.getSession();

	ArrayList<Obra> listaObra = (ArrayList<Obra>) session.getAttribute("listObra");
	
	
	ArrayList<RIS> listaRIS = (ArrayList<RIS>) session.getAttribute("minhalist");
	
%>

</head>

<body style="background-color: #EEE;">
<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">
	<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">VINCULAR OBRAS AS RIS</h3>
			</div>
			<div class="panel-body">
  	<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
  	
	
		<div class="col-md-3">
		 <jsp:include page="obraMenu.jsp" />
		</div>
		<div class="col-md-9">
		
							<form method="post" action="ObraController?action=cadObra/RIS"	name="meu_form">
		
		<div class="col-md-3">
		<br/>
		<span>Obra</span>
		<select name="obra" class="form-control" >
								<option  > </option>
										<%
											for (Obra o : listaObra) {
										%>
										<option value=<%=o.getId()%>><%=o.getNumero()%>	<%=o.getNome()%></option><%}%>
								</select><br/>
								 
							<button type="submit" class="btn btn-primary">Salvar</button>
								
					
		</div>
		<span>Ris</span>
		<div class="col-md-9 checkbox"  >
		
		<%	
										for (RIS r : listaRIS) {
										%>
								
								   <label class="form-control" Style="text-align: left;padding-left: 5%"><input  name="ris" type="checkbox" value="<%=r.getNumero()%>"><%=r.getNumero() %> - <%=r.getNome() %></label>
									<%} %>
		</div>
		</form>
		
							
							
							
		</div>
		
		
			</div>
			</div>
				
		</div>


							
</body>

</html>

