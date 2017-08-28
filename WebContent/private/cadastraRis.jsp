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


<script>
	j = 2;
	function clonarLinha() {
		var clone = document.getElementById('origem').cloneNode(true);
		var destino = document.getElementById('destino');

		var camposClonados = clone.getElementsByTagName('input');

		for (i = 0; i < camposClonados.length; i++) {
			camposClonados[i].value = j++;
		
		}
		var camposClonados1 = clone.getElementsByTagName('textarea');

		for (h = 0; h < camposClonados1.length; h++) {
			camposClonados1[h].value = "";

		}
		destino.appendChild(clone);

	}
	function deleteRow( ){
		tbl = document.getElementById('tabela')
		objRow = tbl.getElementsByTagName('tr');
				objRowSize = objRow.length - 1;
				for( i = objRowSize; i >= 0; i-- ){ // faz um loop de baixo pra cima em todas as linhas
					if (i>1) {
						tbl.deleteRow(objRowSize); // exclui a linha encontrada
						j--;
					}
					
					
				}
		}
</script>



</head>
<body style="background-color: #EEE;">
	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">
		<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">SISTEMA DA QUALIDADE REGISTRO DE
					INSPEÇÃO DE SERVIÇOS</h3>
			</div>
			<div class="panel-body">
				<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->

				<div class="col-md-3">
					<jsp:include page="risMenu.jsp" />

				</div>
				<div class="col-md-9">

					<div class="panel panel-default">
						<!-- Default panel contents -->
						<div class="panel-heading">ENGEPAR</div>
						<div class="panel-body">
							<p>SISTEMA DA QUALIDADE - REGISTRO DE INSPEÇÃO DE SERVIÇOS</p>
						</div>
						<form method="post" action="RISController?action=cad"
							name="meu_form">
							<div class="table ">

								<table class="table ">
									<thead>
										<tr>
											<th width="19%">NUMERO</th>
											<th>TITULO</th>

										</tr>

										<tr>

											<th><input type="number" class="form-control"
												placeholder="10" name="RisNum"></th>
											<th><input type="text" class="form-control"
												placeholder="Revestimento Externo  Argamassa" name="Titulo">

											</th>

										</tr>
									</thead>
								</table>


							</div>

							<div class="table">

								<table class="table " id="tabela" >
									<thead>
										<tr>
											<th>Nº</th>
											<th>Item de Inspeção</th>
											<th>Método de verificação</th>
											<th>Tolerância</th>
										</tr>
										</thead>
										<tbody>
										<tr id="origem">

											<th style="width: 10%"><input type="text"
												class="form-control" value='1' name="num" readonly="true"
												id="num" align="middle" /></th>
											<th><textarea class="form-control" rows="3" name="insp"></textarea>
											</th>
											<th><textarea class="form-control" rows="3" name="veref"></textarea>
											</th>
											<th><textarea class="form-control" rows="3" name="tole"></textarea>
											</th>
										</tr>

									
									
									</tbody>
									<tbody id="destino">
									</tbody>

								</table>

								<div class="btn-group btn-group-sm" role="group"
									aria-label="Small button group">
									<button onclick="clonarLinha()" type="button" class="btn btn-default glyphicon glyphicon-plus">&nbsp;add</button>
									<button onclick="deleteRow()" type="button" class="btn btn-default glyphicon glyphicon-minus">&nbsp;drop</button>
									<button type="submit" class="btn btn-success">Salvar</button>
								</div>

								
							</div>

						</form>

					</div>
				</div>
			</div>
		</div>
	</div>

</body>

</html>

