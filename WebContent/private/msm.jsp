<!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
	   
<script type="text/javascript">

$(function(){
	var msm = '<%=request.getAttribute("msm")%>';
	
	var user = '${sessionScope.usuario.nome}' ;
	
	var textfield = $("input[name=usuario]");
	$("#output").css({
    	"position": "absolute",
    "width":" 300px",
    "top":"55px",
    "right": "0"
    
           });
	            
	            if (msm=="Login") {
                   
                    $("#output").addClass("alert alert-success animated fadeInUp").html("Bem vindo "+ user +"!");
                    $("#output").css({
                      "color": "##333"
    	                   });
                    setTimeout(function() {
                		$('#output').fadeOut('fast');}, 2500);
                   
                } else if (msm=="salvo") {
                   
                    $("#output").addClass("alert alert-success animated fadeInUp").html("Salvo com sucesso!");
                    $("#output").css({
                      "color": "##333"
    	                   });
                    setTimeout(function() {
                		$('#output').fadeOut('fast');}, 2500);
                   
                }else if (msm=="expirou") {
                   
                    
                    $("#output").addClass("alert alert-warning animated fadeInUp").html("Sua Sessão expirou! ");
                    $("#output").css({
                    	"color":" #8A6D3B"
    	                   });
                    setTimeout(function() {
                		$('#output').fadeOut('fast');}, 2500);
                   
                } 
	});


</script>
  <!-- ----------------------------------------------------------------------------------------------------------------------------------- -->
  
