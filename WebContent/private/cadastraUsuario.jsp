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
</head>
<body style="background-color: #EEE;">
	<jsp:include page="navbar.jsp" />
	<div class="container" >
		<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">CADASTRO DE USUARIOS</h3>
			</div>
			<div class="panel-body">
  	<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
  	
		
		<div class="col-md-3">
			
		<div class="list-group">
 <jsp:include page="userMenu.jsp" />
</div>
		</div>
		<div class="col-md-9">
		<form method="post" action="UsuarioController?action=novo_user" name="meu_form">
				
			<div class="table">
			
			
		<div class="input-group">	
			<span class="input-group-addon" id="basic-addon1">Nome </span>
  <input type="text" name="nome" class="form-control" placeholder="Jose" aria-describedby="basic-addon1">
</div>
<br/>
<div class="input-group">
	<span class="input-group-addon" id="basic-addon1">Sobrenome </span>
  <input type="text" name="Sobrenome" class="form-control" placeholder="da Silva" aria-describedby="basic-addon1">
</div>
		<br/>	
	<div class="input-group">		
  <span class="input-group-addon" id="basic-addon1">Usuario </span>
  <input type="text" name="Usuario" class="form-control" placeholder="jose.silva" aria-describedby="basic-addon1">
  </div>
<br/>
<div class="input-group">
<span class="input-group-addon" id="basic-addon1">Senha</span>
  <input type="password" name="senha" class="form-control" placeholder="******" aria-describedby="basic-addon1">
</div><br/>
<div class="input-group">
<span class="input-group-addon" id="basic-addon1">Função</span>
  <input type="text" name="funcao" class="form-control" placeholder="Auxiliar" aria-describedby="basic-addon1">
</div><br/>
<div class="checkbox" align="left">
  <label>
    <input type="checkbox"  value="1" name="perfil">
    Administrador
  </label>
</div>
<div class="checkbox" align="left">
  <label>
    <input type="checkbox" value="1" name="status">
	Ativo
  </label>
</div>


			<br/>
		<button type="submit" class="btn btn-primary">Salvar</button>	
		
			</div>
			</form>

		
	</div>
	</div>
	</div>		
	</div>	
	
	
</body>
</html>