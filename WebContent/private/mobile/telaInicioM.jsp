<%@page import="SGQ.construct.model.Usuario"%>
<%@page import="SGQ.construct.model.Obra"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	
	HttpSession s = request.getSession();
	Usuario usuario = (Usuario) s.getAttribute("usuario");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SQO</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="./resources/jquery/jquery.mobile-1.4.5.min.css">
<script src="./resources/jquery/jquery-1.11.3.min.js"></script>
<script src="./resources/jquery/jquery.mobile-1.4.5.min.js"></script>

</head>
<body>

	<div data-role="page" id="page2">
		<jsp:include page="headerL.jsp" />
		<div data-role="content">
			<div class="div-widget">

				<form action="ObraController?action=acessaObra" method="POST">
					<select data-native-menu="false" name="obra">
						<%
							for (Obra obra : usuario.getObra()) {
								System.out.println(obra.getNome()+" pg inicio");
						%>
						<option value=<%=obra.getId() %>><%=obra.getNumero()%> -
							<%=obra.getNome()%></option>
						<%
							}
						%>
					</select>
					<input type="submit" value="Acessar" data-mini="true">
				</form>


			</div>
			
			
		</div>
		<jsp:include page="FooterL.jspf" />
	</div>


</body>
</html>