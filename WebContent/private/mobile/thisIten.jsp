<%@page import="SGQ.construct.model.Itens"%>
<%@page import="SGQ.construct.model.RIS"%>
<%
	Itens iten = (Itens) request.getAttribute("iten");
	String obraItem = (String)request.getAttribute("obraItem");
	System.out.println("pg thisiten");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SQO</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="./resources/jquery/jquery.mobile-1.4.5.min.css">
<link rel="stylesheet"
	href="./resources/jquery/jquery.mobile.structure-1.4.5.css">
<script src="./resources/jquery/jquery-1.11.3.min.js"></script>
<script src="./resources/jquery/jquery.mobile-1.4.5.min.js"></script>
<script src="./resources/jquery/jquery.min.js"></script>

<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

</head>
<body>
	<div data-role="page" id="pageone">

		<jsp:include page="headerL.jsp" />

		<div data-role="main" class="ui-content">
			<div class="div-widget">

				<div data-role="collapsible">
					<h1><%=iten.getInspecao()%></h1>
					<table data-role="table" class="ui-responsive ui-shadow">
						<thead>
							<tr>
								<th>Verificação</th>
								<th>Tolerância</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><%=iten.getVerificacao()%></td>


								<td><%=iten.getTolerancia()%></td>

							</tr>
					</table>

				</div>

				<form action="RISController?action=regItem&iten=<%=iten.getId()%>&obraItem=<%=obraItem%>"	method="POST" enctype="multipart/form-data" data-ajax="false">
					<div class="ui-grid-a">
						<div class="ui-block-a">
							<div data-role="fieldcontain">
								<label for="text1">local</label> <input type="text" id="local"
									name="local" required />
							</div>
						</div>

					</div>

					<fieldset name="conform" data-role="controlgroup">
						<script>
							$(document).ready(function() {
								$('#desc').hide();
								

							});
							$('#naoconforme').click(function() {
								$('#desc').show();
							});
							$('#conforme').click(function() {
								$('#desc').hide();
							});
						


						</script>
						<legend>Choose your gender:</legend>
						<label for="conforme">conforme</label> <input type="radio"
							name="gender" id="conforme" value="1" checked="checked" /> 
							
							<label for="conformeR">conforme após reinspeção</label> <input type="radio"
							name="gender" id="conformeR" value="2"  /> 
							
							<label for="naoconforme">não conforme </label> <input type="radio"
							name="gender" id="naoconforme" value="0" />


						<div id="desc">
							<textarea name="descr" id="descr"
								placeholder="Descreva não conformidade e como solucionar"
								data-mini="true"></textarea>
								<div>
								<span
						class="ui-btn ui-btn-inline ui-corner-all ui-icon-camera ui-btn-icon-left ui-alt-icon  fileinput-button">
						<span for="file">Foto</span>
						 <input id ="foto" type="file" name="foto" data-role="none"
						Style="position: relative; float: left; margin-right: 4px; position: absolute; top: 0; right: 0; margin: 0; opacity: 0; filter: alpha(opacity = 0); transform: translate(-300px, 0) scale(4); font-size: 23px; direction: ltr; cursor: pointer;" />
					</span>	</div>
								</div>
					</fieldset>
					
					
					
				
			<input type="submit" data-inline="true" value="Submit">


			</form>
					
			</div>





		</div>

		<jsp:include page="FooterL.jspf" />
	</div>
	

</body>
</html>