<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
<head>
	<jsp:include page="../import.jsp"></jsp:include>

</head>
<body style="background-color: #EEE;">
	<jsp:include page="../navbar.jsp"></jsp:include>
	<div class="container" >
<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
<form method="post" action="ReportServlet?action=rel&relName=Nconforme"
		name="meu_form">
<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">NÃO CONFORMIDADE POR ITENS</h3>
			</div>
			<div class="panel-body">
  	<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
  	
	
<div class="col-md-4">

 <div class="input-group">
				<span class="input-group-addon" id="basic-addon1">Nome da obra</span>  <input
					type="text" class="form-control"
					placeholder="Todas obras" name="obranome" aria-describedby="basic-addon1" required disabled="disabled" >
					
					
			</div>

</div>

<div class="col-md-8">

		<div class="table">
	<div class="col-md-4">
	
	<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">Inicio</span> <input
					type="date" class="form-control"
					placeholder="dd/mm/aaaa"  name="datainicio" aria-describedby="basic-addon1" required>
					
					
			</div>
	
	</div>
			
			<div class="col-md-4">
	
	<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">final</span> <input
					type="date" class="form-control"
					placeholder="dd/mm/aaaa" name="datafinal"  aria-describedby="basic-addon1" required>
					
					
			</div>
	
	</div>

				
					
				</div>

			
</div>
	
	</div>
	<div class="row">
						<button type="submit" class="btn btn-primary">Gerar</button>
					</div><br>
	</div>
	</form>
		</div>
</body>

</html>

