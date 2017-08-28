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
<form method="post" action="ObraController?action=salvacad"
		name="meu_form">
		<div class="table">
		<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">Nome</span> <input
					type="text" class="form-control" placeholder="Obra de pavimento" name="nomeObra"
					aria-describedby="basic-addon1">
			</div><br />
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">Nº Obra </span> <input
					type="text" class="form-control"placeholder="CCusto" name="ccusto"
					aria-describedby="basic-addon1">
			</div>
			<br />
			
			
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">Descrição</span> <input
					type="text" class="form-control"
					placeholder="Duplicação Br367" name="descr" aria-describedby="basic-addon1">
					
					
			</div><br/>
			<div class="checkbox" align="left">
  <label>
    <input type="checkbox" value="1" name="status">
	Ativo
  </label>
</div>
				
					<div class="row">
						<button type="submit" class="btn btn-primary">Salvar</button>
					</div>
				</div>

			</form>
</div>
	
	</div>
	</div>
		</div>
</body>

</html>

