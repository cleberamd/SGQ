<%@page import="SGQ.construct.model.RIS"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="import.jsp"></jsp:include>
	
<%
		HttpSession s = request.getSession();
	if(s.getAttribute("minhalist")!=null){
		ArrayList<RIS> listaRIS = (ArrayList<RIS>) s.getAttribute("minhalist");
		
		
			
	%>
<style type="text/css">
.btn-file {
  position: relative;
  overflow: hidden;
}
.btn-file input[type=file] {
  position: absolute;
  top: 0;
  right: 0;
  min-width: 100%;
  min-height: 100%;
  font-size: 100px;
  text-align: right;
  filter: alpha(opacity=0);
  opacity: 0;
  background: red;
  cursor: inherit;
  display: block;
}
input[readonly] {
  background-color: white !important;
  cursor: text !important;
}

</style>

<script type="text/javascript">
$(document).on('change', '.btn-file :file', function() {
	  var input = $(this),
	      numFiles = input.get(0).files ? input.get(0).files.length : 1,
	      label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
	  input.trigger('fileselect', [numFiles, label]);
	});

	$(document).ready( function() {
	    $('.btn-file :file').on('fileselect', function(event, numFiles, label) {
	        
	        var input = $(this).parents('.input-group').find(':text'),
	            log = numFiles > 1 ? numFiles + ' files selected' : label;
	        
	        if( input.length ) {
	            input.val(log);
	        } else {
	            if( log ) alert(log);
	        }
	        
	    });
	});

</script>
</head>
<body style="background-color: #EEE;">
<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">
			<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">CADASTRO DE ITS</h3>
			</div>
			<div class="panel-body">
  	<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
  	
		
		<div class="col-md-3">
		
		 <jsp:include page="itsMenu.jsp" />
		
		</div>
		<div class="col-md-9">
		
		<form action="ItsController?action=salvar" method="post" enctype="multipart/form-data">
		
		
		<div class="form-group">
				
				<label>RIS</label> 
				<select class="form-control" name="ris">
								<option  > </option>
										<%
										for (RIS r : listaRIS) {
										%>
										<option  value=<%=r.getId()%>><%= r.getNome()%></option> <%}}%>
								</select>
						<label>Descrição Its</label> 
						<textarea type="text" class="form-control" name="descricao" placeholder="Entre com sua descrição"></textarea><br/>
							
						
           
	
              
          <div class="form-group" ><span class="file-input btn btn-block btn-primary btn-file">
                Browse&hellip; <input type="file" class="form-control btn btn-primary" accept="application/pdf" name="file"  />
            </span></div>
            		
        				<button type="submit" class="btn btn-primary">Salvar</button>
        				
					</div>
				</form>
			
			
 
				
				</div>
				<br/>
		</div>
		
		
		
		
		
			</div>
			</div>	
			
		
		
		
		
			
	
		
		
	</body>
</html>