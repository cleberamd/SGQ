<%@page import="SGQ.construct.model.Usuario"%>
<%@page import="SGQ.construct.model.Obra"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
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
	
	
	ArrayList<Obra> obra =(ArrayList<Obra>) request.getAttribute("listObra");
	Usuario u =(Usuario) request.getAttribute("usuarioId");
	%>
	
	<jsp:include page="vincularPerm.jspf"></jsp:include>
</head>
<body style="background-color: #EEE;">
	<jsp:include page="navbar.jsp" />
	<div class="container" >
		<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">EDITAR USUARIOS</h3>
			</div>
			<div class="panel-body">
  	<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
  	
	
		<div class="col-md-3">
			
		<div class="list-group">
 <jsp:include page="userMenu.jsp" />
</div>
		</div>
		<div class="col-md-9">
		<form method="post" action="UsuarioController?action=novo_user&usuariId=<%=u.getId() %>" >
				
			<div class="table">
			
			
		<div class="input-group">	
			<span class="input-group-addon" id="basic-addon1">Nome </span>
  <input type="text" name="nome" class="form-control" value="<%=u.getNome() %>"aria-describedby="basic-addon1">
</div>
<br/>
<div class="input-group">
	<span class="input-group-addon" id="basic-addon1">Sobrenome </span>
  <input type="text" name="Sobrenome" class="form-control" value="<%=u.getSobrenome()%>" aria-describedby="basic-addon1">
 
</div>
		<br/>	
	<div class="input-group">		
  <span class="input-group-addon" id="basic-addon1">Usuario </span>
  <input type="text" name="Usuario" class="form-control" value="<%=u.getUsuario() %>" aria-describedby="basic-addon1">
  </div>
<br/>
<div class="input-group">
<span class="input-group-addon" id="basic-addon1">Senha</span>
  <input type="password" name="senha" class="form-control" value="<%=u.getSenha() %>" aria-describedby="basic-addon1">
</div><br/>
<div class="input-group">
<span class="input-group-addon" id="basic-addon1">Função</span>
  <input type="text" name="funcao" class="form-control" value="<%=u.getFuncao() %>" aria-describedby="basic-addon1">
</div><br/>

 
<div class="checkbox" align="left">
 
 
   <label>
    <input type="checkbox"  value="1" name="perfil"  <%if(u.getPerfil()==1){%>checked="checked"<%}%>>
    Administrador
  </label>
</div>
<div class="checkbox" align="left">

  <label>
    <input type="checkbox" value="1" name="status"  <%if(u.getStatus()==1){%>checked="checked"<%}%>>
	Ativo
  </label>
  
  
</div>

<button id="vincularObra" style="width: 8%;height: 50px;font-size:15px; float: right; " type="button" class="btn btn-default">
 <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
 </button>


			
			
		<button type="submit" class="btn btn-primary">Salvar</button>	
		<a class="btn btn-danger" href="UsuarioController?action=deleta_user&usuarioId=<%=u.getId() %>" role="button">Deletar</a>
			</div>
			

			</form>

<div id="dialog-form" title="Obras" class="checkbox">

  <form id="myForm" action="UsuarioController?action=cadObra/User&user=<%=u.getId()%>" method="post" >
  
    <fieldset>
    <%
   
    for(int i=0;i<obra.size();i++){
    	 boolean checado=false;
    	for(int j=0;j< u.getObra().size();j++){
    		
    		if(u.getObra().get(j).getId()==obra.get(i).getId()){
    		checado=true;
    		
    	
    	%>
    
      
 	  
<%}} %>
<div class="checkbox" align="left">
 	
    <label>
    <input type="checkbox"  class="list-group-item" value="<%=obra.get(i).getNumero()%>" name="obra_id"<% 	if(checado){ %> checked="checked"<%}%>>
    &nbsp <b><%=obra.get(i).getNumero() %></b>&nbsp - &nbsp<%=obra.get(i).getNome() %>
  </label>
 </div>


<%} %>
    </fieldset>
 </form>
</div>
  
	</div>
			 
	</div>	
	</div>
	</div>
	
</body>
</html>