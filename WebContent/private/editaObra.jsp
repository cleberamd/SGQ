<%@page import="SGQ.construct.model.Obra"%>
<%@page import="java.util.ArrayList"%>
<%@page import="SGQ.construct.model.RIS"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>

<head>
<jsp:include page="import.jsp"></jsp:include>
<style type="text/css">
th, #num, .table {
	text-align: center;
}
</style>
	<%
	
	Obra o = (Obra) request.getAttribute("obraId");
	ArrayList<RIS> listaRIS = (ArrayList<RIS>) request.getAttribute("minhalist");
	
	%>
	<jsp:include page="vincularPerm.jspf"></jsp:include>
</head>
<body style="background-color: #EEE;">
	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">
	<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">CADASTRO DE OBRAS</h3>
			</div>
			<div class="panel-body">
  	<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
  	
<div class="col-md-3">
 <jsp:include page="obraMenu.jsp" />

</div>

<div class="col-md-9">
<form method="post" action="ObraController?action=salvacad&obraId=<%=o.getId() %>"
		name="meu_form">
		<div class="table">
		<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">Nome</span> <input
					type="text" class="form-control" value="<%=o.getNome() %>" name="nomeObra"
					aria-describedby="basic-addon1">
			</div><br />
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">Nº Obra </span> <input
					type="text" class="form-control" value="<%=o.getNumero() %>" name="ccusto"
					aria-describedby="basic-addon1">
			</div>
			<br />
			
			
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">Descrição</span> <input
					type="text" class="form-control"
					value="<%=o.getDescricao() %>" name="descr" aria-describedby="basic-addon1">
			</div><br/>
			<div class="checkbox" align="left">
 <%
  if(o.getStatus()==1){
 %>
  <label>
    <input type="checkbox" value="1" name="status" checked>
	Ativo
  </label><%}else{ %>
   <label>
    <input type="checkbox" value="1" name="status">
	Ativo
  </label><%} %>
</div>
<button id="vincularObra" style="width: 8%;height: 50px;font-size:15px; float: right; " type="button" class="btn btn-default">
 <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
 </button>

					<div class="row">
						<button type="submit" class="btn btn-primary">Salvar</button>
					<a class="btn btn-danger" href="ObraController?action=deleta_obra&obraId=<%=o.getId() %>" role="button">Deletar</a>	
					</div>
				</div>

			</form>
</div>
	<div id="dialog-form" title="Obras" class="checkbox">

  <form id="myForm" action="ObraController?action=cadObra/RIS&obra=<%=o.getId()%>" method="post" >
  
    <fieldset>
    <%
   
    for(int i=0;i<listaRIS.size();i++){
    	 boolean checado=false;
    	for(int j=0;j< o.getRis().size();j++){
    		
    		if(o.getRis().get(j).getRis().getId()==listaRIS.get(i).getId()){
    		checado=true;
    		
    	
    	%>
    
      
 	  
<%}} %>
<div class="checkbox" align="left">
 	
    <label>
    <input type="checkbox"  class="list-group-item" value="<%=listaRIS.get(i).getNumero()%>" name="ris"<% 	if(checado){ %> checked="checked"<%}%>>
    &nbsp <b><%=listaRIS.get(i).getNumero() %></b>&nbsp - &nbsp<%=listaRIS.get(i).getNome() %>
  </label>
 </div>


<%} %>
    </fieldset>
 </form>
</div>
	</div>
	</div>
		</div>
</body>

</html>

