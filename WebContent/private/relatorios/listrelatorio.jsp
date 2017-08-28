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

	<div id="output" ></div>
	<jsp:include page="../msm.jsp"></jsp:include>

<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">RELATÓRIOS GERAIS</h3>
			</div>
			<div class="panel-body">
  	<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
  	
	
<div class="col-md-3">
 <jsp:include page="relMenu.jsp" />

</div>

<div class="col-md-9">
 <a href="ReportServlet?action=naoConform" class="list-group-item">Não conformidades por item</a>
  <a href="ReportServlet?action=relVerif" class="list-group-item">Relatório de verificação</a>
</div>
</div>
</div>
	
	</div>

</body>
</html>

 