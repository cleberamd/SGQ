<%@page import="java.text.DateFormat"%>
<%@page import="SGQ.construct.model.RIS"%>
<%@page import="SGQ.construct.model.Itens"%>
<%@page import="SGQ.construct.model.PItem"%>
<%@page import="java.util.ArrayList"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>

<head>
	<jsp:include page="import.jsp"></jsp:include>

	<style type="text/css">
		th, #num,.table{
		text-align: center;
		}	
	</style>

	<%
		
		
		RIS r = (RIS) request.getAttribute("RisId");
		
			
	%>

</head>
<body style="background-color: #EEE;">
<jsp:include page="navbar.jsp"></jsp:include>
<div class="container" >
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

	

	
	 
   
    <div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">ENGEPAR</div>
  <div class="panel-body">
    <p>SISTEMA DA QUALIDADE - REGISTRO DE INSPEÇÃO DE SERVIÇOS</p>
  </div>
  
  	<form method="post" action="RISController?action=edit&RisId=<%=r.getId()%>"   >
  	<div class= "table">
	   
    <table class="table " >         
        <thead >
          
           
             <tr>
            
            <th width="19%">
            	<b>RIS-<%=r.getNumero() %></b> 
            </th>
            <th>
            <b><%=r.getNome() %><b>&nbsp <i>(Rev.<%=r.getRev() %>)</i>
            </th>
             <th>
             
            <b><%=r.getData().toLocaleString().substring(0, 10) %> </b>
            </th>                   
            </tr>
            </thead>        
   		 </table>
   		 
   		 
  	</div>
  	 
  	<div class= "table">
	
    <table class="table table-bordered " id="tabela">         
        <thead>
            <tr >            
            <th>
            	Nº
            </th>
            <th>
            Item de Inspeção
            </th>
            <th>
            Método de verificação
            </th>  
             <th>
            Tolerância
            </th>                   
            </tr>
             </thead> 
             <tbody>
                <%
                   for (Itens i : r.getItens() ) {
                   %>    
            <tr>  
                   
            <th style="width:10%;font-size: 10px">
            
            	
  			<%=i.getNum() %>
            </th>
            <th>
             <%=i.getInspecao() %>
            </th>
             <th>
              <%=i.getVerificacao() %>
            </th>
             <th>
            <%=i.getTolerancia() %>
            </th>                   
            </tr>
           <%} %>
       
        </tbody>
     		
     		
    </table>
  
     <div class="row">
    <% boolean b=false; 
    for (Itens i : r.getItens()) {
    	 for (PItem pi : i.getPitens()) {
			if(pi.getItem().equals(i)){
				b=true;
			}}
			
		}%>
		
    <%if(!b){ %>
	 <button type="submit" class="btn btn-primary">Editar</button>
	  <a class="btn btn-danger" href="RISController?action=deleta_RIS&RisId=<%=r.getId() %>" role="button">Deletar</a><%}else{ %>
     <a class="btn btn-info" href="RISController?action=revisao_RIS&RisId=<%=r.getId() %>" role="button">Revisão</a><%} %>
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

