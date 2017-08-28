<%@page import="SGQ.construct.model.RIS"%>
<%@page import="java.util.ArrayList"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>

<head>	

	<jsp:include page="import.jsp"></jsp:include>
	
	<style type="text/css">
	th, #num{
	text-align: center;
	}	
	</style>

	  


</head>
<body style="background-color: #EEE;">



	

	
<jsp:include page="navbar.jsp"></jsp:include>
<div class="container" >
<div id="output" ></div>
	<jsp:include page="msm.jsp"></jsp:include>
<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">SISTEMA DA QUALIDADE REGISTRO DE INSPEÇÃO DE SERVIÇOS</h3>
			</div>
			<div class="panel-body">
  	<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
  	<div class="col-md-3">
  	  <jsp:include page="risMenu.jsp" />
  	</div>
  	<div class="col-md-9">
  	
  	<div class="list-group" >
  
  	<%
		HttpSession s = request.getSession();
		if(s.getAttribute("minhalist")!=null){
			ArrayList<RIS> listaRIS = (ArrayList<RIS>) s.getAttribute("minhalist");
			for (RIS r : listaRIS) {
		
		
			
			
	%>
		 <form action="RISController?action=verRIS&RisId=<%=r.getId()%>" method="post">
		
  		<button style="width: 100%;text-align: left;" type="submit" class="list-group-item">RIS-<%=r.getNumero()%> <%=r.getNome()%>  Data:<%=r.getData().toLocaleString().substring(0, 10)%></button>
  		
	
  	</form>
	<%}}%>
	</div>
  	<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
  	</div>
    </div>
    </div>
</div>
  	
  
  
</body>

</html>

